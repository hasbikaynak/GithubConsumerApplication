package com.example.githubconsumerapplication.service;

import com.example.githubconsumerapplication.config.AppConfig;
import com.example.githubconsumerapplication.dto.*;
import com.example.githubconsumerapplication.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class GithubApiService {

    private AppConfig appConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<GithubRepositoryInfoDto> getGithubRepositoryInfo(String username) {
        String apiUrlForRepositories = "https://api.github.com/users/" + username + "/repos";

        // Set the personal access token in the Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + appConfig.getGithubToken());

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        // Make the API request with the access token
        ResponseEntity<GithubRepositoryDto[]> response = restTemplate.exchange(apiUrlForRepositories, HttpMethod.GET, entity, GithubRepositoryDto[].class);


        // Extract the data you need and convert it to DTO objects
        List<GithubRepositoryDto> repositories = Arrays.stream(Objects.requireNonNull(response.getBody()))
                .filter(repo -> !repo.isFork())
                .toList();

        // Create a list to store the combined repository information
        List<GithubRepositoryInfoDto> repositoryInfoList = new ArrayList<>();

        // Iterate through the repositories to extract branch information
        for (GithubRepositoryDto repo : repositories) {
            String ownerLogin = repo.getOwner().getLogin();
            String repositoryName = repo.getName();
            String branchesUrl = repo.getBranches_url();

            // Remove the "{/branch}" part from the branchesUrl
            branchesUrl = branchesUrl.replace("{/branch}", "");

            // Make the API request to fetch branches for the current repository
            ResponseEntity<BranchInfoDto[]> branchesResponse = restTemplate.exchange(branchesUrl, HttpMethod.GET, entity, BranchInfoDto[].class);
            List<BranchInfoDto> branchInfoDtos = Arrays.stream(Objects.requireNonNull(branchesResponse.getBody()))
                    .toList();


            // Create a GithubRepositoryInfoDto and add it to the list
            GithubRepositoryInfoDto repositoryInfo = new GithubRepositoryInfoDto();
            repositoryInfo.setRepositoryName(repositoryName);
            repositoryInfo.setOwnerLogin(ownerLogin);
            repositoryInfo.setBranches(branchInfoDtos);

            repositoryInfoList.add(repositoryInfo);
        }

        return repositoryInfoList;
    }

}

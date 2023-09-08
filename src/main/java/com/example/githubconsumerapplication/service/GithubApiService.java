package com.example.githubconsumerapplication.service;

import com.example.githubconsumerapplication.config.AppConfig;
import com.example.githubconsumerapplication.dto.*;
import com.example.githubconsumerapplication.error.ErrorMessages;
import com.example.githubconsumerapplication.exception.TokenNotFoundException;
import com.example.githubconsumerapplication.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class GithubApiService {

    final private AppConfig appConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<GithubRepositoryInfoDto> getGithubRepositoryInfo(final String username) {
        final String apiUrlForRepositories = "https://api.github.com/users/" + username + "/repos";

        // Set the personal access token in the Authorization header
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + appConfig.getGithubToken());

        final HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<GithubRepositoryDto[]> response;
        try {
            // Make the API request with the access token
            response = restTemplate.exchange(apiUrlForRepositories, HttpMethod.GET, entity, GithubRepositoryDto[].class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_ERROR_MESSAGES, username), HttpStatus.NOT_FOUND);
            } else {
                throw new TokenNotFoundException(ErrorMessages.TOKEN_NOT_FOUND_ERROR_MESSAGES, HttpStatus.BAD_REQUEST);
            }
        }

        // Extract the data you need and convert it to DTO objects
        final List<GithubRepositoryDto> repositories = Arrays.stream(Objects.requireNonNull(Objects.requireNonNull(response).getBody()))
                .filter(repo -> !repo.isFork())
                .toList();

        // Create a list to store the combined repository information
        final List<GithubRepositoryInfoDto> repositoryInfoList = new ArrayList<>();

        // Iterate through the repositories to extract branch information
        for (GithubRepositoryDto repo : repositories) {
            final String ownerLogin = repo.getOwner().getLogin();
            final String repositoryName = repo.getName();
            String branchesUrl = repo.getBranches_url();

            // Remove the "{/branch}" part from the branchesUrl
            branchesUrl = branchesUrl.replace("{/branch}", "");

            // Make the API request to fetch branches for the current repository
            final ResponseEntity<BranchInfoDto[]> branchesResponse = restTemplate.exchange(branchesUrl, HttpMethod.GET, entity, BranchInfoDto[].class);
            final List<BranchInfoDto> branchInfoDtos = Arrays.stream(Objects.requireNonNull(branchesResponse.getBody()))
                    .toList();


            // Create a GithubRepositoryInfoDto and add it to the list
            final GithubRepositoryInfoDto repositoryInfo = new GithubRepositoryInfoDto();
            repositoryInfo.setRepositoryName(repositoryName);
            repositoryInfo.setOwnerLogin(ownerLogin);
            repositoryInfo.setBranches(branchInfoDtos);

            repositoryInfoList.add(repositoryInfo);
        }

        return repositoryInfoList;
    }

}
package com.example.githubconsumerapplication.controller;

import com.example.githubconsumerapplication.dto.GithubRepositoryInfoDto;
import com.example.githubconsumerapplication.error.ErrorMessages;
import com.example.githubconsumerapplication.exception.UnsupportedMediaTypeException;
import com.example.githubconsumerapplication.exception.UserNotFoundException;
import com.example.githubconsumerapplication.service.GithubApiService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping("/github")
@AllArgsConstructor
public class GithubApiController {

    private GithubApiService githubApiService;

    @GetMapping("repositories")
    public ResponseEntity<?> listGitHubRepositories(
            @RequestParam String username,
            @RequestHeader HttpHeaders headers
    ) {
        // Check the Accept header to determine the response format
        if (headers.getAccept().contains(APPLICATION_XML_VALUE)) {
            // Throw an exception for unsupported media type
            throw new UnsupportedMediaTypeException(ErrorMessages.UNSUPPORTED_MEDIA_TYPE_ERROR_MESSAGES, HttpStatus.NOT_ACCEPTABLE);
        }

        // Perform the GitHub API request
        try {
            List<GithubRepositoryInfoDto> result = githubApiService.getGithubRepositoryInfo(username);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException ex) {
            // Handle the UserNotFoundException by returning a 404 response
            throw new UserNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND_ERROR_MESSAGES, username), HttpStatus.NOT_FOUND);
        }
    }
}
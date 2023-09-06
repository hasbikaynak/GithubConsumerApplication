package com.example.githubconsumerapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${github.token}")
    private String githubToken;


    public String getGithubToken() {
        return githubToken;
    }
}

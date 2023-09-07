package com.example.githubconsumerapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${github.token}")
    private String githubToken;


    public String getGithubToken() {
        return githubToken;
    }
}

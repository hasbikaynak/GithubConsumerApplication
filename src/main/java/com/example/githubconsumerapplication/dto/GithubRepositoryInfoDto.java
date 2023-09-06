package com.example.githubconsumerapplication.dto;

import java.util.List;

public class GithubRepositoryInfoDto {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchInfoDto> branches;

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<BranchInfoDto> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchInfoDto> branches) {
        this.branches = branches;
    }
}

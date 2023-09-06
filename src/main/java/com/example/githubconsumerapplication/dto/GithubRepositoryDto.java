package com.example.githubconsumerapplication.dto;

public class GithubRepositoryDto {
    private String name;
    private OwnerDto ownerDto;

    private boolean fork;

    private String branches_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OwnerDto getOwner() {
        return ownerDto;
    }

    public void setOwner(OwnerDto ownerDto) {
        this.ownerDto = ownerDto;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getBranches_url() {
        return branches_url;
    }

    public void setBranches_url(String branches_url) {
        this.branches_url = branches_url;
    }
}

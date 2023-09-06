package com.example.githubconsumerapplication.dto;

public class BranchInfoDto {
    private String name;
    private Commit commit;

    public BranchInfoDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}

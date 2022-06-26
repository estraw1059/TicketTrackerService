package com.ticketTracker.ticketTracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "Repo")
public class Repo {
    @Id
    private UUID repoId;
    private String repoName;

    public Repo() {
    }

    public Repo(UUID repoId, String repoName) {
        this.repoId = repoId;
        this.repoName = repoName;
    }

    public UUID getRepoId() {
        return repoId;
    }

    public void setRepoId(UUID repoId) {
        this.repoId = repoId;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
}

package com.ticketTracker.ticketTracker.controller;

import com.ticketTracker.ticketTracker.errorHandling.TicketTrackerException;
import com.ticketTracker.ticketTracker.model.Repo;
import com.ticketTracker.ticketTracker.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/repos")
public class RepoController {
    private final RepoService repoService;

    @Autowired
    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping
    public List<Repo> getRepos() {
        return repoService.getAllRepos();
    }

    @GetMapping(path = "{repoId}")
    public Repo getRepoById(@PathVariable("repoId") UUID repoId) throws TicketTrackerException {
        return repoService.getRepoById(repoId);
    }

    @PostMapping
    public Repo createNewRepo(@RequestBody Repo repo) {
        return repoService.createNewRepo(repo);
    }

    @PutMapping
    public Repo updateRepo(@RequestBody Repo repo) throws TicketTrackerException {
        return repoService.updateRepo(repo);
    }

    @DeleteMapping(path = "{repoId}")
    public void deleteRepo(@PathVariable("repoId") UUID repoId) throws TicketTrackerException {
        repoService.deleteRepo(repoId);
    }
}

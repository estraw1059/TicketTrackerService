package com.ticketTracker.ticketTracker.service;

import com.ticketTracker.ticketTracker.model.Repo;
import com.ticketTracker.ticketTracker.model.TicketTracker;
import com.ticketTracker.ticketTracker.repository.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class RepoService {
    private final RepoRepository repoRepository;

    private final TicketTrackerService ticketTrackerService;

    @Autowired
    public RepoService(RepoRepository repoRepository, TicketTrackerService ticketTrackerService) {
        this.repoRepository = repoRepository;
        this.ticketTrackerService = ticketTrackerService;
    }

    public List<Repo> getAllRepos() {
        return repoRepository.findAll();
    }

    public Repo createNewRepo(Repo repo) {
        // Todo: Add duplicate checking (no account right now)
        repo.setRepoId(UUID.randomUUID());
        repoRepository.save(repo);
        return repo;
    }

    @Transactional
    public Repo updateRepo(Repo repo) {
        //Check that repo exist
        Repo currentRepo = repoRepository.findById(repo.getRepoId()).orElseThrow(() -> new IllegalStateException("Repo Doesn't Exist"));
        currentRepo.setRepoName(repo.getRepoName());
        return currentRepo;
    }

    public void deleteRepo(UUID repoId) {
        Repo toDelete = repoRepository.findById(repoId).orElseThrow(() -> new IllegalStateException("Repo Doesn't Exist"));
        //Get all tickets for repo
        List<TicketTracker> ticketsToDelete = ticketTrackerService.getAllByServiceId(toDelete.getRepoId());
        ticketsToDelete.forEach(ticketToDelete -> {
            ticketTrackerService.deleteTicketById(ticketToDelete.getTicketId());
        });
        repoRepository.delete(toDelete);
    }

    public Repo getRepoById(UUID repoId) {
        return repoRepository.findById(repoId).orElseThrow(() -> new IllegalStateException(("Repo Not Found")));
    }
}

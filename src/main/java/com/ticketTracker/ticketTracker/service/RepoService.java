package com.ticketTracker.ticketTracker.service;

import com.ticketTracker.ticketTracker.errorHandling.TicketTrackerException;
import com.ticketTracker.ticketTracker.model.Repo;
import com.ticketTracker.ticketTracker.model.TicketTracker;
import com.ticketTracker.ticketTracker.repository.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Repo updateRepo(Repo repo) throws TicketTrackerException {
        //Check that repo exist
        Repo currentRepo = repoRepository.findById(
                repo.getRepoId()).orElseThrow(() ->
                new TicketTrackerException("Repo Not Found", HttpStatus.NOT_FOUND));
        currentRepo.setRepoName(repo.getRepoName());
        return currentRepo;
    }

    public void deleteRepo(UUID repoId) throws TicketTrackerException {
        Repo toDelete = repoRepository.findById(repoId)
                .orElseThrow(() -> new TicketTrackerException("Repo Not Found", HttpStatus.NOT_FOUND));
        //Get all tickets for repo
        List<TicketTracker> ticketsToDelete = ticketTrackerService.getAllByServiceId(toDelete.getRepoId());
        ticketsToDelete.forEach(ticketToDelete -> {
            try {
                ticketTrackerService.deleteTicketById(ticketToDelete.getTicketId());
            } catch (TicketTrackerException e) {
                return;
            }
        });
        repoRepository.delete(toDelete);
    }

    public Repo getRepoById(UUID repoId) throws TicketTrackerException {
        return repoRepository.findById(repoId)
                .orElseThrow(() -> new TicketTrackerException("Repo Not Found", HttpStatus.NOT_FOUND));
    }
}

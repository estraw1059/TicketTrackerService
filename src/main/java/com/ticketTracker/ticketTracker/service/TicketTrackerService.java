package com.ticketTracker.ticketTracker.service;

import com.ticketTracker.ticketTracker.model.Repo;
import com.ticketTracker.ticketTracker.model.TicketTracker;
import com.ticketTracker.ticketTracker.repository.TicketTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketTrackerService {
    private final TicketTrackerRepository ticketTrackerRepository;

    private final TimeLogService timeLogService;

    private final RepoService repoService;

    @Autowired
    public TicketTrackerService(
            TicketTrackerRepository ticketTrackerRepository,
            TimeLogService timeLogService,
            @Lazy RepoService repoService) {
        this.ticketTrackerRepository = ticketTrackerRepository;
        this.timeLogService = timeLogService;
        this.repoService = repoService;
    }

    public List<TicketTracker> getAllTickets() {
        return ticketTrackerRepository.findAll();
    }

    public TicketTracker getTicketById(UUID ticketId) {
        return ticketTrackerRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("Ticket not found"));
    }

    public void deleteTicketById(UUID ticketId) {
        TicketTracker toDelete = ticketTrackerRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("Ticket Not Found"));
        timeLogService.deleteTimeLogByTicketId(ticketId);
        ticketTrackerRepository.delete(toDelete);
    }

    public TicketTracker createTicket(TicketTracker ticket) {
        confirmServiceExist(ticket.getServiceId());
        ticket.setTicketId(UUID.randomUUID());
        ticket.setLastModified(new Date());
        ticketTrackerRepository.save(ticket);
        return ticket;
    }

    @Transactional
    public TicketTracker updateTicket(TicketTracker ticket) {
        TicketTracker ticketToUpdate =
                ticketTrackerRepository.findById(ticket.getTicketId())
                        .orElseThrow(() -> new IllegalStateException("Ticket not found"));
        confirmServiceExist(ticket.getServiceId());
        ticketToUpdate.setTicketName(ticket.getTicketName());
        ticketToUpdate.setDescription(ticket.getDescription());
        ticketToUpdate.setLastModified(new Date());
        ticketToUpdate.setServiceId(ticket.getServiceId());
        ticketToUpdate.setReview(ticket.getReview());
        ticketToUpdate.setApproved(ticket.getApproved());
        ticketToUpdate.setCommented(ticket.getCommented());
        ticketToUpdate.setInProgress(ticket.getInProgress());
        return ticketToUpdate;
    }

    private void confirmServiceExist(UUID repoId) {
        repoService.getRepoById(repoId);
    }

    public List<TicketTracker> getAllByServiceId(UUID repoId) {
        return ticketTrackerRepository.getAllByServiceId(repoId);
    }
}

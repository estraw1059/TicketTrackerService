package com.ticketTracker.ticketTracker.controller;

import com.ticketTracker.ticketTracker.errorHandling.TicketTrackerException;
import com.ticketTracker.ticketTracker.model.TicketTracker;
import com.ticketTracker.ticketTracker.service.TicketTrackerService;
import com.ticketTracker.ticketTracker.service.TimeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/tickets")
public class TicketTrackerController {
    private final TicketTrackerService ticketTrackerService;
    private final TimeLogService timeLogService;

    @Autowired
    public TicketTrackerController(TicketTrackerService ticketTrackerService, TimeLogService timeLogService) {
        this.ticketTrackerService = ticketTrackerService;
        this.timeLogService = timeLogService;
    }

    @GetMapping
    public List<TicketTracker> getAllTickets() {
        return ticketTrackerService.getAllTickets();
    }

    @GetMapping(path="{ticketId}")
    public TicketTracker getTicketById(@PathVariable("ticketId") UUID ticketId) throws TicketTrackerException {
        return ticketTrackerService.getTicketById(ticketId);
    }

    @PostMapping
    public TicketTracker createTicket(@RequestBody TicketTracker ticket) throws TicketTrackerException {
        return ticketTrackerService.createTicket(ticket);
    }

    @PutMapping
    public TicketTracker updateTicket(@RequestBody TicketTracker ticket) throws TicketTrackerException {
        return ticketTrackerService.updateTicket(ticket);
    }


    @DeleteMapping(path="{ticketId}")
    public void deleteTicketById(@PathVariable("ticketId") UUID ticketId) throws TicketTrackerException {
        ticketTrackerService.deleteTicketById(ticketId);
    }
}

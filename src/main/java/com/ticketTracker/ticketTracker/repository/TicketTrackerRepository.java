package com.ticketTracker.ticketTracker.repository;

import com.ticketTracker.ticketTracker.model.TicketTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketTrackerRepository extends JpaRepository<TicketTracker, UUID> {
}

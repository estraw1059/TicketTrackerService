package com.ticketTracker.ticketTracker.repository;

import com.ticketTracker.ticketTracker.model.TicketTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TicketTrackerRepository extends JpaRepository<TicketTracker, UUID> {
    @Modifying
    @Query("DELETE FROM ticket_tracker t where t.serviceId = ?1")
    void deleteAllTicketsInRepo(UUID repoId);

    @Query("SELECT t FROM ticket_tracker t where t.serviceId = ?1")
    List<TicketTracker> getAllByServiceId(UUID repoId);
}

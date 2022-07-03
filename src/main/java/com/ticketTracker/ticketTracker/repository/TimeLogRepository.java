package com.ticketTracker.ticketTracker.repository;

import com.ticketTracker.ticketTracker.model.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, UUID> {

    @Query("SELECT t FROM time_log t where t.ticketId = ?1")
    public List<TimeLog> findTimeLogsByTicketId(UUID ticketId);

    @Modifying
    @Query("DELETE FROM time_log t where t.ticketId = ?1")
    public void deleteByTicketId(UUID ticketId);
}

package com.ticketTracker.ticketTracker.repository;

import com.ticketTracker.ticketTracker.model.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimeLogRepository extends JpaRepository<TimeLog, UUID> {
}

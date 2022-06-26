package com.ticketTracker.ticketTracker.service;

import com.ticketTracker.ticketTracker.model.TimeLog;
import com.ticketTracker.ticketTracker.repository.TimeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TimeLogService {
    private final TimeLogRepository timeLogRepository;

    @Autowired
    public TimeLogService(TimeLogRepository timeLogRepository) {
        this.timeLogRepository = timeLogRepository;
    }

    public List<TimeLog> getAllTimeLogs() {
        return timeLogRepository.findAll();
    }

    public List<TimeLog> getAllTimeByTicket(UUID ticketId) {
        return timeLogRepository.findTimeLogsByTicketId(ticketId);
    }

    public int getTotalMinutesByTicketId(UUID ticketId) {
        // Todo: Implement
        return 10;
    }

    public TimeLog createTimeLog(TimeLog timeLog) {
        timeLog.setLogId(UUID.randomUUID());
        timeLogRepository.save(timeLog);
        return timeLog;
    }

    @Transactional
    public TimeLog updateTimeLog(TimeLog newTimeLog) {
        TimeLog oldTimeLog = timeLogRepository.findById(newTimeLog.getLogId()).orElseThrow(() -> new IllegalStateException("Time Log not found"));
        oldTimeLog.setMinutes(newTimeLog.getMinutes());
        // Todo: We need to check that the ticket Id exist. That isn't built yet
        oldTimeLog.setTicketId(newTimeLog.getTicketId());
        return oldTimeLog;
    }

    public void deleteTimeLog(UUID timeLogId) {
        TimeLog toDelete = timeLogRepository.findById(timeLogId).orElseThrow(() -> new IllegalStateException("Time Log not found"));
        timeLogRepository.delete(toDelete);
    }

}

package com.ticketTracker.ticketTracker.service;

import com.ticketTracker.ticketTracker.model.TimeLog;
import com.ticketTracker.ticketTracker.repository.TimeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TimeLogService {
    private final TimeLogRepository timeLogRepository;
    private final TicketTrackerService ticketTrackerService;

    @Autowired
    public TimeLogService(TimeLogRepository timeLogRepository,
                          @Lazy TicketTrackerService ticketTrackerService) {
        this.timeLogRepository = timeLogRepository;
        this.ticketTrackerService = ticketTrackerService;
    }

    public List<TimeLog> getAllTimeLogs() {
        return timeLogRepository.findAll();
    }

    public List<TimeLog> getAllTimeByTicket(UUID ticketId) {
        return timeLogRepository.findTimeLogsByTicketId(ticketId);
    }

    public int getTotalMinutesByTicketId(UUID ticketId) {
        List<TimeLog> allTickets = timeLogRepository.findTimeLogsByTicketId(ticketId);
        int totalTime = 0;
        for(int i = 0; i < allTickets.size(); i++) {
            totalTime = allTickets.get(i).getMinutes() + totalTime;
        }
        return totalTime;
    }

    public TimeLog createTimeLog(TimeLog timeLog) {
        confirmTicketExist(timeLog.getTicketId());
        timeLog.setLogId(UUID.randomUUID());
        timeLogRepository.save(timeLog);
        return timeLog;
    }

    @Transactional
    public TimeLog updateTimeLog(TimeLog newTimeLog) {
        confirmTicketExist(newTimeLog.getTicketId());
        TimeLog oldTimeLog = timeLogRepository.findById(newTimeLog.getLogId()).orElseThrow(() -> new IllegalStateException("Time Log not found"));
        oldTimeLog.setMinutes(newTimeLog.getMinutes());
        oldTimeLog.setTicketId(newTimeLog.getTicketId());
        return oldTimeLog;
    }

    private void confirmTicketExist(UUID ticketId) {
        ticketTrackerService.getTicketById(ticketId);
    }

    public void deleteTimeLog(UUID timeLogId) {
        TimeLog toDelete = timeLogRepository.findById(timeLogId).orElseThrow(() -> new IllegalStateException("Time Log not found"));
        timeLogRepository.delete(toDelete);
    }

    @Transactional
    public void deleteTimeLogByTicketId(UUID ticketId) {
        //Ensure that Ticket is there
        ticketTrackerService.getTicketById(ticketId);
        timeLogRepository.deleteByTicketId(ticketId);
    }
}

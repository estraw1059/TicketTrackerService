package com.ticketTracker.ticketTracker.controller;

import com.ticketTracker.ticketTracker.model.TimeLog;
import com.ticketTracker.ticketTracker.service.TimeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/timeLog")
public class TimeLogController {
    private final TimeLogService timeLogService;

    @Autowired
    public TimeLogController(TimeLogService timeLogService) {
        this.timeLogService = timeLogService;
    }

    @GetMapping
    public List<TimeLog> getAllTimeLogs() {
        return timeLogService.getAllTimeLogs();
    }

    @GetMapping("ticket/{ticketId}")
    public List<TimeLog> getAllTimeByTicketId(@PathVariable("ticketId") UUID ticketId) {
        return timeLogService.getAllTimeByTicket(ticketId);
    }

    @PostMapping
    public TimeLog createNewTimeLog(@RequestBody TimeLog timeLog) {
        return timeLogService.createTimeLog(timeLog);
    }

    @PutMapping
    public TimeLog updateTimeLog(@RequestBody TimeLog timeLog) {
        return timeLogService.updateTimeLog(timeLog);
    }

    @DeleteMapping(path="{timeLogId}")
    public void deleteTimeLog(@PathVariable("timeLogId") UUID timeLogId) {
        timeLogService.deleteTimeLog(timeLogId);
    }
}

package com.ticketTracker.ticketTracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "time_log")
@Table (name = "time_log")
public class TimeLog {
    @Id
    private UUID logId;
    private UUID ticketId;
    private int minutes;

    public TimeLog() {
    }

    public TimeLog(UUID logId, UUID ticketId, int minutes) {
        this.logId = logId;
        this.ticketId = ticketId;
        this.minutes = minutes;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UUID getLogId() {
        return logId;
    }

    public void setLogId(UUID logId) {
        this.logId = logId;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}

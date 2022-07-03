package com.ticketTracker.ticketTracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity(name = "ticket_tracker")
@Table(name = "ticket_tracker")
public class TicketTracker {
    @Id
    private UUID ticketId;
    private String ticketName;
    private String description;
    private Date lastModified;
    private UUID serviceId;
    private Boolean review;
    private Boolean approved;
    private Boolean commented;
    private Boolean inProgress;

    public TicketTracker() {
    }

    public TicketTracker(UUID ticketId, String ticketName, String description, Date lastModified, UUID serviceId, Boolean review, Boolean approved, Boolean commented, Boolean inProgress) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.description = description;
        this.lastModified = lastModified;
        this.serviceId = serviceId;
        this.review = review;
        this.approved = approved;
        this.commented = commented;
        this.inProgress = inProgress;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public Boolean getReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getCommented() {
        return commented;
    }

    public void setCommented(Boolean commented) {
        this.commented = commented;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }
}

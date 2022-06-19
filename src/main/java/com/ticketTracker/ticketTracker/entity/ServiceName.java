package com.ticketTracker.ticketTracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ServiceName {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID serviceId;

    private String serviceName;




}
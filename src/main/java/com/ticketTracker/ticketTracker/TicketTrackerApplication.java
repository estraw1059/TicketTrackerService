package com.ticketTracker.ticketTracker;

import com.ticketTracker.ticketTracker.model.Repo;
import com.ticketTracker.ticketTracker.model.TicketTracker;
import com.ticketTracker.ticketTracker.model.TimeLog;
import com.ticketTracker.ticketTracker.repository.RepoRepository;
import com.ticketTracker.ticketTracker.repository.TicketTrackerRepository;
import com.ticketTracker.ticketTracker.repository.TimeLogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class TicketTrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TicketTrackerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RepoRepository repoRepository, TimeLogRepository timeLogRepository, TicketTrackerRepository ticketTrackerRepository) {
		return args -> {
			repoRepository.save(new Repo(UUID.randomUUID(), "Example Service"));
			timeLogRepository.save(new TimeLog(UUID.randomUUID(), UUID.randomUUID(), 22));
			ticketTrackerRepository.save(new TicketTracker(
					UUID.randomUUID(),
					"Test Ticket",
					"Some Description",
					new Date(),
					UUID.randomUUID(),
					false,
					false,
					false,
					false
					));
		};
	}

}

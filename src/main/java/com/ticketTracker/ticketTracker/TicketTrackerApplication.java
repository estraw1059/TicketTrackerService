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
			UUID repoId = UUID.randomUUID();
			UUID ticketId = UUID.fromString("d833eec8-58ae-4c62-90c7-bce81e96b86e");
			UUID hourLogId = UUID.randomUUID();
			repoRepository.save(new Repo(repoId, "Example Service"));
			timeLogRepository.save(new TimeLog(hourLogId, ticketId, 22));
			ticketTrackerRepository.save(new TicketTracker(
					ticketId,
					"Test Ticket",
					"Some Description",
					new Date(),
					repoId,
					false,
					false,
					false,
					false
					));
		};
	}

}

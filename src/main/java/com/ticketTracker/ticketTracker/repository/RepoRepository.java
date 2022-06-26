package com.ticketTracker.ticketTracker.repository;

import com.ticketTracker.ticketTracker.model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepoRepository extends JpaRepository<Repo, UUID> {
}

package com.hw.fyf.repo;

import com.hw.fyf.models.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MilestoneRepository extends JpaRepository<Milestone, UUID> {
}

package com.thiagodff.job_manager.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagodff.job_manager.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

  // contains -> LIKE %filter%
  List<JobEntity> findAllByDescriptionContaining(String filter);
}

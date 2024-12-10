package com.thiagodff.job_manager.modules.candidates.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagodff.job_manager.modules.company.entities.JobEntity;
import com.thiagodff.job_manager.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {

  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(String filter) {
    return this.jobRepository.findAllByDescriptionContaining(filter);
  }
}

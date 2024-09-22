package com.thiagodff.job_manager.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodff.job_manager.modules.company.entities.JobEntity;
import com.thiagodff.job_manager.modules.company.useCases.CreateJobUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  public void create(@Valid @RequestBody JobEntity jobEntity) {
    this.createJobUseCase.execute(jobEntity);
  }

}

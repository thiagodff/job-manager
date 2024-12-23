package com.thiagodff.job_manager.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodff.job_manager.modules.company.dto.CreateJobDTO;
import com.thiagodff.job_manager.modules.company.entities.JobEntity;
import com.thiagodff.job_manager.modules.company.useCases.CreateJobUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    var jobEntity = JobEntity.builder()
      .benefits(createJobDTO.getBenefits())
      .description(createJobDTO.getDescription())
      .level(createJobDTO.getLevel())
      .companyId(UUID.fromString(companyId.toString()))
      .build();

    jobEntity.setCompanyId(UUID.fromString(companyId.toString()));

    return this.createJobUseCase.execute(jobEntity);
  }

}

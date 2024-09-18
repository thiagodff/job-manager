package com.thiagodff.job_manager.modules.candidates.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodff.job_manager.exceptions.UserFoundException;
import com.thiagodff.job_manager.modules.candidates.CandidateEntity;
import com.thiagodff.job_manager.modules.candidates.CandidateRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CandidateRepository candidateRepository;

  @PostMapping("/")
  public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
    // var candidateExists = this.candidateRepository
    //   .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail());
    // if (candidateExists != null) {
    //   throw new RuntimeException("Candidate already exists");
    // }
    this.candidateRepository
      .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
      .ifPresent((user) -> {
        throw new UserFoundException();
      });

    return this.candidateRepository.save(candidateEntity);
  }
}

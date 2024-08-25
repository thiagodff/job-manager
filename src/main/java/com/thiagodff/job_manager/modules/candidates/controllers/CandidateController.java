package com.thiagodff.job_manager.modules.candidates.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodff.job_manager.modules.candidates.CandidateEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidates")
public class CandidateController {

  @PostMapping("/")
  public void create(@RequestBody CandidateEntity candidate) {
      System.out.println("Creating candidate: " + candidate.getEmail());
  }
}

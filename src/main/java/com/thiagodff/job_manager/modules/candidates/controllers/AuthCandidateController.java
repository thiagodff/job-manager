package com.thiagodff.job_manager.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagodff.job_manager.modules.candidates.dto.AuthCandidateRequestDTO;
import com.thiagodff.job_manager.modules.candidates.useCases.AuthCandidateUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/auth")
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
    try {
      var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);
      return ResponseEntity.ok(token);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}

package com.thiagodff.job_manager.modules.candidates.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thiagodff.job_manager.modules.candidates.CandidateRepository;
import com.thiagodff.job_manager.modules.candidates.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate)
      .orElseThrow(() -> {
        throw new UsernameNotFoundException("user not found");
      });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
      .description(candidate.getDescription())
      .email(candidate.getEmail())
      .id(candidate.getId().toString())
      .name(candidate.getName())
      .username(candidate.getUsername())
      .build();

    return candidateDTO;
  }
}

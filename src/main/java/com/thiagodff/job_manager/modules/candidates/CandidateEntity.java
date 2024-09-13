package com.thiagodff.job_manager.modules.candidates;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

  private UUID id;
  private String name;

  @Pattern(regexp = "^(?!\\s*$).+", message = "Username must not contain spaces")
  private String username;

  @Email(message = "Email should be valid")
  private String email;

  @Length(min = 6, max = 100, message = "Password should have at least 6 characters and at most 100")
  private String password;
  private String description;
  private String curriculum;

}

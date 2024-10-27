package com.thiagodff.job_manager.modules.company.useCases;

import java.time.Instant;
import java.time.Duration;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.thiagodff.job_manager.modules.company.dto.AuthCompanyDTO;
import com.thiagodff.job_manager.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${security.jwt.secret}")
  private String secreteKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository
      .findByUsername(authCompanyDTO.getUsername())
      .orElseThrow(() -> {
        throw new UsernameNotFoundException("Company not found");
      });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secreteKey);
    var token = JWT.create()
      .withIssuer("quare_software")
      .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
      .withSubject(company.getId().toString())
      .sign(algorithm);

    return token;
  }

}

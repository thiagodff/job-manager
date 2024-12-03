package com.thiagodff.job_manager.modules.company.useCases;

import java.time.Instant;
import java.util.Arrays;
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
import com.thiagodff.job_manager.modules.company.dto.AuthCompanyResponseDTO;
import com.thiagodff.job_manager.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${security.jwt.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository
      .findByUsername(authCompanyDTO.getUsername())
      .orElseThrow(() -> {
        throw new UsernameNotFoundException("Company not found");
      });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresIn = Instant.now().plus(Duration.ofHours(2));

    var token = JWT.create()
      .withIssuer("quare_software")
      .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
      .withSubject(company.getId().toString())
      .withExpiresAt(expiresIn)
      .withClaim("roles", Arrays.asList("COMPANY"))
      .sign(algorithm);

    var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
      .access_token(token)
      .expires_in(expiresIn.toEpochMilli())
      .build();

    return authCompanyResponseDTO;
  }

}

package com.thiagodff.job_manager.security;

import org.hibernate.annotations.CompositeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration // queremos que seja uma classe de config que o spring gerencie
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private SecurityFilter securityFilter;

  @Autowired
  private SecurityCandidateFilter securityCandidateFilter;

  private static final String[] SWAGGER_LIST = {
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
  };

  // avisa ao spring para sobrescrever o método já implementado pelo spring security
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/candidate/").permitAll()
          .requestMatchers("/company/").permitAll()
          .requestMatchers("/company/auth").permitAll()
          .requestMatchers("/candidate/auth").permitAll()
          .requestMatchers(SWAGGER_LIST).permitAll();
        auth.anyRequest().authenticated();
      })
      // um filter/middleware/interceptor para todas as requisições
      .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
      .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

package com.thiagodff.job_manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // queremos que seja uma classe de config que o spring gerencie
public class SecurityConfig {

  // avisa ao spring para sobrescrever o método já implementado pelo spring security
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/candidate/").permitAll()
          .requestMatchers("/company/").permitAll();
        auth.anyRequest().authenticated();
      });
    return http.build();
  }
}

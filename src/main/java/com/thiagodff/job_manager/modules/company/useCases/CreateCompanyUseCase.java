package com.thiagodff.job_manager.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagodff.job_manager.exceptions.UserFoundException;
import com.thiagodff.job_manager.modules.company.entities.CompanyEntity;
import com.thiagodff.job_manager.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository
      .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
      .ifPresent(company -> {
        throw new UserFoundException();
      });

    return this.companyRepository.save(companyEntity);
  }

}

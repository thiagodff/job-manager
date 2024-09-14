package com.thiagodff.job_manager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // vai criar o construtor com argumentos
public class ErrorMessageDTO {

  private String message;
  private String field;

}

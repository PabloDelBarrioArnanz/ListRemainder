package com.delbarrio.pablo.listremainder.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.function.Function;

@Getter
public class ErrorDTO {

  private final Integer errorLevel;
  private final String message;
  private final LocalDateTime time;

  public ErrorDTO(String message) {
    this.errorLevel = 1;
    this.message = message;
    this.time = LocalDateTime.now();
  }

  public ErrorDTO(Integer errorLevel, String message) {
    this.errorLevel = errorLevel;
    this.message = message;
    this.time = LocalDateTime.now();
  }

  public static final Function<String, ErrorDTO> createError = ErrorDTO::new;
}

package com.delbarrio.pablo.listremainder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Map;

@Builder
@AllArgsConstructor
public class LogInfoResponseDTO {

  private final String id;
  private final String statusCode;
  private final Map<String, String> headers;
  private final String body;

  @Override
  public String toString() {
    return "Send Response => " +
        "{Id: " + id +
        ", StatusCode: " + statusCode +
        ", Headers: " + headers +
        ", Body: " + body + "}";
  }
}

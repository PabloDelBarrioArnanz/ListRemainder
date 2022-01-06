package com.delbarrio.pablo.listremainder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Map;

@Builder
@AllArgsConstructor
public class LogInfoRequestDTO {

  private final String id;
  private final String method;
  private final String path;
  private final Map<String, String> headers;
  private final String body;

  @Override
  public String toString() {
    return "Received Request => " +
        "{Id: " + id +
        ", Method: " + method +
        ", Path: " + path +
        ", Headers: " + headers +
        ", Body: " + body + "}";
  }
}

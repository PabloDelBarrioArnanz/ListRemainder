package com.delbarrio.pablo.listremainder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListRemindDto {

  private String id;
  private String name;
  private String topic;
  private Integer priority;
  private String text;
  private LocalDateTime lastModifiedAt;
}

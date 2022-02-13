package com.delbarrio.pablo.listremainder.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ListRemind {

  @Id
  private String id;
  private String name;
  private String topic;
  private String state;
  private Integer priority;
  private String text;
  @LastModifiedDate
  private LocalDateTime lastModifiedAt;
}

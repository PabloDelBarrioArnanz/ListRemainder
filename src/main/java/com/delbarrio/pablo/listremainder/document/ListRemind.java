package com.delbarrio.pablo.listremainder.document;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
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
  private Integer priority;
  private String text;
  //TODO Fix audit
  @CreatedDate
  private LocalDateTime createAt;
  @LastModifiedDate
  private LocalDateTime lastModifiedAt;
}

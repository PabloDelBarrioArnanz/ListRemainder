package com.delbarrio.pablo.listremainder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.delbarrio.pablo.listremainder.constant.ValidationMessages.LIST_NAME_EMPTY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListRemindDto {

  private String id;
  @Size(min = 1, max = 200, message = LIST_NAME_EMPTY)
  private String name;
  private Integer priority;
  @Size(min = 1, max = 200, message = LIST_NAME_EMPTY)
  private String text;
  private LocalDateTime createAt;
  private LocalDateTime lastModifiedAt;
}

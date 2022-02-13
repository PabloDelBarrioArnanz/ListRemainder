package com.delbarrio.pablo.listremainder.constant;

import com.delbarrio.pablo.listremainder.dto.ListRemindDto;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static com.delbarrio.pablo.listremainder.constant.ConstantDefinition.*;

public class ListRestriction {

  private ListRestriction() {
  }

  public static final Map<Predicate<ListRemindDto>, Consumer<ListRemindDto>> checks = Map.of(
      list -> Objects.isNull(list.getId()), list -> list.setId(UUID.randomUUID().toString()),
      list -> Objects.isNull(list.getPriority()), list -> list.setPriority(5),
      list -> Objects.isNull(list.getState()) || !list.getState().equals(COMPLETED_REMIND), list -> list.setState(UNCOMPLETED_REMIND),
      list -> !(1 < list.getName().length() && list.getName().length() < 120), list -> {throw new RuntimeException(VALIDATION_ERROR);},
      list -> !(1 < list.getTopic().length() && list.getTopic().length() < 50), list -> {throw new RuntimeException(VALIDATION_ERROR);}
  );

  public static final UnaryOperator<ListRemindDto> checkDefaults = list -> {
    checks.forEach((key, value) -> {
      if (key.test(list))
        value.accept(list);
    });
    return list;
  };
}
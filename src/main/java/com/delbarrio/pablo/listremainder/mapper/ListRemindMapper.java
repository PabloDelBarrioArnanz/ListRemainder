package com.delbarrio.pablo.listremainder.mapper;

import com.delbarrio.pablo.listremainder.document.ListRemind;
import com.delbarrio.pablo.listremainder.dto.ListRemindDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListRemindMapper {

  ListRemind toEntity(ListRemindDto listRemindDto);

  ListRemindDto toDto(ListRemind listRemind);

  List<ListRemind> toEntity(List<ListRemindDto> listRemindDto);

  List<ListRemindDto> toDto(List<ListRemind> listRemind);
}

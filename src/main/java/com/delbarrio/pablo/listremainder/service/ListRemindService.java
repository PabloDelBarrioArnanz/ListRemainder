package com.delbarrio.pablo.listremainder.service;

import com.delbarrio.pablo.listremainder.document.ListRemind;
import com.delbarrio.pablo.listremainder.dto.ListRemindDto;
import com.delbarrio.pablo.listremainder.mapper.ListRemindMapper;
import com.delbarrio.pablo.listremainder.repository.ListRemindRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.delbarrio.pablo.listremainder.constant.ConstantDefinition.VALIDATION_ERROR;

@Service
@AllArgsConstructor
public class ListRemindService {

  private ListRemindMapper listRemindMapper;
  private ListRemindRepository listRemindRepository;

  public Flux<ListRemindDto> findAll() {
    return listRemindRepository.findAll()
        .map(listRemindMapper::toDto);
  }

  public Mono<ListRemindDto> findById(String id) {
    return listRemindRepository.findById(id)
        .map(listRemindMapper::toDto);
  }

  public Mono<ListRemindDto> save(ListRemindDto listRemindDto) {
    return checkErrors.apply(listRemindDto)
        .map(listRemindMapper::toEntity)
        .doOnNext(setPriorityIfNull)
        .flatMap(listRemindRepository::save)
        .map(listRemindMapper::toDto);
  }

  public Mono<Void> deleteById(String id) {
    return listRemindRepository.deleteById(id);
  }

  public Mono<Void> deleteAll() {
    return listRemindRepository.deleteAll();
  }

  private static final Consumer<ListRemind> setPriorityIfNull = listRemind -> Optional.ofNullable(listRemind.getPriority())
      .ifPresentOrElse(t -> {}, () -> listRemind.setPriority(5));

  private static final Function<ListRemindDto, Mono<ListRemindDto>> checkErrors = listRemindDto -> Optional.ofNullable(listRemindDto)
      .filter(list -> 1 < list.getName().length() && list.getName().length() < 120)
      .filter(list -> 1 < list.getText().length() && list.getText().length() < 120)
      .map(Mono::just)
      .orElseThrow(() -> new RuntimeException(VALIDATION_ERROR));
}

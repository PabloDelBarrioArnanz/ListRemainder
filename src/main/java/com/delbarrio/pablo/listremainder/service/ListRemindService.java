package com.delbarrio.pablo.listremainder.service;

import com.delbarrio.pablo.listremainder.dto.ListRemindDto;
import com.delbarrio.pablo.listremainder.mapper.ListRemindMapper;
import com.delbarrio.pablo.listremainder.repository.ListRemindRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import static com.delbarrio.pablo.listremainder.constant.ListRestriction.checkDefaults;

@Service
@AllArgsConstructor
public class ListRemindService {

  private ListRemindMapper listRemindMapper;
  private ListRemindRepository listRemindRepository;

  public Flux<ListRemindDto> findAll() {
    return listRemindRepository.findAll()
        .map(listRemindMapper::toDto);
  }

  public Mono<Map<String, Collection<ListRemindDto>>> findAllGrouped() {
    return listRemindRepository.findAll()
        .map(listRemindMapper::toDto)
        .collectMultimap(ListRemindDto::getTopic, Function.identity());
  }

  public Mono<ListRemindDto> findById(String id) {
    return listRemindRepository.findById(id)
        .map(listRemindMapper::toDto);
  }

  public Flux<ListRemindDto> findByTopic(String topic) {
    return listRemindRepository.findAllByTopic(topic)
        .filter(list -> list.getTopic().equals(topic))
        .map(listRemindMapper::toDto);
  }

  public Mono<ListRemindDto> save(ListRemindDto listRemindDto) {
    return Mono.just(listRemindDto)
        .map(checkDefaults)
        .map(listRemindMapper::toEntity)
        .flatMap(listRemindRepository::save)
        .map(listRemindMapper::toDto);
  }

  public Mono<Void> deleteById(String id) {
    return listRemindRepository.deleteById(id);
  }

  public Mono<Void> deleteAll() {
    return listRemindRepository.deleteAll();
  }

  public Flux<ListRemindDto> editTopic(String oldTopic, String newTopic) {
    return listRemindRepository.findAllByTopic(oldTopic)
        .doOnNext(list -> list.setTopic(newTopic))
        .flatMap(listRemindRepository::save)
        .map(toDo -> listRemindMapper.toDto(toDo));
  }
}

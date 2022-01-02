package com.delbarrio.pablo.listremainder.service;

import com.delbarrio.pablo.listremainder.dto.ListRemindDto;
import com.delbarrio.pablo.listremainder.mapper.ListRemindMapper;
import com.delbarrio.pablo.listremainder.repository.ListRemindRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    return Mono.just(listRemindMapper.toEntity(listRemindDto))
        //TODO set lowest priority if null
        .flatMap(listRemindRepository::save)
        .map(listRemindMapper::toDto);
  }

  public Mono<Void> deleteById(String id) {
    return listRemindRepository.deleteById(id);
  }
}

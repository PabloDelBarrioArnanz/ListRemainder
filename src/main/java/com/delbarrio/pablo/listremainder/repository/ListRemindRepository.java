package com.delbarrio.pablo.listremainder.repository;

import com.delbarrio.pablo.listremainder.document.ListRemind;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ListRemindRepository extends ReactiveMongoRepository<ListRemind, String> {

  Flux<ListRemind> findAllByTopic(String topic);
}

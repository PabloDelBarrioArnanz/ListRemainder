package com.delbarrio.pablo.listremainder.handler;

import com.delbarrio.pablo.listremainder.document.ListRemind;
import com.delbarrio.pablo.listremainder.dto.ListRemindDto;
import com.delbarrio.pablo.listremainder.service.ListRemindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.delbarrio.pablo.listremainder.constant.ConstantDefinition.API_REDIRECT;
import static com.delbarrio.pablo.listremainder.constant.ConstantDefinition.ID;

@Component
@RequiredArgsConstructor
public class ListRemindHandler {

  private final ListRemindService listRemindService;

  public Mono<ServerResponse> list(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(listRemindService.findAll(), ListRemind.class);
  }

  public Mono<ServerResponse> detail(ServerRequest request) {
    return Mono.just(request.queryParam(ID)
            .orElse("NONE"))
        .flatMap(listRemindService::findById)
        .flatMap(product -> ServerResponse.ok()
            .bodyValue(product))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> create(ServerRequest request) {
    return request.bodyToMono(ListRemindDto.class)
        //TODO Read possible errors in DTO
        .flatMap(listRemindService::save)
        .flatMap(listRemind -> ServerResponse.created(URI.create(API_REDIRECT.concat(listRemind.getId())))
            .bodyValue(listRemind));
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    return Mono.just(request.queryParam(ID)
            .orElse("NONE"))
        .flatMap(listRemindService::deleteById)
        .then(ServerResponse.noContent().build());
  }
}

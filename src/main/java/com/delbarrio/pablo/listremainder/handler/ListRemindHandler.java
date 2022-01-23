package com.delbarrio.pablo.listremainder.handler;

import com.delbarrio.pablo.listremainder.document.ListRemind;
import com.delbarrio.pablo.listremainder.dto.ErrorDTO;
import com.delbarrio.pablo.listremainder.dto.ListRemindDto;
import com.delbarrio.pablo.listremainder.service.ListRemindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

import static com.delbarrio.pablo.listremainder.constant.ConstantDefinition.API_REDIRECT;
import static com.delbarrio.pablo.listremainder.constant.ConstantDefinition.ID;

@Component
@RequiredArgsConstructor
public class ListRemindHandler {

  private final ListRemindService listRemindService;

  public Mono<ServerResponse> list(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(listRemindService.findAll(), ListRemind.class)
        .onErrorResume(createErrorServerResponse);
  }

  public Mono<ServerResponse> listGrouped(ServerRequest request) {
    return ServerResponse.ok()
        .body(listRemindService.findAllGrouped(), ListRemind.class)
        .onErrorResume(createErrorServerResponse);
  }

  public Mono<ServerResponse> detail(ServerRequest request) {
    return Mono.just(request.queryParam(ID)
            .orElse("NONE"))
        .flatMap(listRemindService::findById)
        .flatMap(product -> ServerResponse.ok()
            .bodyValue(product))
        .switchIfEmpty(ServerResponse.notFound().build())
        .onErrorResume(createErrorServerResponse);
  }

  public Mono<ServerResponse> create(ServerRequest request) {
    return request.bodyToMono(ListRemindDto.class)
        .flatMap(listRemindService::save)
        .flatMap(listRemind -> ServerResponse.created(URI.create(API_REDIRECT.concat(listRemind.getId())))
            .bodyValue(listRemind))
        .onErrorResume(createErrorServerResponse);
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    return Mono.just(request.queryParam(ID)
            .orElse("NONE"))
        .flatMap(listRemindService::deleteById)
        .then(ServerResponse.noContent().build())
        .onErrorResume(createErrorServerResponse);
  }

  public Mono<ServerResponse> deleteAll(ServerRequest request) {
    return listRemindService.deleteAll()
        .then(ServerResponse.noContent().build())
        .onErrorResume(createErrorServerResponse);
  }

  private static final Function<Throwable, Mono<ServerResponse>> createErrorServerResponse = throwable -> ErrorDTO.createError
      .andThen(error -> ServerResponse.unprocessableEntity()
          .bodyValue(error))
      .apply(throwable.getMessage());
}

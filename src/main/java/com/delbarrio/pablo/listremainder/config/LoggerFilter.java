package com.delbarrio.pablo.listremainder.config;

import com.delbarrio.pablo.listremainder.dto.LogInfoRequestDTO;
import com.delbarrio.pablo.listremainder.dto.LogInfoResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
public class LoggerFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
    BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(serverWebExchange);
    String id = UUID.randomUUID().toString();
    return webFilterChain.filter(bodyCaptureExchange)
        .doOnSuccess(exchange -> {
          BodyCaptureRequest request = bodyCaptureExchange.getRequest();
          BodyCaptureResponse response = bodyCaptureExchange.getResponse();
          log.info(LogInfoRequestDTO.builder()
              .id(id)
              .method(request.getMethodValue())
              .path(request.getPath().toString())
              .headers(request.getHeaders().toSingleValueMap())
              .body(request.getFullBody())
              .build().toString());
          log.info(LogInfoResponseDTO.builder()
              .id(id)
              .headers(response.getHeaders().toSingleValueMap())
              .body(response.getFullBody())
              .build().toString());
        });
  }
}

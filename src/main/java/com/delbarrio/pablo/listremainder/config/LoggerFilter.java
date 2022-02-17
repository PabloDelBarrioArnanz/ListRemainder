package com.delbarrio.pablo.listremainder.config;

import com.delbarrio.pablo.listremainder.constant.ConstantDefinition;
import com.delbarrio.pablo.listremainder.dto.LogInfoRequestDTO;
import com.delbarrio.pablo.listremainder.dto.LogInfoResponseDTO;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;
import java.util.function.UnaryOperator;

@Slf4j
@Component
public class LoggerFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
    BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(serverWebExchange);
    String id = UUID.randomUUID().toString();
    return webFilterChain.filter(bodyCaptureExchange)
        .filter(exchange -> bodyCaptureExchange.getRequest().getPath().toString().contains(ConstantDefinition.API))
        .doOnSuccess(exchange -> {
          BodyCaptureRequest request = bodyCaptureExchange.getRequest();
          BodyCaptureResponse response = bodyCaptureExchange.getResponse();
          log.info(LogInfoRequestDTO.builder()
              .id(id)
              .method(request.getMethodValue())
              .path(request.getPath().toString())
              .headers(request.getHeaders().toSingleValueMap())
              .body(removeInvalidCharacters.apply(request.getFullBody()))
              .build().toString());
          log.info(LogInfoResponseDTO.builder()
              .id(id)
              .statusCode(Optional.ofNullable(response.getStatusCode())
                  .map(httpStatus -> httpStatus.value() + "-" + httpStatus.getReasonPhrase())
                  .orElse(null))
              .headers(response.getHeaders().toSingleValueMap())
              .body(response.getFullBody())
              .build().toString());
        });
  }

  private static final UnaryOperator<String> removeInvalidCharacters = body -> body.replace("\n", StringUtil.EMPTY_STRING)
      .replace("\r", StringUtil.EMPTY_STRING)
      .replace(" ", StringUtil.EMPTY_STRING);
}

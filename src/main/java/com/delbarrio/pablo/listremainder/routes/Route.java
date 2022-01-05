package com.delbarrio.pablo.listremainder.routes;

import com.delbarrio.pablo.listremainder.handler.ListRemindHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.delbarrio.pablo.listremainder.constant.ConstantDefinition.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class Route {

  private final ListRemindHandler listRemindHandler;

  @Bean
  public RouterFunction<ServerResponse> routes() {
    return route(GET(API).or(GET(API_LIST_ALL)), listRemindHandler::list)
        .andRoute(GET(API_FIND_ONE), listRemindHandler::detail)
        .andRoute(POST(API).or(POST(API_CREATE)), listRemindHandler::create)
        .andRoute(DELETE(API).or(DELETE(API_DELETE)), listRemindHandler::delete)
        .andRoute(DELETE(API_DELETE_ALL), listRemindHandler::deleteAll);
  }
}

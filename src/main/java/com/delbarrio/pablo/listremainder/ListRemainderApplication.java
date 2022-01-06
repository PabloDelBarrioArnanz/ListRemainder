package com.delbarrio.pablo.listremainder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
@EnableReactiveMongoAuditing
public class ListRemainderApplication {

  public static void main(String[] args) {
    SpringApplication.run(ListRemainderApplication.class, args);
  }
}

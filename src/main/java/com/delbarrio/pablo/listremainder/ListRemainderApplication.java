package com.delbarrio.pablo.listremainder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ListRemainderApplication {

  public static void main(String[] args) {
    SpringApplication.run(ListRemainderApplication.class, args);
  }
}

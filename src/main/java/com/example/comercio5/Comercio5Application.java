package com.example.comercio5;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class Comercio5Application {
  public static void main(String[] args) {
    SpringApplication.run(Comercio5Application.class, args);
  }
}

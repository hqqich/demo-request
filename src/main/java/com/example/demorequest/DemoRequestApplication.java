package com.example.demorequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demorequest.config")
public class DemoRequestApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoRequestApplication.class, args);
  }

}

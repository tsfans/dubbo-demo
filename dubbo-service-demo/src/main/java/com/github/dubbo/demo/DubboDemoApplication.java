package com.github.dubbo.demo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDubbo(scanBasePackages = "com.github.dubbo.demo.service")
@SpringBootApplication
public class DubboDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboDemoApplication.class, args);
	}

}

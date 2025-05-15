package com.sample.demo_mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms

public class DemoMqApplication {

    public static void main(String[] args) {
		SpringApplication.run(DemoMqApplication.class, args);
	}

}

package com.xzymon.rabbitsubscriber;

import com.xzymon.rabbitsubscriber.config.CredentialsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@PropertySource("${rabbitsubscriber.config.location}")
@Import(CredentialsConfig.class)
@SpringBootApplication
public class RabbitSubscriberApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitSubscriberApplication.class, args);
	}
}

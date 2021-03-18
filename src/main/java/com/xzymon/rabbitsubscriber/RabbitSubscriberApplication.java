package com.xzymon.rabbitsubscriber;

import com.xzymon.rabbitsubscriber.config.CredentialsConfig;
import com.xzymon.rabbitsubscriber.credentials.CredentialsManager;
import com.xzymon.rabbitsubscriber.receiver.RawMessageReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

//@PropertySource("${rabbitsubscriber.config.location}")
@Import(CredentialsConfig.class)
@SpringBootApplication
public class RabbitSubscriberApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitSubscriberApplication.class, args);
	}

	//subscriber.conn.user=subscriber

	static final String TOPIC_EXCHANGE_NAME = "test.sth";

	static final String QUEUE_NAME = "test.q";
	static final boolean DURABLE = true;

	static final String ROUTING_KEY = "test.#";

	static final String URI_NO_CREDENTIALS = "amqp://localhost:5672/%2f";
	static final String USERNAME = "guest";
	static final String PASSWORD = "guest";

	/*
	@Value("${subscriber.conn.user}")
	private String username;

	@Value("${subscriber.conn.pass}")
	private String password;
	 */

	/*
	@Profile("json")
	@Bean
	public MessageConverter jsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	@Profile("text")
	@Bean
	public MessageConverter textMessageConverter(){
		return new SimpleMessageConverter();
	}*/

	/*
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	                                         MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}*/

	/*
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver, MessageConverter messageConverter) {
		return new MessageListenerAdapter(receiver, messageConverter);
	}*/
}

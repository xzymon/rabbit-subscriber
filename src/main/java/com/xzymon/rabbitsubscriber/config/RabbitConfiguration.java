package com.xzymon.rabbitsubscriber.config;

import com.xzymon.rabbitsubscriber.credentials.*;
import com.xzymon.rabbitsubscriber.fun.MyFunClass;
import com.xzymon.rabbitsubscriber.receiver.RawMessageReceiver;
import com.xzymon.rabbitsubscriber.repository.MessageRepository;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@PropertySource("${rabbitsubscriber.config.location}")
@Import(CredentialsConfig.class)
@Configuration
public class RabbitConfiguration {

	private static final String QUEUE_NAME_PROPERTY_NAME = "subscriber.conn.queue";
	private static final String TOPIC_EXCHANGE_NAME_PROPERTY_NAME = "subscriber.conn.topic.exchange";
	private static final String ROUTING_KEY_PROPERTY_NAME = "subscriber.conn.routing.key";
	private static final String URI_NO_CREDENTIALS_PROPERTY_NAME = "subscriber.conn.uri_no_credentials";
	private static final String DURABLE_PROPERTY_NAME = "subscriber.conn.durable";
	private static final String USERNAME_PROPERTY_NAME = "subscriber.conn.user";
	private static final String PASSWORD_PROPERTY_NAME = "subscriber.conn.pass";

	@Value("${subscriber.onlyOnJboss}")
	private String onlyOnJboss;

	@Value("${subscriber.conn.queue}")
	private String queueName;

	@Value("${subscriber.conn.topic.exchange}")
	private String topicExchangeName;

	@Value("${subscriber.conn.routing.key}")
	private String routingKey;

	@Value("${subscriber.conn.uri_no_credentials}")
	private String uriNoCredentials;

	@Value("${subscriber.conn.durable}")
	private Boolean durable;

	@Bean
	MyFunClass myFunClass() {
		MyFunClass myFunClass = new MyFunClass();
		myFunClass.setSomeValue(onlyOnJboss);
		System.out.println("MyFunClass is set on [" + myFunClass.getSomeValue() + "]");
		return myFunClass;
	}

	@Bean
	Queue queue() {
		return new Queue(queueName, durable);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	@Bean
	MessageListener messageListener(MessageRepository messageRepository) {
		return new RawMessageReceiver(messageRepository);
	}

	@Bean
	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageListener messageListener) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(messageListener);
		return container;
	}

	@Bean
	com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory(CredentialsManager credentialsManager) {
		try {
			com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory = new com.rabbitmq.client.ConnectionFactory();
			rabbitConnectionFactory.setUri(uriNoCredentials);
			rabbitConnectionFactory.setUsername(credentialsManager.get(USERNAME_PROPERTY_NAME));
			System.out.println("Username is: " + rabbitConnectionFactory.getUsername());
			rabbitConnectionFactory.setPassword(credentialsManager.get(PASSWORD_PROPERTY_NAME));
			System.out.println("Password is: " + rabbitConnectionFactory.getPassword());
			return rabbitConnectionFactory;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	ConnectionFactory connectionFactory(com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitConnectionFactory);
		return connectionFactory;
	}
}
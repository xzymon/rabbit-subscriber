package com.xzymon.rabbitsubscriber.receiver;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMessageReceiver implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("Received ---- Message ----");
		System.out.println(message.getMessageProperties());
		System.out.println(message.getBody());
	}
//<property name="rabbitsubscriber.config.location" value="classpath:/server-one/rabbit-subscriber.properties" boot-time="true"/>
	/*
	@Override
	public void containerAckMode(AcknowledgeMode mode) {

	}

	@Override
	public void onMessageBatch(List<Message> messages) {

	}*/
}

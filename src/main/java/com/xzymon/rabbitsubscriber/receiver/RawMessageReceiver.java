package com.xzymon.rabbitsubscriber.receiver;

import com.xzymon.rabbitsubscriber.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class RawMessageReceiver implements MessageListener {

	Logger logger = LoggerFactory.getLogger(RawMessageReceiver.class);

	private MessageRepository messageRepository;

	public RawMessageReceiver(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public void onMessage(Message message) {
		System.out.println("Received ---- Message ----");
		System.out.println(message.getMessageProperties());
		System.out.println(message.getBody());

		System.out.println("A teraz proba wrzucenia do bazy");
		com.xzymon.rabbitsubscriber.domain.Message dbMessage = new com.xzymon.rabbitsubscriber.domain.Message();
		dbMessage.setMsgContent(message.getBody());
		try {
			logger.info(new String(message.getBody(), "UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		messageRepository.insertWithEntityManager(dbMessage);
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

package com.xzymon.rabbitsubscriber.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;

//@Component
public class Receiver {
	/*
	public void handleMessage(String text) {
		System.out.println("Received --- text ---");
		System.out.println(text);
	}

	public void handleMessage(Map map) {
		System.out.println("Received --- Map ---");
		System.out.println(map.toString());
	}

	public void handleMessage(byte[] bytes) {
		System.out.println("Received --- bytes ---");
		try {
			System.out.println(new String(bytes, "UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
	}

	public void handleMessage(Serializable obj) {
		System.out.println("Received --- Serializable obj ---");
		System.out.println(obj);
	}*/

	public void handleMessage(Message message) {
		System.out.println("Received --- Message ---");
	}
}

package com.xzymon.rabbitsubscriber.credentials.decryptor;

public class SimpleDecryptor implements Decryptor {

	@Override
	public byte[] decrypt(byte[] source) {
		return source;
	}
}

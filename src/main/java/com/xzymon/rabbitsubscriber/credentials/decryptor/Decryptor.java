package com.xzymon.rabbitsubscriber.credentials.decryptor;

public interface Decryptor {
	byte[] decrypt(byte[] source);
}

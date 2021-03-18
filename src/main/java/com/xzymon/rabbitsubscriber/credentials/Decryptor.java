package com.xzymon.rabbitsubscriber.credentials;

public interface Decryptor {
	byte[] decrypt(byte[] source);
}

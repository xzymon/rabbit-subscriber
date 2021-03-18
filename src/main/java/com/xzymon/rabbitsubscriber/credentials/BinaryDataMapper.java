package com.xzymon.rabbitsubscriber.credentials;

import java.util.Map;

public interface BinaryDataMapper {
	Map<String, String> map(byte[] source);
}

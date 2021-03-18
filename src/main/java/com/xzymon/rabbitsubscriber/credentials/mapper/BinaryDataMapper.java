package com.xzymon.rabbitsubscriber.credentials.mapper;

import java.util.Map;

public interface BinaryDataMapper {
	Map<String, String> map(byte[] source);
}

package com.xzymon.rabbitsubscriber.config;

import com.xzymon.rabbitsubscriber.credentials.*;
import com.xzymon.rabbitsubscriber.credentials.decryptor.BackwardsLineDecryptor;
import com.xzymon.rabbitsubscriber.credentials.decryptor.Decryptor;
import com.xzymon.rabbitsubscriber.credentials.mapper.BinaryDataMapper;
import com.xzymon.rabbitsubscriber.credentials.mapper.SimpleDataMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class CredentialsConfig {

	@Value("${rabbitsubscriber.encrypted.config.location}")
	private Resource encryptedResource;

	@Bean
	Decryptor decryptor() {
		return new BackwardsLineDecryptor();
	}

	@Bean
	BinaryDataMapper binaryDataMapper() {
		return new SimpleDataMapper();
	}

	@Bean
	CredentialsManager credentialsManager(Decryptor decryptor, BinaryDataMapper binaryDataMapper) {
		CredentialsManager credentialsManager = new CredentialsManager(encryptedResource, decryptor, binaryDataMapper);
		return credentialsManager;
	}

}

package com.xzymon.rabbitsubscriber.credentials;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class CredentialsManager {
	private byte[] binarySource;
	private Map<String, String> map;

	public CredentialsManager(Resource resource, Decryptor decryptor, BinaryDataMapper dataMapper) {
		extractBinaryDataFromResource(resource);
		map = getMappedData(dataMapper, getDecryptedData(decryptor));
	}

	public String get(String key) {
		return map.get(key);
	}

	private void extractBinaryDataFromResource(Resource resource) {
		byte[] binary = null;
		try (InputStream inputStream = resource.getInputStream()) {
			int avail = inputStream.available();
			if (avail > 0) {
				binary = new byte[avail];
				inputStream.read(binary);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		binarySource = binary;
	}

	private byte[] getDecryptedData(Decryptor decryptor) {
		byte[] workingData = Arrays.copyOf(binarySource, binarySource.length);
		return decryptor.decrypt(workingData);
	}

	private Map<String, String> getMappedData(BinaryDataMapper dataMapper, byte[] dataToBeMapped) {
		return dataMapper.map(dataToBeMapped);
	}
}

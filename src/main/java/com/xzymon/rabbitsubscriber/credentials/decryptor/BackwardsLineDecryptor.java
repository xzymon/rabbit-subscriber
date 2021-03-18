package com.xzymon.rabbitsubscriber.credentials.decryptor;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BackwardsLineDecryptor implements Decryptor {

	@Override
	public byte[] decrypt(byte[] source) {
		if (source != null) {
			try (InputStream is = new ByteArrayInputStream(source);
			     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			) {
				StringBuilder backwardsBuffer = new StringBuilder(source.length);
				boolean firstLine = true;
				while (bufferedReader.ready()) {
					if (!firstLine) {
						backwardsBuffer.append("\n");
					} else {
						firstLine = false;
					}
					String line = bufferedReader.readLine();
					char[] lineChars = line.toCharArray();
					StringBuilder backwardsLineBuffer = new StringBuilder(lineChars.length);
					for (int cursor = lineChars.length - 1; cursor > -1; cursor--) {
						backwardsLineBuffer.append(lineChars[cursor]);
					}
					backwardsBuffer.append(backwardsLineBuffer.toString());
				}
				return backwardsBuffer.toString().getBytes(StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new byte[0];
	}
}

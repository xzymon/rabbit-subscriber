package com.xzymon.rabbitsubscriber.credentials;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SimpleDataMapper implements BinaryDataMapper {
	public static final String SEPARATOR = "=";

	private static final String WRONG_LINE_MSG_PART = "Natrafiono na bledna linie (linia :%1$d ): ";
	private static final String WRONG_LINE_EMPTY_MSG_PART = WRONG_LINE_MSG_PART + "linia jest pusta";
	private static final String WRONG_LINE_NO_SEPARATOR_MSG = WRONG_LINE_MSG_PART + "brak separatora \"%2$s\" w linii";
	private static final String WRONG_LINE_MORE_THAN_ONE_SEPARATOR_MSG = WRONG_LINE_MSG_PART + "separator \"%2$s\" wystepuje w niej %3$d razy - linia zostanie pominieta";

	public Map<String, String> map(byte[] source) {
		Map<String, String> result = new HashMap<>();

		try (InputStream is = new ByteArrayInputStream(source);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
		) {
			int lineNo = 0;
			while (bufferedReader.ready()) {
				String line = bufferedReader.readLine();
				if(!line.isEmpty()) {
					if(line.contains(SEPARATOR)) {
						String[] parts = line.split(SEPARATOR);
						if (parts.length < 2 || parts.length > 2) {
							System.out.println(String.format(WRONG_LINE_MORE_THAN_ONE_SEPARATOR_MSG, lineNo, SEPARATOR, parts.length-1));
						} else {
							result.put(parts[0], parts[1]);
						}
					} else {
						System.out.println(String.format(WRONG_LINE_NO_SEPARATOR_MSG, lineNo, SEPARATOR));
					}
				} else {
					System.out.println(String.format(WRONG_LINE_EMPTY_MSG_PART, lineNo));
				}
				lineNo++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}

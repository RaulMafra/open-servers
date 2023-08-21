package utils;

import java.util.ArrayList;
import java.util.List;

public class FormatterUtil {

	public static List<String> storeFormatter(String[] splitLine) {
		List<String> stores = new ArrayList<>();
		for (int i = 0; i < splitLine.length; i++) {
			String item = splitLine[i];
			String[] split = item.split(",");
			stores.add(split[1]);
		}

		return stores;
	}

	public static List<String> replace(List<String> stores, List<String> formatter) {

		for (String store : stores) {
			String line = null;
			String newLine = null;
			for (int i = 0; i < 3; i++) {
				if (i == 1) {
					line = formatter.get(1);
					newLine = line.replace(line.substring(6, 9), store);
					formatter.set(1, newLine);
				}
				if (i == 2) {
					line = formatter.get(2);
					newLine = line.replace(line.substring(13, 16), store);
					formatter.set(2, newLine);
				}

			}

		}
		return formatter;
	}

}

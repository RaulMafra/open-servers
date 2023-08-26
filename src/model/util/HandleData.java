package model.util;

import java.util.List;
import java.util.regex.Pattern;

public class  HandleData {

	private String splitStoreName(String store) {
		String[] split = store.split(",");
		store = split[1];

		return store;
	}

	public List<String> replace(String storeFull, List<String> formatter) {

		String line = null;
		String StoreName = splitStoreName(storeFull);

		for (int i = 1; i < 3; i++) {
			line = formatter.get(i);
			formatter.set(i, Pattern.compile("XXX").matcher(line).replaceAll(StoreName));
		}

		return formatter;

	}

}
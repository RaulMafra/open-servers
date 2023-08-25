package model.util;

import java.util.List;

public class HandleData {

	private String splitStoreName(String store) {
		String[] split = store.split(",");
		store = split[1];

		return store;
	}

	public List<String> replace(String storeFull, List<String> formatter) {
		String StoreName = splitStoreName(storeFull);

		String line = null;
		String newLine = null;
		
		for (int i = 0; i < 3; i++) {
			if (i == 1) {
				line = formatter.get(1);
				newLine = line.replace(line.substring(6, 9), StoreName);
				formatter.set(1, newLine);

			}
			if (i == 2) {
				line = formatter.get(2);
				newLine = line.replace(line.substring(13, 16), StoreName);
				if (newLine.substring(20, 23).equals(StoreName)) {
					newLine = newLine.replace(newLine.substring(20, 23), "MAR");
				}
				formatter.set(2, newLine);
			}

		}

		return formatter;

	}

}

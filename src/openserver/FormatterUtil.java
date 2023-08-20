package openserver;

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

}

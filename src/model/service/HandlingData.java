package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import exceptions.HandlingDataException;
import model.entity.Store;
import model.util.HandlingFile;

public final class HandlingData {

	public static void store(String[] storesFull) {
		List<Store> listStore = new ArrayList<>();

		for (String store : storesFull) {
			storesFull = store.split(",");
			if (!(storesFull[0].charAt(2) != 3) || !(storesFull[1].charAt(2) != 3)) {
				throw new HandlingDataException(
						"Stores must be in the format \"xxx,yyy\", where 'x' is the store number and 'y' is the store name");
			}
			Short num = Short.parseShort(storesFull[0]);
			String name = storesFull[1];
			listStore.add(new Store(num, name));
		}

		HandlingFile.writterMiddleXML(listStore);
	}

	public static List<String> replace(Store stores, List<String> formatter) throws IndexOutOfBoundsException {
		for (int i = 0; i < 4; i++) {
			String line = formatter.get(i);
			formatter.set(i, Pattern.compile("XXX").matcher(line).replaceAll(stores.getName()));
		}

		return formatter;
	}

}

package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import exceptions.HandlingDataException;
import model.entity.Store;
import model.util.HandlingFile;

public class HandlingData {
		
	public static void store(String[] storesFull) {
		List<Store> listStore = new ArrayList<>();
		
		for (String store : storesFull) {
			storesFull = store.split(",");
			if(!(storesFull.length > 1 || !(storesFull.length < 3))){
				throw new HandlingDataException("Stores must be in the format \"xxx,yyy\", where 'x' is the store number and 'y' is the store name");
			}
			Short num = Short.parseShort(storesFull[0]);
			String name = storesFull[1];
			listStore.add(new Store(num, name));
		}
		
		HandlingFile.writter(listStore);
	}

	public static List<String> replace(Store stores, List<String> formatter) throws IndexOutOfBoundsException{
			for (int i = 1; i < 3; i++) {
				String line = formatter.get(i);
				formatter.set(i, Pattern.compile("XXX").matcher(line).replaceAll(stores.getName()));
			}
		
		return formatter;
	}


}

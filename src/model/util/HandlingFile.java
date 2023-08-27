package model.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class HandlingFile {

	private static List<String> formatter;
	private static String targetPath;
	
	public static List<String> reader() {
		formatter = new ArrayList<>();

		String sourcePath = "C:\\Users\\raulc\\openserver\\bin\\LOGIN_SERVERS";

		try (BufferedReader br = new BufferedReader(new FileReader(sourcePath))) {

			String read = br.readLine();
			while (read != null) {
				formatter.add(read);
				read = br.readLine();
			}

		} catch (IOException e) {
			System.out.println("File not finded: " + e.getMessage());
		}
		return formatter;
	}
	

	public static void writter(String[] storesFull) {
		for (String store : storesFull) {
			List<String> rawList = new ArrayList<>(formatter);

			HandlingData.replace(store, rawList);

			boolean createFile = false;
			try {
				createFile = new File("C:\\Users\\tmp\\out.txt").createNewFile();
				targetPath = "C:\\Users\\tmp\\out.txt";

			} catch (IOException e) {
				System.out.println("File not created: " + e.getMessage());
			}

			if (createFile) {
				JOptionPane.showMessageDialog(null, "File created with successfully!");
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath, true))) {

				for (String line : rawList) {
					bw.write(line);
					bw.newLine();
					System.out.println("\n\n");
				}

			} catch (IOException e) {
				System.out.println("Error writter: " + e.getMessage());
			}
		}
	}

}

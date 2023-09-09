package model.util;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.entity.Store;
import model.service.HandlingData;

public class HandlingFile {

	private static List<String> formatter;

	private static File extensionTXT = null;
	private static File extensionRDG = new File("C:\\PRIMARYs\\Primary.rgd");

	private static List<String> reader() {
		formatter = new ArrayList<>();

		String sourcePath = "C:\\PRIMARYs\\SERVIDORES.xml";

		try (BufferedReader br = new BufferedReader(new FileReader(sourcePath))) {

			String read = br.readLine();
			while (read != null) {
				formatter.add(read);
				read = br.readLine();
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Server formatting file not found", null, JOptionPane.ERROR_MESSAGE);
		}

		return formatter;
	}

	public static void writterMiddleXML(List<Store> stores) {
		reader();
		extensionTXT = new File("C:\\PRIMARYs\\Primary.txt");
		boolean createFile = false;
		try {
			do {
				createFile = extensionTXT.createNewFile();
				if (createFile) {
					JOptionPane.showMessageDialog(null, "File created with successfully!");
				}
				if (extensionRDG.exists()) {
					extensionRDG.delete();
					createFile = extensionTXT.createNewFile();
				}
				createFile = true;
			} while (createFile == false);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File not created: " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}

		List<String> upsideXML = formatter.subList(0, 13);
		List<String> bottomXML = formatter.subList(25, 30);

		writterUpsideXML(upsideXML);

		for (Store store : stores) {
			List<String> middleXML = new ArrayList<>(formatter.subList(13, 25));

			HandlingData.replace(store, middleXML);

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(extensionTXT, true))) {

				for (String line : middleXML) {
					bw.write(line);
					bw.newLine();
				}
				bw.flush();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error in writing middle xml: " + e.getMessage(), null,
						JOptionPane.ERROR_MESSAGE);
			}
		}
		writterBottomXML(bottomXML);
		openPrimary(extensionTXT);
	}

	private static void writterUpsideXML(List<String> upsideXML) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(extensionTXT, true))) {

			for (String line : upsideXML) {
				bw.write(line);
				bw.newLine();
			}
			bw.flush();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error in writing top xml: " + e.getMessage(), null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void writterBottomXML(List<String> bottomXML) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(extensionTXT, true))) {

			for (String line : bottomXML) {
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error in writing bottom xml: " + e.getMessage(), null,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void openPrimary(File targetPath) {

		if (targetPath.renameTo(extensionRDG)) {
			try {
				Desktop.getDesktop().open(extensionRDG);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}

		}

	}

}

package application;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.util.HandleData;

public class application implements ActionListener {

	private JFrame Jframe = new JFrame();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel Jpanel = new JPanel();
	private JLabel Jlabel = new JLabel();
	private JButton criar = new JButton();
	private JButton limpar = new JButton();
	private JTextArea textArea = new JTextArea();
	HandleData data = new HandleData();
	private JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					application window = new application();
					window.Jframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public application() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Jframe = new JFrame();
		Jframe.setBounds(100, 100, 275, 422);
		Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Jframe.getContentPane().setLayout(null);

		Jpanel = new JPanel();
		Jpanel.setBounds(0, 0, 259, 383);
		Jframe.getContentPane().add(Jpanel);
		Jpanel.setLayout(null);

		Jlabel = new JLabel("Lojas");
		Jlabel.setBounds(79, 11, 46, 14);
		Jpanel.add(Jlabel);
		Jlabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		limpar = new JButton("Limpar");
		limpar.setBounds(160, 186, 89, 23);
		Jpanel.add(limpar);
		buttonGroup.add(limpar);

		criar = new JButton("Criar");
		criar.setBounds(160, 127, 89, 23);
		Jpanel.add(criar);
		buttonGroup.add(criar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 36, 101, 303);
		Jpanel.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		criar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent action) {

		String[] storesFull = textArea.getText().split("\\r?\\n");

		Collections.sort(Arrays.asList(storesFull));
		System.out.println(Arrays.toString(storesFull));

		String sourcePath = "C:\\Users\\raulc\\openserver\\bin\\LOGIN_SERVERS";
		String targetPath = null;

		List<String> formatter = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(sourcePath))) {

			String read = br.readLine();
			while (read != null) {
				formatter.add(read);
				read = br.readLine();
			}

		} catch (IOException e) {
			System.out.println("File not finded: " + e.getMessage());
		}

		for (String store : storesFull) {
			List<String> rawList = new ArrayList<>(formatter);

			data.replace(store, rawList);

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

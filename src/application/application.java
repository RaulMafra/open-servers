package application;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import exceptions.HandlingDataException;
import model.service.HandlingData;

public class Application implements ActionListener {

	private JFrame Jframe = new JFrame();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel Jpanel = new JPanel();
	private JLabel Jlabel = new JLabel();
	private JButton Jcreate = new JButton();
	private JButton Jclear = new JButton();
	private JTextArea JtextArea = new JTextArea();
	private JScrollPane JscrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 * 
	 */

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application application = new Application();
					application.Jframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 * 
	 */
	public Application() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		Jframe = new JFrame();
		Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Jframe.setTitle("PRIMARYs");
		Jframe.setResizable(false);
		Jframe.setBounds(100, 100, 275, 422);
		Jframe.getContentPane().setLayout(null);

		Jpanel = new JPanel();
		Jpanel.setBounds(0, 0, 259, 383);
		Jframe.getContentPane().add(Jpanel);
		Jpanel.setLayout(null);

		Jlabel = new JLabel("Stores");
		Jlabel.setBounds(79, 11, 46, 14);
		Jpanel.add(Jlabel);
		Jlabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		Jclear = new JButton("Clear");
		Jclear.setBounds(160, 186, 89, 23);
		Jpanel.add(Jclear);
		buttonGroup.add(Jclear);

		Jcreate = new JButton("Create");
		Jcreate.setBounds(160, 127, 89, 23);
		Jpanel.add(Jcreate);
		buttonGroup.add(Jcreate);

		JscrollPane = new JScrollPane();
		JscrollPane.setBounds(47, 36, 101, 303);
		Jpanel.add(JscrollPane);

		JtextArea = new JTextArea();
		JscrollPane.setViewportView(JtextArea);

		Jcreate.addActionListener(this);
		Jclear.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		try {

			if (event.getActionCommand().equals("Limpar")) {
				JtextArea.setText("");
			} else {
				String[] storesFull = JtextArea.getText().split("\\r?\\n");

				HandlingData.store(storesFull);
			}
			
		} catch (HandlingDataException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Incorrect value, please enter a value in the format \"xxx,yyy\", where 'x' is the store number and 'y' is the store name",
					null, JOptionPane.ERROR_MESSAGE);
		}
		catch(IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "File size smaller than expected", null, JOptionPane.ERROR_MESSAGE);
		}

	}

}

package Motorensteuerung;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class Motorensteuerung_2 extends JFrame {

	private JPanel contentPane;
	private int speed;
	JLabel lblGeschwindigkeit = new JLabel("0");
	Motor motor = new Motor(0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Motorensteuerung_2 frame = new Motorensteuerung_2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Motorensteuerung_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton btnLangsamer = new JButton("Langsamer");
		MotorenAction motorenAction = new MotorenAction(motor);
		btnLangsamer.setHideActionText(true);
		btnLangsamer.setAction(motorenAction);
		btnLangsamer.setName("Langsamer");
		btnLangsamer.setText("Langsamer");
		InputMap langsamerMap = new InputMap();
		langsamerMap.put(KeyStroke.getKeyStroke("u"), "action Name");
		panel.add(btnLangsamer);
		JButton btnSchneller = new JButton("Schneller");
		btnSchneller.setHideActionText(true);
		btnSchneller.setAction(motorenAction);
		btnSchneller.setName("Schneller");
		btnSchneller.setText("Schneller");
		panel.add(btnSchneller);
		contentPane.add(lblGeschwindigkeit, BorderLayout.CENTER);
	}

}

package com.example;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
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
//		btnLangsamer.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				if (e.getKeyCode() == 38) {
//					System.out.println("up");
//				}
//				if (e.getKeyCode() == 40) {
//					System.out.println("down");
//				}
//			}
//		});
//		btnLangsamer.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				speed -= 1;
//				lblGeschwindigkeit.setText("" + speed);
//			}
//		});
		
		MotorenAction motorenAction = new MotorenAction(motor);
//		panel.addKeyListener(motorenAction);
		
		// Sollte langsamerAction & schnellerAction haben
		// TODO
		
//		btnLangsamer.setAction(new MotorenAction("Langsamer",motor));
		btnLangsamer.setHideActionText(true);
		btnLangsamer.setAction(motorenAction);
		btnLangsamer.setName("Langsamer");
		btnLangsamer.setText("Langsamer");
		InputMap langsamerMap = new InputMap();
		
		langsamerMap.put(KeyStroke.getKeyStroke("u"), "action Name");
		
		btnLangsamer.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, new InputMap().put(KeyStroke.getKeyStroke("u"), 0));

		panel.add(btnLangsamer);

		JButton btnSchneller = new JButton("Schneller");
		btnSchneller.setHideActionText(true);
		btnSchneller.setAction(motorenAction);
		btnSchneller.setName("Schneller");
		btnSchneller.setText("Schneller");

//		btnSchneller.setAction(new MotorenAction("Schneller",motor));

//		btnSchneller.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				speed += 1;
//				lblGeschwindigkeit.setText("" + speed);
//			}
//		});
		panel.add(btnSchneller);

		contentPane.add(lblGeschwindigkeit, BorderLayout.CENTER);
	}

}

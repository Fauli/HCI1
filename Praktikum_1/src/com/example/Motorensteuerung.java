package com.example;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class Motorensteuerung extends JFrame {

	private JPanel contentPane;
	private int speed;
	JLabel lblGeschwindigkeit = new JLabel("0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Motorensteuerung frame = new Motorensteuerung();
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
	public Motorensteuerung() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JButton btnLangsamer = new JButton("Langsamer");
		final JButton btnSchneller = new JButton("Schneller");

		btnLangsamer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 38) {
					System.out.println("up");
					speed += 1;
					lblGeschwindigkeit.setText("" + speed);
					if(speed == 10){
						btnSchneller.setEnabled(false);
					} else {
						btnSchneller.setEnabled(true);
						btnLangsamer.setEnabled(true);
					}
					
				}
				if (e.getKeyCode() == 40) {
					System.out.println("down");
					speed -= 1;
					lblGeschwindigkeit.setText("" + speed);
					if(speed == 0){
						btnLangsamer.setEnabled(false);
					} else {
						btnLangsamer.setEnabled(true);
						btnSchneller.setEnabled(true);
					}
					
				}
			}
		});
		btnLangsamer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speed -= 1;
				lblGeschwindigkeit.setText("" + speed);
				if(speed == 0){
					btnLangsamer.setEnabled(false);
				} else {
					btnLangsamer.setEnabled(true);
					btnSchneller.setEnabled(true);
				}
			}
		});
		
		
		panel.add(btnLangsamer);


		btnSchneller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speed += 1;
				lblGeschwindigkeit.setText("" + speed);
				if(speed == 10){
					btnSchneller.setEnabled(false);
				} else {
					btnSchneller.setEnabled(true);
					btnLangsamer.setEnabled(true);

					
				}
			}
		});
		panel.add(btnSchneller);

		contentPane.add(lblGeschwindigkeit, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setSize(200, 200);
		panel_1.setForeground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.WEST);
	}

}

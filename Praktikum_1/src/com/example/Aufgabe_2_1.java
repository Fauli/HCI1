package com.example;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class Aufgabe_2_1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtAn;
	private JTextField textField_2;
	private JTextField txtBetreff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aufgabe_2_1 frame = new Aufgabe_2_1();
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
	public Aufgabe_2_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		txtAn = new JTextField();
		txtAn.setText("An");
		panel.add(txtAn);
		txtAn.setColumns(10);
		
		txtBetreff = new JTextField();
		txtBetreff.setText("Betreff");
		panel.add(txtBetreff, BorderLayout.SOUTH);
		txtBetreff.setColumns(10);
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, BorderLayout.CENTER);
		textField_2.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSenden = new JButton("Senden");
		panel_1.add(btnSenden);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		panel_1.add(btnAbbrechen);
	}

}

package com.example;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.FlowLayout;

public class Aufgabe_2_2_2 extends JFrame {

	private JPanel contentPane;
	private JTextField firstNameInput;
	private JTextField lastNameInput;
	private JTextField addressInput;
	private JLabel addressLabel;
	private JTextField postCodeInput;
	private JButton saveButton;
	private JLabel postCodeCityLabel;
	private JButton cancelButton;
	private JTextField cityInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aufgabe_2_2_2 frame = new Aufgabe_2_2_2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Aufgabe_2_2_2() {
		setTitle("Adresse erfassen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(450, 205));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel firstNameLabel = new JLabel("Vorname");

		firstNameInput = new JTextField();
		firstNameInput.setColumns(10);

		lastNameInput = new JTextField();
		lastNameInput.setColumns(10);

		JLabel lastNameLabel = new JLabel("Nachname");

		addressInput = new JTextField();
		addressInput.setColumns(10);

		addressLabel = new JLabel("Adresse");

		postCodeInput = new JTextField();
		postCodeInput.setColumns(10);

		saveButton = new JButton("Speichern");

		postCodeCityLabel = new JLabel("PLZ/Ort");

		cancelButton = new JButton("Abbrechen");

		cityInput = new JTextField();
		cityInput.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(17)
																		.addComponent(
																				firstNameLabel)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				firstNameInput,
																				GroupLayout.DEFAULT_SIZE,
																				350,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lastNameLabel)
																						.addComponent(
																								addressLabel)
																						.addComponent(
																								postCodeCityLabel))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								addressInput,
																								GroupLayout.DEFAULT_SIZE,
																								350,
																								Short.MAX_VALUE)
																						.addComponent(
																								lastNameInput,
																								GroupLayout.DEFAULT_SIZE,
																								350,
																								Short.MAX_VALUE)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																postCodeInput,
																																Alignment.LEADING,
																																0,
																																0,
																																Short.MAX_VALUE)
																														.addComponent(
																																saveButton,
																																Alignment.LEADING,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																cancelButton,
																																Alignment.TRAILING)
																														.addComponent(
																																cityInput,
																																GroupLayout.DEFAULT_SIZE,
																																227,
																																Short.MAX_VALUE))))))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																firstNameInput,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																firstNameLabel))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lastNameLabel)
														.addComponent(
																lastNameInput,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																addressInput,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																addressLabel))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																postCodeInput,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																postCodeCityLabel)
														.addComponent(
																cityInput,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																saveButton)
														.addComponent(
																cancelButton))
										.addContainerGap(97, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}

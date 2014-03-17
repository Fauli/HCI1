package com.example;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.JButton;

public class Aufgabe_2_3_2 extends JFrame {

	private JPanel contentPane;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel addressLabel;
	private JLabel postCodeCityLabel;
	private JButton saveButton;
	private JButton cancelButton;
	private JTextField postCodeInput;
	private JTextField cityInput;
	private JTextField addressInput;
	private JTextField lastNameInput;
	private JTextField firstNameInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aufgabe_2_3_2 frame = new Aufgabe_2_3_2();
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
	public Aufgabe_2_3_2() {
		setTitle("Adresse erfassen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 300);
		setMinimumSize(new Dimension(445, 195));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		firstNameLabel = new JLabel("Vorname");
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 0;
		contentPane.add(firstNameLabel, gbc_firstNameLabel);

		firstNameInput = new JTextField();
		GridBagConstraints gbc_firstNameInput = new GridBagConstraints();
		gbc_firstNameInput.gridwidth = 2;
		gbc_firstNameInput.insets = new Insets(0, 0, 5, 0);
		gbc_firstNameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameInput.gridx = 1;
		gbc_firstNameInput.gridy = 0;
		contentPane.add(firstNameInput, gbc_firstNameInput);
		firstNameInput.setColumns(10);

		lastNameLabel = new JLabel("Nachname");
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lastNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameLabel.gridx = 0;
		gbc_lastNameLabel.gridy = 1;
		contentPane.add(lastNameLabel, gbc_lastNameLabel);

		lastNameInput = new JTextField();
		GridBagConstraints gbc_lastNameInput = new GridBagConstraints();
		gbc_lastNameInput.gridwidth = 2;
		gbc_lastNameInput.insets = new Insets(0, 0, 5, 0);
		gbc_lastNameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameInput.gridx = 1;
		gbc_lastNameInput.gridy = 1;
		contentPane.add(lastNameInput, gbc_lastNameInput);
		lastNameInput.setColumns(10);

		addressLabel = new JLabel("Adresse");
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.anchor = GridBagConstraints.EAST;
		gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressLabel.gridx = 0;
		gbc_addressLabel.gridy = 2;
		contentPane.add(addressLabel, gbc_addressLabel);

		addressInput = new JTextField();
		GridBagConstraints gbc_addressInput = new GridBagConstraints();
		gbc_addressInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressInput.gridwidth = 2;
		gbc_addressInput.insets = new Insets(0, 0, 5, 0);
		gbc_addressInput.gridx = 1;
		gbc_addressInput.gridy = 2;
		contentPane.add(addressInput, gbc_addressInput);
		addressInput.setColumns(10);

		postCodeCityLabel = new JLabel("PLZ/Ort");
		GridBagConstraints gbc_postCodeCityLabel = new GridBagConstraints();
		gbc_postCodeCityLabel.anchor = GridBagConstraints.EAST;
		gbc_postCodeCityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_postCodeCityLabel.gridx = 0;
		gbc_postCodeCityLabel.gridy = 3;
		contentPane.add(postCodeCityLabel, gbc_postCodeCityLabel);

		postCodeInput = new JTextField();
		GridBagConstraints gbc_postCodeInput = new GridBagConstraints();
		gbc_postCodeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_postCodeInput.insets = new Insets(0, 0, 5, 5);
		gbc_postCodeInput.gridx = 1;
		gbc_postCodeInput.gridy = 3;
		contentPane.add(postCodeInput, gbc_postCodeInput);
		postCodeInput.setColumns(4);

		cityInput = new JTextField();
		GridBagConstraints gbc_cityInput = new GridBagConstraints();
		gbc_cityInput.insets = new Insets(0, 0, 5, 0);
		gbc_cityInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityInput.gridx = 2;
		gbc_cityInput.gridy = 3;
		contentPane.add(cityInput, gbc_cityInput);
		cityInput.setColumns(10);

		saveButton = new JButton("Speichern");
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveButton.insets = new Insets(0, 0, 0, 5);
		gbc_saveButton.gridx = 1;
		gbc_saveButton.gridy = 4;
		contentPane.add(saveButton, gbc_saveButton);

		cancelButton = new JButton("Abbrechen");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.EAST;
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = 4;
		contentPane.add(cancelButton, gbc_cancelButton);
	}

}

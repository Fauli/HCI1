package com.example;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Aufgabe_2_3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aufgabe_2_3 frame = new Aufgabe_2_3();
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
	public Aufgabe_2_3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Vorname");
		contentPane.add(lblNewLabel, "4, 4, right, default");
		
		textField = new JTextField();
		contentPane.add(textField, "6, 4, fill, default");
		textField.setColumns(10);
		
		JLabel lblNachname = new JLabel("Nachname");
		contentPane.add(lblNachname, "4, 8, right, default");
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "6, 8, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Adresse");
		contentPane.add(lblNewLabel_1, "4, 12, right, default");
		
		textField_2 = new JTextField();
		contentPane.add(textField_2, "6, 12, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("PLZ / Stadt");
		contentPane.add(lblNewLabel_2, "4, 16, right, default");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3, "6, 16, fill, default");
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Speichern");
		contentPane.add(btnNewButton, "4, 20");
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		contentPane.add(btnAbbrechen, "6, 20");
	}

}

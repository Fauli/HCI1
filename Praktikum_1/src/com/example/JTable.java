package com.example;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JTable extends JFrame {

	private JPanel contentPane;
	private javax.swing.JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTable frame = new JTable();
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
	public JTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String[] columns = {"Vorname","Nachname","Geschlecht","Geburtstag"};
		String[][] data = {{"Sopie","MŸller","f","25.12.2002"},
				{"Katharina","MŸller","f","12.09.1980"},
				{"Frank","MŸller","m","05.02.1967"}};
		
//		Person p1 = new Person();
//		p1.setFirstName("Sopie");
//		p1.setFirstName("MŸller");
//		p1.setGender(Person.Gender.FEMALE);
//		p1.setBirthday(new Date("25.01.2002"));
//		ArrayList<Person> persons = new ArrayList<Person>();
//		persons.add(p1);
		
		table = new javax.swing.JTable(data,columns);
		contentPane.add(table, BorderLayout.CENTER);
	}

}

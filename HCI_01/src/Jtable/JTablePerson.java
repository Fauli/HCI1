package Jtable;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Jtable.Person2.Gender;

public class JTablePerson {
	public static void main(String[] args) {
		final JTableModel model = new JTableModel();

		JTable table = new JTable(model);


		Person2 person1 = new Person2(Gender.MALE, "Hans", "Muster", "10.08.1989");
		model.addPerson(person1);
		
		Person2 person2 = new Person2(Gender.FEMALE, "Olga", "Mistress", "10.10.1967");
		model.addPerson(person2);
		
		Person2 person3 = new Person2(Gender.MALE, "Theo", "Atoll", "15.03.1952");
		model.addPerson(person3);
		
		Person2 person4 = new Person2(Gender.FEMALE, "Anna", "Kurti", "24.02.1989");
		model.addPerson(person4);
		
		Person2 person5 = new Person2(Gender.FEMALE, "Tania", "Rutz", "09.01.1993");
		model.addPerson(person5);

		JFrame frame = new JFrame("JTable");

		Container content = frame.getContentPane();

		content.add(new JScrollPane(table), BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

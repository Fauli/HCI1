package Jtable;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTablePerson {
	public static void main(String[] args) {
		final JTableModel model = new JTableModel();

		JTable table = new JTable(model);


		Person person1 = new Person("MALE", "Hans", "asdf", "10.08.1989");
		model.addPerson(person1);
		Person person2 = new Person("FEMALE", "Olga", "fdas", "10.10.1967");
		model.addPerson(person2);
		Person person3 = new Person("MALE", "Theo", "dfsas", "15.03.1952");
		model.addPerson(person3);
		Person person4 = new Person("FEMALE", "Anna", "fddfa", "24.02.1989");
		model.addPerson(person4);
		Person person5 = new Person("FEMALE", "Tania", "sasfd", "09.01.1993");
		model.addPerson(person5);

		JFrame frame = new JFrame("Demo");

		Container content = frame.getContentPane();

		content.add(new JScrollPane(table), BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

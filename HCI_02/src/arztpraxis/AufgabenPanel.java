package arztpraxis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class AufgabenPanel extends JPanel {
	final JtableAufgabenModel tableDaysModel = new JtableAufgabenModel();
	
	public AufgabenPanel() {
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Aufgabe hinzufügen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableDaysModel.addAufgabe();
			}
		});
		btnNewButton.setBounds(171, 228, 122, 23);
		add(btnNewButton);
		
	
		JTable theTable = new JTable(tableDaysModel);
		add(new JScrollPane(theTable));
		
		
	}
}

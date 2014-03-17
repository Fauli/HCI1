package arztpraxis;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AufgabenPanel extends JPanel {

	public AufgabenPanel() {
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JLabel filler = new JLabel("Aufgabe");
		filler.setHorizontalAlignment(JLabel.CENTER);
		add(filler);

	}

}

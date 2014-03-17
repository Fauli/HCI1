package Layout;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NeueNachrichtGridLayout extends JFrame {

	private JPanel contentPane;
	private JTextField txtAn;
	private JTextField textField_1;
	private JTextField txtBetreff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NeueNachrichtGridLayout frame = new NeueNachrichtGridLayout();
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
	public NeueNachrichtGridLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		txtAn = new JTextField();
		txtAn.setText("An");
		contentPane.add(txtAn);
		txtAn.setColumns(10);
		
		txtBetreff = new JTextField();
		txtBetreff.setText("Betreff");
		contentPane.add(txtBetreff);
		txtBetreff.setColumns(10);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btnNewButton_1 = new JButton("Senden");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Abbrechen");
		panel.add(btnNewButton);
	}

}

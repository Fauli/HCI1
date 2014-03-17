package Layout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NeueNachrichtGridBagLayout extends JFrame {

	private JPanel contentPane;
	private JTextField txtAn;
	private JTextField txtBetreff;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NeueNachrichtGridBagLayout frame = new NeueNachrichtGridBagLayout();
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
	public NeueNachrichtGridBagLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0,   Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtAn = new JTextField();
		txtAn.setText("An");
		GridBagConstraints gbc_txtAn = new GridBagConstraints();
		gbc_txtAn.insets = new Insets(0, 0, 5, 0);
		gbc_txtAn.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAn.gridx = 0;
		gbc_txtAn.gridy = 0;
		contentPane.add(txtAn, gbc_txtAn);
		txtAn.setColumns(10);
		
		txtBetreff = new JTextField();
		txtBetreff.setText("Betreff");
		GridBagConstraints gbc_txtBetreff = new GridBagConstraints();
		gbc_txtBetreff.insets = new Insets(0, 0, 5, 0);
		gbc_txtBetreff.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBetreff.gridx = 0;
		gbc_txtBetreff.gridy = 1;
		contentPane.add(txtBetreff, gbc_txtBetreff);
		txtBetreff.setColumns(10);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 2;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Senden");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Abbrechen");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 4;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
	}

}

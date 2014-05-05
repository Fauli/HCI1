package org.nigfau.changer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PwdChanger extends JFrame {

	String pwdPattern = "((?=.*[0-9])(?=.*[a-z]) (?=.*[A-Z])(?=.*[@#*=_$&+])(?=[\\S]+$).{5,16})";
//	String pwdPattern = "((?=.*[0-9])(?=.*[a-z]) (?=.*[A-Z])(?=.*[@#*=_$&+])(?=[\\S]+$).{5,16})";
	
	private JPanel contentPane;
	private JPasswordField txtCurrPwd;
	private JLabel lblNewPassword;
	private JPasswordField txtNewPwd;
	private JLabel lblConfirmNewPwd;
	private JPasswordField txtNewPwdConfirm;
	private JPanel panel;
	private JButton btnSave;
	private JButton btnCancel;
	private JLabel lblInfoLabel;

	private JTextField txtUsername;

	// private JTextField txtCurrPwd;
	// private JTextField txtNewPwd;
	// private JTextField txtNewPwdConfirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PwdChanger frame = new PwdChanger();
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
	public PwdChanger() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		contentPane.add(lblUsername, gbc_lblUsername);

		txtUsername = new JTextField();
		txtUsername.setText("faulfra");
		txtUsername.setEditable(false);
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 0;
		gbc_txtUsername.gridy = 1;
		contentPane.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);

		JLabel lblCurrentPassword = new JLabel("Current Password:");
		lblCurrentPassword.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblCurrentPassword = new GridBagConstraints();
		gbc_lblCurrentPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPassword.gridx = 0;
		gbc_lblCurrentPassword.gridy = 2;
		contentPane.add(lblCurrentPassword, gbc_lblCurrentPassword);

		txtCurrPwd = new JPasswordField();
		GridBagConstraints gbc_txtCurrPwd = new GridBagConstraints();
		gbc_txtCurrPwd.insets = new Insets(0, 0, 5, 0);
		gbc_txtCurrPwd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCurrPwd.gridx = 0;
		gbc_txtCurrPwd.gridy = 3;
		contentPane.add(txtCurrPwd, gbc_txtCurrPwd);
		txtCurrPwd.setColumns(10);

		lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewPassword.gridx = 0;
		gbc_lblNewPassword.gridy = 4;
		contentPane.add(lblNewPassword, gbc_lblNewPassword);

		txtNewPwd = new JPasswordField();
		GridBagConstraints gbc_txtNewPwd = new GridBagConstraints();
		gbc_txtNewPwd.insets = new Insets(0, 0, 5, 0);
		gbc_txtNewPwd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNewPwd.gridx = 0;
		gbc_txtNewPwd.gridy = 5;
		contentPane.add(txtNewPwd, gbc_txtNewPwd);
		txtNewPwd.setColumns(10);

		lblConfirmNewPwd = new JLabel("Confirm New Password:");
		lblConfirmNewPwd.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblConfirmNewPwd = new GridBagConstraints();
		gbc_lblConfirmNewPwd.insets = new Insets(0, 0, 5, 0);
		gbc_lblConfirmNewPwd.gridx = 0;
		gbc_lblConfirmNewPwd.gridy = 6;
		contentPane.add(lblConfirmNewPwd, gbc_lblConfirmNewPwd);

		txtNewPwdConfirm = new JPasswordField();
		GridBagConstraints gbc_txtNewPwdConfirm = new GridBagConstraints();
		gbc_txtNewPwdConfirm.insets = new Insets(0, 0, 5, 0);
		gbc_txtNewPwdConfirm.anchor = GridBagConstraints.NORTH;
		gbc_txtNewPwdConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNewPwdConfirm.gridx = 0;
		gbc_txtNewPwdConfirm.gridy = 7;
		contentPane.add(txtNewPwdConfirm, gbc_txtNewPwdConfirm);
		txtNewPwdConfirm.setColumns(10);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkIfOldIsCorrect(txtCurrPwd.getText())) {
					checkIfNewIsValid(txtNewPwd.getText(),
							txtNewPwdConfirm.getText());
				}
			}
		});
		panel.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				System.exit(1);
			}
		});
		panel.add(btnCancel);

		lblInfoLabel = new JLabel("");
		lblInfoLabel.setForeground(Color.red);
		GridBagConstraints gbc_lblInfoLabel = new GridBagConstraints();
		gbc_lblInfoLabel.gridx = 0;
		gbc_lblInfoLabel.gridy = 9;
		contentPane.add(lblInfoLabel, gbc_lblInfoLabel);
	}

	public boolean checkIfOldIsCorrect(String oldPwd) {
		lblInfoLabel.setForeground(Color.red);
		if (oldPwd.equals("test")) {
			return true;
		} else {
			lblInfoLabel.setText("Old password is incorrect");
			return false;
		}

	}

	public boolean checkIfNewIsValid(String newPwd, String newPwdConfirm) {
		if (newPwd.equals(newPwdConfirm)) {
			System.out.println("Confirm and other are the same");
			if (checkPwdPolicy(newPwd)) {
				lblInfoLabel.setForeground(Color.BLUE);
				lblInfoLabel.setText("Pwd Successfully changed");
			} else {
				lblInfoLabel.setText("Pwd does not match Policy");
			}
		} else {
			lblInfoLabel.setText("New Passwords do not match");
			return false;
		}

		return true;
	}

	public boolean checkPwdPolicy(String newPwd) {
		System.out.println("new pwd is: "+newPwd);
		if (newPwd.length() < 8) {
			lblInfoLabel.setText("New Passwords must be at least 8 chars");
			return false;
		} else if(newPwd.matches("(?=.*[0-9])")){
			System.out.println("The has a number");
			return true;
		} else {
			System.out.println("Did not match the POLICY");
			return false;
		}
	}
}

package Motorensteuerung;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class MotorenAction extends AbstractAction implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Motor motor;
	
	public MotorenAction(String name, Motor motor){
		super(name);
		this.motor = motor;
	};
	
	public MotorenAction(Motor motor){
		this.motor = motor;
	};

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		System.out.println("Action Called");
		if(arg0.getSource() instanceof JButton){
			System.out.println("ITS A BUTTON");
			JButton source = (JButton) arg0.getSource();
			boolean equals = source.getName().equals("Langsamer");
			doTheNeedful(equals);	
		}
		

	}

	private void doTheNeedful(boolean down) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		boolean downButtonPressed = e.getKeyCode() == 38;
		doTheNeedful(downButtonPressed);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

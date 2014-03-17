package com.download;

/*
 		Graphical Calendar JAVA Application
  		Copyleft: Manoj Tiwari
 			 
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;

public class CelendarApp extends JApplet{
	
	
	
	private JPanel p;
	
	
	
	public void init(){
		
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	
		    	if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            
		            break;
		        }
		        else{
		        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		setLayout(new GridLayout(1,1));
		setSize(315,193);
		
		Days d= new Days();
		
		add(d);

	}
	
	
}

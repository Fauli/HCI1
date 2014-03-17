package com.download;


/*
 		Graphical Calendar JAVA Application
  		Copyleft: Manoj Tiwari
 			 
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;

public class Celendar extends JFrame{
	
	
	
	private JPanel p;
	
	
	
	Celendar(){
		super("Calendar");
		
		//UIManager.put("nimbusBase", new Color(233,22,22));
        //UIManager.put("nimbusBlueGrey", new Color(22,200,150));
        //UIManager.put("control", new Color(100,150,200));
		
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
		setSize(322,223);
		setResizable(false);
		Days d= new Days();
		
		add(d);
//		try{
//			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//			//UIManager.setLookAndFeel("com.sun.javax.swing.plaf.metal.MetalLookAndFeel");
//			SwingUtilities.updateComponentTreeUI(this);
//		}catch(Exception e){System.out.println("error "+e);}
	}
	
	
	
	public static void main(String aggsp[]){
		
		Celendar c = new Celendar();
		c.setVisible(true);
		c.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	
}

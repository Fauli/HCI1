package com.download;

/*

 		Graphical Calendar JAVA Application
  		Copyleft: Manoj Tiwari
 			 
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.UIManager;
import java.util.*;

public class Days extends JPanel {
	//lables use for showing weekday names
	
	
	private JLabel sun;
	private JLabel mon;
	private JLabel tue;
	private JLabel wed;
	private JLabel thu;
	private JLabel fri;
	private JLabel sat;
	
	private Icon pic[]= {new ImageIcon(getClass().getResource("blackArrowUp.gif")),new ImageIcon(getClass().getResource("blackArrowDown.gif"))};
	
	private JPanel mainpanel;   				//for add days and weekday
	private JPanel p[]= new JPanel[42];			//Array of Panels for Adding Days Buttons 
	private JButton b[]= new JButton[31];		//Array of 31 Buttons For 31 Days
	
	
		//for year , month and date (all will be added to First )
		
		
		private JPanel datepanel;			//Panel on which date , year and month will add
		private JTextField date;		//for showing date in text fields
		public JComboBox month;			//for months
		public JTextField year;			//for year
		private JButton sb[]= new JButton[2];			// for increasing year by 1 on every click
		
		//string array has name of months for adding to combobox
		
		private String strmon[]= {"January","Febuary","March","April","May","June","July","Augest","September","October","November","December"};

		//Constructor Start
	Days(){
		
		 
		setLayout(null);				//setting layout 
		setSize(350,228);
		setVisible(true);
		
		
		
		//first Frame Build
		
		datepanel = new JPanel();
		datepanel.setLayout(null);
		//datepanel.setLayout(new GridLayout(1,4,2,2));
		datepanel.setSize(325,31);
		datepanel.setLocation(1,0);
		
		
		
		date = new JTextField(); 	//date textField will strore the date 
		date.setSize(100,30);
		date.setLocation(5,0);
		date.setBackground(Color.WHITE);
		date.setFont(new Font("Arial",Font.PLAIN,14));
		date.setEditable(false);
		
		month = new JComboBox(strmon);			//month combo box will have all months
		month.setSize(90,30);
		month.setLocation(110,0);
		
		
		year = new JTextField("2008");		//year will have year part of date	
		year.setSize(70,30);
		year.setLocation(205,0);
		year.setBackground(Color.WHITE);
		year.setEditable(false);
		
		sb[0] = new JButton("");				//sb button increments the year by 1
		sb[0].setSize(30,15);
		sb[0].setLocation(275,0);
		sb[0].setIcon(pic[0]);
		
		sb[1] = new JButton("");				//sb button decrements the year by 1
		sb[1].setSize(30,15);
		sb[1].setLocation(275,16);
		sb[1].setIcon(pic[1]);

		datepanel.add(date);
		datepanel.add(month);
		datepanel.add(year);
		datepanel.add(sb[0]);
		datepanel.add(sb[1]);
		add(datepanel);
		
		
		
		//Days Frame 
		
		mainpanel = new JPanel();
		mainpanel.setLayout(new GridLayout(7,7,1,1));
		mainpanel.setSize(315,160);
		mainpanel.setLocation(1,31);
		
		//Calendar now = Calendar.getInstance();
		//int y=getYear();
		//System.out.println(now.get(Calendar.DATE));
		//System.out.println('01-02-2010'.get(Calendar.DAY_OF_MONTH));
		//System.out.println(now.get(Calendar.DAY_OF_WEEK));
		//System.out.println(now.get(Calendar.YEAR));
		//System.out.println(dayOfWeek);
		
		sun = new JLabel("Sun");
		sun.setForeground(Color.RED);
		mon = new JLabel("Mon");
		tue = new JLabel("Tue");
		wed = new JLabel("Wed");
		thu = new JLabel("Thu");
		fri = new JLabel("Fri");
		sat = new JLabel("Sat");
		
		//add labels to panel
		mainpanel.add(sun);
		mainpanel.add(mon);
		mainpanel.add(tue);
		mainpanel.add(wed);
		mainpanel.add(thu);
		mainpanel.add(fri);
		mainpanel.add(sat);
		
		//Initializing memory to Jpanels and add it to mainpanel
		for (int x=0;x<42;x++){
			p[x]= new JPanel();
			p[x].setLayout(new GridLayout(1,1));
			p[x].setBackground(Color.WHITE);
			mainpanel.add(p[x]);
			validate();
		}
		
		final HandlerClass handler= new HandlerClass();		// object of handlerclass
		
		for (int i=0;i<31;i++){			//only Initializing memory to buttons not adding them
			b[i]= new JButton();
			b[i].addActionListener(handler);
			b[i].setFont(new Font("Times New Roman",Font.PLAIN,11));
			b[i].setFocusable(false);
			b[i].setText(Integer.toString(i+1));
		
		}
		
		
		final Calendar now = GregorianCalendar.getInstance(); 		//create a Calendar object
		year.setText(Integer.toString(now.get(Calendar.YEAR)));		//get year and month from Calendar object
		month.setSelectedIndex(now.get(Calendar.MONTH));			//add values to year and month
																			
		validate();
		
		// DAY_OF_WEEK GIVES THE DAY OF THE FROM WIHCH THE MONTH STARTS
		int ye=Integer.parseInt(year.getText());
		Calendar cal = new GregorianCalendar(ye, month.getSelectedIndex(), 1);	

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);   
		
		for (int i=0;i<getDaysNo();i++){	
			
			p[dayOfWeek-1].add(b[i]);
			dayOfWeek++;
			}
		
		int bd=now.get(Calendar.DATE);				//getting the current date
		b[bd-1].setEnabled(false);					// sets the current date button enabled false
		
		
		add(mainpanel);						// add main pannel to 
		
		validate();
	
		month.addItemListener(
		new ItemListener(){

			public void itemStateChanged(ItemEvent event){
				if (event.getStateChange()==ItemEvent.SELECTED){
					mainpanel.removeAll();
					validate();
					
					mainpanel.add(sun);
					mainpanel.add(mon);
					mainpanel.add(tue);
					mainpanel.add(wed);
					mainpanel.add(thu);
					mainpanel.add(fri);
					mainpanel.add(sat);
					
					for (int x=0;x<42;x++){
						
						p[x].removeAll();
						mainpanel.add(p[x]);
						validate();
					}
					
					
					int ye=Integer.parseInt(year.getText());
					Calendar cal = new GregorianCalendar(ye, month.getSelectedIndex(), 1);
					
					int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); 
					
					for (int i=0;i<getDaysNo();i++){
							
						p[dayOfWeek-1].add(b[i]);
						dayOfWeek++;
						validate();
					}
					validate();
				}
				datepanel.validate();
				validate();
				
				date.setText(Integer.toString(handler.db +1).concat(" / ").concat(Integer.toString(month.getSelectedIndex() + 1)).concat(" / ").concat(year.getText()));
			}	
		}
		);
	
		
		sb[0].addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int y = Integer.parseInt(year.getText());
					y++;
					onYearClick(y);
						//sets the date on Click of year Button
						date.setText(Integer.toString(handler.db +1).concat(" / ").concat(Integer.toString(month.getSelectedIndex() + 1)).concat(" / ").concat(year.getText()));
						
				}
								
				}
		);
		


		sb[1].addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int y = Integer.parseInt(year.getText());
					y--;
					onYearClick(y);
					//sets the date on Click of year Button
					date.setText(Integer.toString(handler.db +1).concat(" / ").concat(Integer.toString(month.getSelectedIndex() + 1)).concat(" / ").concat(year.getText()));
		
				}
								
				}
		);



		//sets the date on form load
		date.setText(Integer.toString(handler.db +1).concat(" / ").concat(Integer.toString(month.getSelectedIndex() + 1)).concat(" / ").concat(year.getText()));
	}
	
	
	//runs when year changes
	public void onYearClick(int y){
		year.setText(Integer.toString(y));
		
		mainpanel.removeAll();
		validate();
		
		mainpanel.add(sun);
		mainpanel.add(mon);
		mainpanel.add(tue);
		mainpanel.add(wed);
		mainpanel.add(thu);
		mainpanel.add(fri);
		mainpanel.add(sat);
		
		for (int x=0;x<42;x++){
			
			
			p[x].removeAll();
			mainpanel.add(p[x]);
			validate();
		}
			
		int ye=Integer.parseInt(year.getText());
		Calendar cal = new GregorianCalendar(ye, month.getSelectedIndex(), 1);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); 
		
		for (int i=0;i<getDaysNo();i++){
			
			p[dayOfWeek-1].add(b[i]);
			dayOfWeek++;
			validate();
		}
	
		validate();
		

	}
	
	

	//returns no. of days in a week
	public int getDaysNo(){
		int no = 31;
		if (month.getSelectedIndex()==1){
			no=28;
			if (Integer.parseInt(year.getText())%4==0){
				no=29;
				
			}
			month.validate();
		}
		
		if (month.getSelectedIndex()==3 || month.getSelectedIndex()==5 || month.getSelectedIndex()==8 || month.getSelectedIndex()==10)
		{
			no=30;
		}
		return no;
		
	}
	
	
	// handler class set which date is selected 
	
	class HandlerClass implements ActionListener{
			
		
		Calendar now = Calendar.getInstance();
		public int db=(now.get(Calendar.DATE))-1;
		
		public void actionPerformed(ActionEvent e){
			 	
			for (int k=0;k<31;k++){	
			 		
			 		if (e.getSource()==b[k])
			 		{
			 			b[k].setEnabled(false); 		
			 			validate();
			 			db=k;
			 			
			 		}
			 		else{
			 			b[k].setEnabled(true);
			 			validate();
			 		}	
			 		month.validate();
			 		
			 	}

		
			 	date.setText(Integer.toString(db +1).concat(" / ").concat(Integer.toString(month.getSelectedIndex()+1)).concat(" / ").concat(year.getText()));
		}
		
		
	}
	
}
	

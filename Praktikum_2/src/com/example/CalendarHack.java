package com.example;

/*
 Swing Hacks Tips and Tools for Killer GUIs
 By Joshua Marinacci, Chris Adamson
 First Edition June 2005  
 Series: Hacks
 ISBN: 0-596-00907-0
 Pages: 542
 website: http://www.oreilly.com/catalog/swinghks/
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalendarHack extends JPanel {
	protected Image background = new ImageIcon("calendar.png").getImage();
	protected Image highlight = new ImageIcon("highlight.png").getImage();
	protected Image day_img = new ImageIcon("day.png").getImage();

	protected SimpleDateFormat month = new SimpleDateFormat("MMMM");

	protected SimpleDateFormat year = new SimpleDateFormat("yyyy");

	protected SimpleDateFormat day = new SimpleDateFormat("d");

	protected Date date = new Date();

	public void setDate(Date date) {
		this.date = date;
	}

	public CalendarHack() {
		this.setPreferredSize(new Dimension(300, 280));
	}

	public void paintComponent(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(background, 0, 0, null);
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.PLAIN, 18));
		g.drawString(month.format(date), 34, 36);
		g.setColor(Color.white);
		g.drawString(year.format(date), 235, 36);

		Calendar today = Calendar.getInstance();
		today.setTime(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK) + 1);
		for (int week = 0; week < 6; week++) {
			for (int d = 0; d < 7; d++) {
				Image img = day_img;
				Color col = Color.black;
				// only draw if it's actually in this month
				if (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
					if (cal.equals(today)) {
						img = highlight;
						col = Color.white;
					}
					g.drawImage(img, d * 30 + 46, week * 29 + 81, null);
					g.drawString(day.format(cal.getTime()), d * 30 + 46 + 4,
							week * 29 + 81 + 20);
				}
				cal.add(Calendar.DATE, +1);
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarHack ch = new CalendarHack();
		ch.setDate(new Date());
		frame.getContentPane().add(ch);
		frame.setUndecorated(true);

		MoveMouseListener mml = new MoveMouseListener(ch);
		ch.addMouseListener(mml);
		ch.addMouseMotionListener(mml);

		frame.pack();
		frame.setVisible(true);
	}
}

class MoveMouseListener implements MouseListener, MouseMotionListener {
	JComponent target;

	Point start_drag;

	Point start_loc;

	public MoveMouseListener(JComponent target) {
		this.target = target;
	}

	public static JFrame getFrame(Container target) {
		if (target instanceof JFrame) {
			return (JFrame) target;
		}
		return getFrame(target.getParent());
	}

	Point getScreenLocation(MouseEvent e) {
		Point cursor = e.getPoint();
		Point target_location = this.target.getLocationOnScreen();
		return new Point((int) (target_location.getX() + cursor.getX()),
				(int) (target_location.getY() + cursor.getY()));
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		this.start_drag = this.getScreenLocation(e);
		this.start_loc = this.getFrame(this.target).getLocation();
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		Point current = this.getScreenLocation(e);
		Point offset = new Point(
				(int) current.getX() - (int) start_drag.getX(),
				(int) current.getY() - (int) start_drag.getY());
		JFrame frame = this.getFrame(target);
		Point new_location = new Point(
				(int) (this.start_loc.getX() + offset.getX()),
				(int) (this.start_loc.getY() + offset.getY()));
		frame.setLocation(new_location);
	}

	public void mouseMoved(MouseEvent e) {
	}
}

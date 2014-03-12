package Motorensteuerung;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.event.SwingPropertyChangeSupport;

public class Model {
	private int speed = 0;
	private final PropertyChangeSupport propertyChangeSupport = new SwingPropertyChangeSupport(
			this);

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int newValue) {
		int oldValue = speed;
		speed = newValue;

		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this,
				"speed", oldValue, newValue));
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
}
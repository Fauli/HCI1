package changelistener;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EngineModelImpl implements EngineModel {
    private int speed = 0;
    private final SwingPropertyChangeSupport propertyChangeSupport = new SwingPropertyChangeSupport(this);

    @Override
    public void increaseSpeed() {
        int oldValue = speed;
        speed += 1;

        if (speed > 10) {
            throw new IllegalStateException("Cannot go faster than 10.");
        }

        propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "speed", oldValue, speed));
    }

    @Override
    public void decreaseSpeed() {
        int oldValue = speed;
        speed -= 1;

        if (speed < 0) {
            throw new IllegalStateException("Cannot go slower than 0.");
        }

        propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "speed", oldValue, speed));
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        int oldValue = this.speed;
        this.speed = speed;
        propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "speed", oldValue, speed));
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}

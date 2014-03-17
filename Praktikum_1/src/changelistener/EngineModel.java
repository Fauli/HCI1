package changelistener;

import java.beans.PropertyChangeListener;

public interface EngineModel {
    void increaseSpeed();

    void decreaseSpeed();

    int getSpeed();

    void setSpeed(int speed);

    void addPropertyChangeListener(PropertyChangeListener listener);
}

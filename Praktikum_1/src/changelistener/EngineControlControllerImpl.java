package changelistener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EngineControlControllerImpl implements EngineControlController {

    private final EngineModel engineModel;
    private final EngineControlView engineControlView;

    public EngineControlControllerImpl(EngineModelImpl engineModel) {
        this.engineModel = engineModel;
        this.engineControlView = new EngineControlViewImpl(this, engineModel);

        engineModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (!(evt.getSource() instanceof EngineModel) || !evt.getPropertyName().equals("speed")) {
                    return;
                }

                int speed = (Integer)evt.getNewValue();

                if (speed > 9) {
                    engineControlView.disableIncreaseAction();
                } else if (!engineControlView.increaseActionEnabled()) {
                    engineControlView.enableIncreaseAction();
                }

                if (speed < 1) {
                    engineControlView.disableDecreaseAction();
                } else if (!engineControlView.decreaseActionEnabled()) {
                    engineControlView.enableDecreaseAction();
                }
            }
        });

        engineControlView.setEngineSpeed(engineModel.getSpeed());
        engineControlView.disableDecreaseAction();
        engineControlView.show();
    }

    @Override
    public void decreaseEngineSpeed() {
        int speed = engineModel.getSpeed();

        if (speed > 0) {
            engineModel.decreaseSpeed();
        }
    }

    @Override
    public void increaseEngineSpeed() {
        int speed = engineModel.getSpeed();

        if (speed < 10) {
            engineModel.increaseSpeed();
        }
    }
}

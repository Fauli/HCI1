package changelistener;

public interface EngineControlView {
    void show();

    void setEngineSpeed(int speed);

    void disableDecreaseAction();

    void enableDecreaseAction();

    boolean decreaseActionEnabled();

    void disableIncreaseAction();

    void enableIncreaseAction();

    boolean increaseActionEnabled();
}

package changelistener;

import java.awt.*;

public class EngineControl {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EngineModelImpl model = new EngineModelImpl();
                    new EngineControlControllerImpl(model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

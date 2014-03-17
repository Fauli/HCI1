package changelistener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EngineControlViewImpl implements EngineControlView {

    private enum UserAction {
        SLOWER, FASTER
    }

    private final EngineControlController controller;
    private final EngineModel model;
    private final JPanel contentPane;
    private final JLabel speedDisplay;
    private final SlowerAction slowerAction;
    private final FasterAction fasterAction;

    public EngineControlViewImpl(EngineControlController controller, EngineModel model) {
        this.controller = controller;
        this.model = model;

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{150, 50, 150, 0};
        gbl_contentPane.rowHeights = new int[]{150, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        speedDisplay = new JLabel();
        speedDisplay.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        GridBagConstraints gbc_speedDisplay = new GridBagConstraints();
        gbc_speedDisplay.insets = new Insets(0, 0, 5, 5);
        gbc_speedDisplay.gridx = 1;
        gbc_speedDisplay.gridy = 0;
        contentPane.add(speedDisplay, gbc_speedDisplay);
        model.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getSource() instanceof EngineModel && evt.getPropertyName().equals("speed")) {
                    speedDisplay.setText(evt.getNewValue().toString());
                }
            }
        });

        slowerAction = new SlowerAction("langsamer");
        JButton slowerButton = new JButton();
        slowerButton.setAction(slowerAction);
        slowerButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), UserAction.SLOWER);
        slowerButton.getActionMap().put(UserAction.SLOWER, slowerAction);
        GridBagConstraints gbc_slowerButton = new GridBagConstraints();
        gbc_slowerButton.insets = new Insets(0, 0, 0, 5);
        gbc_slowerButton.gridx = 0;
        gbc_slowerButton.gridy = 1;
        contentPane.add(slowerButton, gbc_slowerButton);

        fasterAction = new FasterAction("schneller");
        JButton fasterButton = new JButton();
        fasterButton.setAction(fasterAction);
        fasterButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UserAction.FASTER);
        fasterButton.getActionMap().put(UserAction.FASTER, fasterAction);
        GridBagConstraints gbc_fasterButton = new GridBagConstraints();
        gbc_fasterButton.gridx = 2;
        gbc_fasterButton.gridy = 1;
        contentPane.add(fasterButton, gbc_fasterButton);
    }

    @Override
    public void show() {
        JFrame frame = new JFrame();
        frame.setTitle("Motorsteuerung");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 360, 220);
        frame.setMinimumSize(new Dimension(360, 220));
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void setEngineSpeed(int speed) {
        speedDisplay.setText(String.valueOf(speed));
    }

    @Override
    public void disableDecreaseAction() {
        slowerAction.setEnabled(false);
    }

    @Override
    public void enableDecreaseAction() {
        slowerAction.setEnabled(true);
    }

    @Override
    public boolean decreaseActionEnabled() {
        return slowerAction.isEnabled();
    }

    @Override
    public void disableIncreaseAction() {
        fasterAction.setEnabled(false);
    }

    @Override
    public void enableIncreaseAction() {
        fasterAction.setEnabled(true);
    }

    @Override
    public boolean increaseActionEnabled() {
        return fasterAction.isEnabled();
    }

    private class SlowerAction extends AbstractAction {

        private SlowerAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.decreaseEngineSpeed();
        }
    }

    private class FasterAction extends AbstractAction {

        private FasterAction(String name) {
            super(name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.increaseEngineSpeed();
        }
    }
}

package view.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;


import controller.Controller;


/**
 * Dialog das die Kontaktinformationen anzeigt.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class KontaktInfos extends JDialog implements ActionListener, KeyListener
{

    private JLabel staticContact, staticRights, staticProblem;
    private JButton okButton;
    private MainWindow gui;


    public KontaktInfos(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleKontaktInfos").toString(), true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
    }


    private void setDialogInfos()
    {
        setLabelAndPanelInfos();
        setInformation(this);
        this.pack();
        setResizable(false);
        setLocationRelativeTo(gui);
        setVisible(true);
    }


    /**
     * Hier werden die Dialoginformationen gestellt.
     */
    private void setLabelAndPanelInfos()
    {
        staticContact = new JLabel(Controller.stats.getObject("konaktQuestion").toString());
        staticProblem = new JLabel(Controller.stats.getObject("kontaktBugs").toString());
        staticRights = new JLabel(Controller.stats.getObject("kontaktCopyright").toString());
        okButton = new JButton(Controller.stats.getObject("buttonOk").toString());
        okButton.addActionListener(this);
        okButton.addKeyListener(this);
    }


    /**
     * Hier werden die Elemente positioniert.
     * 
     * @param dialog
     */
    public void setInformation(KontaktInfos dialog)
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        // Spalte 1
        gridBag.gridy = 0;
        gridBag.gridy = 0;
        dialog.add(staticContact, gridBag);
        // Spalte 2
        gridBag.gridy = 0;
        gridBag.gridy = 1;
        dialog.add(staticProblem, gridBag);
        // Spalte 3
        gridBag.gridy = 0;
        gridBag.gridy = 2;
        dialog.add(staticRights, gridBag);
        gridBag.gridy = 2;
        gridBag.anchor = GridBagConstraints.EAST;
        dialog.add(okButton, gridBag);
    }


    public void actionPerformed(ActionEvent e)
    {
        this.setVisible(false);
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            okButton.doClick();
        }
    }


    public void keyReleased(KeyEvent e)
    {
        // inti
    }


    public void keyTyped(KeyEvent e)
    {
        // inti
    }
}
package view.serie;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.main.MainWindow;

import controller.Controller;


/**
 * Dialog zum Hinzufügen eines neuen Genres oder neuen Ort, oder einer neuen Sprache.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class NewEditInfos extends JDialog implements ActionListener, KeyListener
{

    private JTextField infos;
    private String metaInfo;
    private JLabel newInfosTextField;
    private JButton okButton, cancelButton;
    private JPanel buttonPanel, newInfosPanel, allPanels;
    private MainWindow gui;
    private GridBagConstraints gridBag;


    public NewEditInfos(MainWindow gui, String titel)
    {
        super(gui, Controller.stats.getObject("titleNewEditInfos").toString() + titel, true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        metaInfo = titel;
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
        setVisible(true);
    }


    private void setDialogInfos()
    {
        setLabelAndPanelInfos();
        setInformationPanel();
        setButtonPanel();
        setButtonPanel();
        setAllPanel();
        this.add(allPanels);
        this.pack();
        setResizable(false);
        setLocationRelativeTo(gui);
    }


    /**
     * Hier werden die Dialoginformationen gestellt.
     */
    private void setLabelAndPanelInfos()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        newInfosPanel = new JPanel();
        newInfosPanel.setLayout(new GridBagLayout());
        allPanels = new JPanel();
        allPanels.setLayout(new GridBagLayout());
        infos = new JTextField(20);
        infos.addKeyListener(this);
        if (metaInfo == Controller.stats.getObject("language").toString())
        {
            newInfosTextField = new JLabel(Controller.stats.getObject("newFeminin").toString() + metaInfo + ": ");
        }
        else
            newInfosTextField = new JLabel(Controller.stats.getObject("newMasculin").toString() + metaInfo + ": ");
        okButton = new JButton(Controller.stats.getObject("buttonAdd").toString());
        okButton.addActionListener(this);
        okButton.addKeyListener(this);
        cancelButton = new JButton(Controller.stats.getObject("buttonCancel").toString());
        cancelButton.addActionListener(this);
        cancelButton.addKeyListener(this);
    }


    public void setInformationPanel()
    {
        gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        newInfosPanel.add(newInfosTextField, gridBag);
        gridBag.gridx = 1;
        newInfosPanel.add(infos, gridBag);
    }


    public void setButtonPanel()
    {
        gridBag = new GridBagConstraints();
        // Buttons
        gridBag.gridy = 0;
        gridBag.gridx = 1;
        gridBag.insets.set(3, 3, 0, 0);
        buttonPanel.add(okButton, gridBag);
        gridBag.gridx = 2;
        buttonPanel.add(cancelButton, gridBag);
    }


    public void setAllPanel()
    {
        gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(3, 3, 0, 0);
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        allPanels.add(newInfosPanel, gridBag);
        gridBag.insets.set(0, 3, 3, 3);
        gridBag.gridy = 1;
        gridBag.anchor = GridBagConstraints.EAST;
        allPanels.add(buttonPanel, gridBag);
    }


    public void actionPerformed(ActionEvent e)
    {
        String newInfo = infos.getText();
        if (e.getSource().equals(okButton) && metaInfo.equals(MainWindow.STRING_LANGUAGE))
        {
            gui.getController().getSerien().setLangaugeListe(newInfo);
            gui.refreshLanguageBox(newInfo);
            gui.getNewLanguageLabel().setText(newInfo);
        }
        else if (e.getSource().equals(okButton) && metaInfo.equals(MainWindow.STRING_GENRE))
        {
            gui.getController().getSerien().setGenreListe(newInfo);
            gui.refreshGenreBox(newInfo);
            gui.getNewGenreLabel().setText(newInfo);
        }
        this.setVisible(false);
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource().equals(cancelButton)
                || e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            cancelButton.doClick();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER
                && (e.getSource().equals(okButton) || e.getSource().equals(newInfosTextField)))
        {
            okButton.doClick();
        }
    }


    public void keyReleased(KeyEvent e)
    {
    }


    public void keyTyped(KeyEvent e)
    {
    }
}
package view.db;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import view.main.MainWindow;

import controller.Controller;
import controller.ListerProperties;


/**
 * Dialogfenster das den User fragt welches File importiert werden soll und ob das Importieren gespeichert werden soll
 * oder nur angezeigt werden soll.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class ImportDialog extends JDialog implements ActionListener, KeyListener
{

    private JFileChooser fileChooser;
    private JRadioButton overwriteRadioButton, ansichtRadioButton, tempCheckBox;
    private ButtonGroup buttonGroup;
    private JButton importButton, cancelButton, directoryButton, downloadButton, tempButton;
    private JPanel buttonPanel, importPanel, allPanels;
    private MainWindow gui;
    private GridBagConstraints gridBag;
    private JTextField directoryTextField;
    private SocketImport socketImportDialog = null;


    public ImportDialog(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleImportDialog").toString(), true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
    }


    private void setDialogInfos()
    {
        setLabelAndPanelInfos();
        setInformation();
        setButtonPanel();
        setAllPanel();
        this.add(allPanels);
        this.pack();
        setResizable(false);
        setLocationRelativeTo(gui);
        setVisible(true);
    }


    public void reactivate(String type)
    {
        this.setTitle(type);
        this.setVisible(true);
    }


    private void setLabelAndPanelInfos()
    {
        // Initialisiere
        directoryTextField = new JTextField(30);
        overwriteRadioButton = new JRadioButton(Controller.stats.getObject("newDatabaseImport").toString());
        ansichtRadioButton = new JRadioButton(Controller.stats.getObject("onlyLook").toString());
        buttonGroup = new ButtonGroup();
        buttonGroup.add(overwriteRadioButton);
        buttonGroup.add(ansichtRadioButton);
        overwriteRadioButton.setSelected(true);
        directoryButton = new JButton("...");
        downloadButton = new JButton(Controller.stats.getObject("buttonFileDownload").toString());
        importButton = new JButton(Controller.stats.getObject("buttonImport").toString());
        cancelButton = new JButton(Controller.stats.getObject("buttonCancel").toString());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        importPanel = new JPanel();
        importPanel.setLayout(new GridBagLayout());
        allPanels = new JPanel();
        allPanels.setLayout(new GridBagLayout());
        // add KeyListener
        directoryTextField.addKeyListener(this);
        overwriteRadioButton.addKeyListener(this);
        ansichtRadioButton.addKeyListener(this);
        importButton.addKeyListener(this);
        cancelButton.addKeyListener(this);
        directoryButton.addKeyListener(this);
        downloadButton.addKeyListener(this);
        // add ActionListener
        directoryButton.addActionListener(this);
        importButton.addActionListener(this);
        cancelButton.addActionListener(this);
        downloadButton.addActionListener(this);
    }


    public void setInformation()
    {
        int zeile = 0;
        gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(2, 2, 0, 0);
        gridBag.gridwidth = 2;
        gridBag.gridx = 0;
        gridBag.gridy = zeile;
        importPanel.add(new JLabel(Controller.stats.getObject("fileSelect").toString()), gridBag);
        gridBag.gridwidth = 1;
        // FileChooser
        gridBag.gridy = ++zeile;
        importPanel.add(directoryTextField, gridBag);
        gridBag.gridx = 1;
        importPanel.add(directoryButton, gridBag);
        gridBag.gridx = 0;
        // DownloadButton
        gridBag.gridy = ++zeile;
        importPanel.add(downloadButton, gridBag);
        // CheckBix
        gridBag.gridy = ++zeile;
        importPanel.add(overwriteRadioButton, gridBag);
        gridBag.gridy = ++zeile;
        importPanel.add(ansichtRadioButton, gridBag);
    }


    public void setButtonPanel()
    {
        gridBag = new GridBagConstraints();
        // Buttons
        gridBag.gridy = 0;
        gridBag.gridx = 1;
        gridBag.insets.set(3, 3, 0, 0);
        buttonPanel.add(importButton, gridBag);
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
        allPanels.add(importPanel, gridBag);
        gridBag.insets.set(0, 3, 3, 3);
        gridBag.gridy = 1;
        gridBag.anchor = GridBagConstraints.EAST;
        allPanels.add(buttonPanel, gridBag);
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(directoryButton))
        {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(""));
            fileChooser.showOpenDialog(this);
            if (fileChooser.getSelectedFile() != null)
            {
                directoryTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
        if (e.getSource().equals(importButton))
        {
            if (gui.getController().getSerien().loadAllSeries(directoryTextField.getText()))
            {
                gui.refreshBoxes();
                if (gui.getDisEnable().isVisible())
                {
                    gui.getDisEnable().doClick();
                }
                gui.getController().fillSerienList(false);
                if (overwriteRadioButton.isSelected())
                {
                    gui.getController().getSerien().save(ListerProperties.getDefaultProperties().getProperty("pfad"));
                    gui.getController().getSerien().save();
                }
                else
                {
                    gui.getDisEnable().setVisible(true);
                    gui.disEnableAll();
                }
                this.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, Controller.stats.getObject("iDWimportWarningMsg").toString(),
                        Controller.stats.getObject("iDWimportWarning").toString(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getSource().equals(cancelButton))
        {
            this.setVisible(false);
        }
        else if (e.getSource().equals(downloadButton))
        {
            if (socketImportDialog == null)
            {
                socketImportDialog = new SocketImport(gui);
                if (socketImportDialog.getTransferStatus().getText().equals(
                        Controller.stats.getObject("fileLoaded").toString()))
                {
                    File file = new File("");
                    directoryTextField.setText(file.getAbsolutePath() + "\\import.txt");
                }
            }
            else
            {
                socketImportDialog.reactivate();
            }
        }
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER
                && !(e.getSource().equals(overwriteRadioButton) || e.getSource().equals(ansichtRadioButton)))
        {
            if (!e.getSource().equals(directoryTextField))
            {
                try
                {
                    tempButton = (JButton) e.getSource();
                    tempButton.doClick();
                }
                catch (ClassCastException cce)
                {
                    cce.printStackTrace();
                }
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            cancelButton.doClick();
        }
        else if ((e.getSource().equals(overwriteRadioButton) || e.getSource().equals(ansichtRadioButton))
                && e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try
            {
                tempCheckBox = (JRadioButton) e.getSource();
                tempCheckBox.setSelected(!tempCheckBox.isSelected());
            }
            catch (ClassCastException cce)
            {
                cce.printStackTrace();
            }
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
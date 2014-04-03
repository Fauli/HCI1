package view.db;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.main.MainWindow;

import controller.Controller;
import controller.ExportHelper;


/**
 * Dialogfeld mit Abfragen was für Informationen alles exportiert werden sollen.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class ExportDialog extends JDialog implements ActionListener, KeyListener, ItemListener
{

    private JCheckBox allCheckBox, titleCheckBox, episodesCheckBox, completeCheckBox, languageCheckBox, genreCheckBox,
            placeCheckBox, tempBox;
    private JFileChooser fileChooser;
    private JLabel directoryLabel, fileNameLabel, fileNameDetail;
    private JButton exportButton, cancelButton, directoryButton;
    private MainWindow gui;
    private ExportHelper export;
    private JTextField directoryTextField, filenameTextField;
    private JPanel buttonPanel, exportPanel, allPanels, exportInfosPanel, textFieldPanel;
    private GridBagConstraints gridBag;


    public ExportDialog(MainWindow gui, String type)
    {
        super(gui, type, true);
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


    /**
     * Hier werden die Dialoginformationen gestellt.
     */
    private void setLabelAndPanelInfos()
    {
        // Initialisiere
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        exportPanel = new JPanel();
        exportPanel.setLayout(new GridBagLayout());
        allPanels = new JPanel();
        allPanels.setLayout(new GridBagLayout());
        exportInfosPanel = new JPanel();
        exportInfosPanel.setLayout(new GridBagLayout());
        textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());
        allCheckBox = new JCheckBox(Controller.stats.getObject("all").toString());
        titleCheckBox = new JCheckBox(Controller.stats.getObject("title").toString());
        titleCheckBox.setEnabled(false);
        directoryLabel = new JLabel(Controller.stats.getObject("directoryLabel").toString());
        fileNameLabel = new JLabel(Controller.stats.getObject("fileName").toString());
        fileNameDetail = new JLabel(Controller.stats.getObject("defaultExport").toString());
        episodesCheckBox = new JCheckBox(Controller.stats.getObject("episodeExisting").toString());
        completeCheckBox = new JCheckBox(Controller.stats.getObject("exportEpisodesTotal").toString());
        languageCheckBox = new JCheckBox(Controller.stats.getObject("language").toString());
        genreCheckBox = new JCheckBox(Controller.stats.getObject("genre").toString());
        placeCheckBox = new JCheckBox(Controller.stats.getObject("place").toString());
        exportButton = new JButton(Controller.stats.getObject("export").toString());
        cancelButton = new JButton(Controller.stats.getObject("buttonCancel").toString());
        directoryButton = new JButton("...");
        directoryTextField = new JTextField(16);
        filenameTextField = new JTextField(16);
        // add KeyListener
        allCheckBox.addKeyListener(this);
        titleCheckBox.addKeyListener(this);
        episodesCheckBox.addKeyListener(this);
        completeCheckBox.addKeyListener(this);
        languageCheckBox.addKeyListener(this);
        genreCheckBox.addKeyListener(this);
        placeCheckBox.addKeyListener(this);
        exportButton.addKeyListener(this);
        cancelButton.addKeyListener(this);
        directoryButton.addKeyListener(this);
        // add ItemListener
        titleCheckBox.addItemListener(this);
        episodesCheckBox.addItemListener(this);
        completeCheckBox.addItemListener(this);
        languageCheckBox.addItemListener(this);
        genreCheckBox.addItemListener(this);
        placeCheckBox.addItemListener(this);
        allCheckBox.addItemListener(this);
        // SetSelected
        titleCheckBox.setSelected(true);
        episodesCheckBox.setSelected(true);
        completeCheckBox.setSelected(true);
        languageCheckBox.setSelected(true);
        genreCheckBox.setSelected(true);
        placeCheckBox.setSelected(true);
        allCheckBox.setSelected(true);
        // Add ActionLister
        directoryButton.addActionListener(this);
        exportButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }


    private void setExportInfos()
    {
        int zeile = 0;
        gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(2, 2, 0, 0);
        gridBag.gridwidth = 3;
        gridBag.gridx = 0;
        gridBag.gridy = zeile;
        exportInfosPanel.add(new JLabel("Informationen zum exportieren"), gridBag);
        // Checkboxen
        gridBag.gridwidth = 4;
        gridBag.gridy = ++zeile;
        exportInfosPanel.add(allCheckBox, gridBag);
        gridBag.insets.set(1, 15, 0, 0);
        gridBag.gridy = ++zeile;
        exportInfosPanel.add(titleCheckBox, gridBag);
        gridBag.gridy = ++zeile;
        exportInfosPanel.add(episodesCheckBox, gridBag);
        gridBag.gridy = ++zeile;
        exportInfosPanel.add(completeCheckBox, gridBag);
        gridBag.gridy = ++zeile;
        exportInfosPanel.add(languageCheckBox, gridBag);
        gridBag.gridy = ++zeile;
        exportInfosPanel.add(genreCheckBox, gridBag);
        gridBag.gridy = ++zeile;
        exportInfosPanel.add(placeCheckBox, gridBag);
    }


    private void setTextFieldInfos()
    {
        int zeile = 0;
        gridBag.insets.set(1, 2, 0, 0);
        gridBag.gridwidth = 2;
        gridBag.gridy = zeile;
        textFieldPanel.add(directoryLabel, gridBag);
        gridBag.gridwidth = 1;
        gridBag.gridx = 2;
        textFieldPanel.add(directoryTextField, gridBag);
        gridBag.gridwidth = 1;
        gridBag.gridx = 3;
        textFieldPanel.add(directoryButton, gridBag);
        // Filename
        // gridBag.gridwidth = 2;
        gridBag.gridy = ++zeile;
        gridBag.gridx = 0;
        textFieldPanel.add(fileNameLabel, gridBag);
        gridBag.gridx = 2;
        textFieldPanel.add(filenameTextField, gridBag);
        gridBag.gridx = 3;
        textFieldPanel.add(fileNameDetail, gridBag);
    }


    /**
     * Hier werden die Elemente positioniert.
     * 
     * @param dialog
     */
    private void setInformation()
    {
        gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        setExportInfos();
        setTextFieldInfos();
        gridBag.gridy = 0;
        exportPanel.add(exportInfosPanel, gridBag);
        gridBag.gridy = 1;
        exportPanel.add(textFieldPanel, gridBag);
    }


    public void setButtonPanel()
    {
        gridBag = new GridBagConstraints();
        // Buttons
        gridBag.gridy = 0;
        gridBag.gridx = 1;
        gridBag.insets.set(3, 3, 0, 0);
        buttonPanel.add(exportButton, gridBag);
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
        gridBag.gridwidth = 2;
        gridBag.anchor = GridBagConstraints.EAST;
        allPanels.add(exportPanel, gridBag);
        gridBag.insets.set(3, 3, 3, 3);
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridx = 1;
        allPanels.add(buttonPanel, gridBag);
    }


    /**
     * Setzt die Arrayinhalte je nach Selektion der Checkboxen true oder false.
     * 
     * @return booleanArray
     */
    private boolean[] selection()
    {
        boolean[] selected = new boolean[6];
        if (allCheckBox.isSelected())
        {
            selected[0] = true;
            selected[1] = true;
            selected[2] = true;
            selected[3] = true;
            selected[4] = true;
            selected[5] = true;
        }
        else
        {
            selected[0] = titleCheckBox.isSelected();
            selected[1] = episodesCheckBox.isSelected();
            selected[2] = completeCheckBox.isSelected();
            selected[3] = languageCheckBox.isSelected();
            selected[4] = genreCheckBox.isSelected();
            selected[5] = placeCheckBox.isSelected();
        }
        return selected;
    }


    private void enDisableCheckboxes(boolean enabled)
    {
        episodesCheckBox.setEnabled(enabled);
        completeCheckBox.setEnabled(enabled);
        languageCheckBox.setEnabled(enabled);
        genreCheckBox.setEnabled(enabled);
        placeCheckBox.setEnabled(enabled);
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(directoryButton))
        {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:/"));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(this);
            if (fileChooser.getSelectedFile() != null)
            {
                directoryTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
        else if (e.getSource().equals(exportButton))
        {
            if (export == null)
            {
                export = new ExportHelper();
            }
            if (filenameTextField.getText().equals(""))
            {
                filenameTextField.setText("export");
            }
            export.makeExport(this.getTitle(), gui, selection(), directoryTextField.getText() + "/"
                    + filenameTextField.getText() + ".txt");
            this.setVisible(false);
        }
        else if (e.getSource().equals(cancelButton))
        {
            this.setVisible(false);
        }
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (e.getSource().equals(exportButton))
            {
                exportButton.doClick();
            }
            else if (e.getSource().equals(cancelButton))
            {
                cancelButton.doClick();
            }
            else if (e.getSource().equals(directoryButton))
            {
                directoryButton.doClick();
            }
            else
            {
                try
                {
                    tempBox = (JCheckBox) e.getSource();
                    tempBox.setSelected(!tempBox.isSelected());
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
    }


    public void keyReleased(KeyEvent e)
    {
        // inti
    }


    public void keyTyped(KeyEvent e)
    {
        // inti
    }


    public void itemStateChanged(ItemEvent itemEvent)
    {
        if (itemEvent.getSource().equals(allCheckBox))
        {
            if (allCheckBox.isSelected())
            {
                enDisableCheckboxes(false);
            }
            else
            {
                enDisableCheckboxes(true);
            }
        }
    }
}
package view.helper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.main.MainWindow;

import controller.Controller;
import controller.ListerProperties;


/**
 * Creates the Dialog for the Settings.
 * 
 */
@SuppressWarnings("serial")
public class SettingsDialog extends JDialog implements ActionListener, KeyListener
{

    private JFileChooser fileChooser;
    private JPanel einstellungenPanel, buttonPanel;
    private JLabel importFileLabel, pathLabel, languageLabel, designLabel;
    private JTextField importFileTextField, pathTextField, beschreibungTextField;
    private JButton pathFileChooseButton, importFileChooseButton, okButton, cancelButton;
    private JComboBox comboSprache, comboDesign;
    private UIManager.LookAndFeelInfo currentLAF;
    private UIManager.LookAndFeelInfo[] designs = new UIManager.LookAndFeelInfo[UIManager.getInstalledLookAndFeels().length];
    private Locale[] sprache = new Locale[ListerProperties.getLanguageProperties().size() / 2];
    private MainWindow gui;


    public SettingsDialog(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleSettingDialog").toString(), true);
        this.gui = gui;
        getContentPane().setLayout(new BorderLayout());
        doHinzufuegen();
    }


    private void setLanguageBox()
    {
        for (int country, language = 2, anzahl = 0; language <= ListerProperties.getLanguageProperties().size(); language += 2, anzahl++)
        {
            country = language - 1;
            Locale localeToComboBox = new Locale(ListerProperties.getLanguageProperties()
                    .get(Integer.toString(country)).toString(), ListerProperties.getLanguageProperties().get(
                    Integer.toString(language)).toString());
            sprache[anzahl] = localeToComboBox;
        }
    }


    private void getAllLAF()
    {
        String tempUIClassname = UIManager.getLookAndFeel().getClass().toString().replace("class ", "");
        for (int index = 0; index < UIManager.getInstalledLookAndFeels().length; index++)
        {
            designs[index] = UIManager.getInstalledLookAndFeels()[index];
            if (tempUIClassname.equals(UIManager.getInstalledLookAndFeels()[index].getClassName().toString()))
            {
                currentLAF = UIManager.getInstalledLookAndFeels()[index];
            }
        }
    }


    private void doHinzufuegen()
    {
        einstellungenPanel = new JPanel();
        einstellungenPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag = setGridBag();
        getAllLAF();
        setLanguageBox();
        setDialog();
        setEinstellungenPanel(gridBag);
        setButtonPanel();
        setKeyListerner();
        getContentPane().add(einstellungenPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }


    /**
     * Sets the GridBagConstraints for the first use.
     * 
     * @return GridbagConstraints ready for the first use
     */
    private GridBagConstraints setGridBag()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.weightx = 0;
        gridBag.weighty = 0;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.left = 2;
        gridBag.insets.right = 2;
        gridBag.insets.bottom = 2;
        gridBag.insets.top = 2;
        return gridBag;
    }


    private void setDialog()
    {
        pathLabel = new JLabel(Controller.stats.getObject("directoryLabel").toString());
        importFileLabel = new JLabel(Controller.stats.getObject("importLabel").toString());
        pathTextField = new JTextField(ListerProperties.getDefaultProperties().getProperty("pfad"), 25);
        pathTextField.setEditable(false);
        importFileTextField = new JTextField(ListerProperties.getDefaultProperties().getProperty("import"), 25);
        importFileTextField.setEditable(false);
        languageLabel = new JLabel(Controller.stats.getObject("languageLabel").toString());
        designLabel = new JLabel(Controller.stats.getObject("designLabel").toString());
        beschreibungTextField = new JTextField(25);
        beschreibungTextField.setText(Controller.stats.getObject("settingText").toString());
        beschreibungTextField.setEnabled(false);
        okButton = new JButton(Controller.stats.getObject("buttonOk").toString());
        cancelButton = new JButton(Controller.stats.getObject("buttonCancel").toString());
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        importFileChooseButton = new JButton("...");
        importFileChooseButton.setPreferredSize(new Dimension(20, 20));
        importFileChooseButton.addActionListener(this);
        pathFileChooseButton = new JButton("...");
        pathFileChooseButton.setPreferredSize(new Dimension(20, 20));
        pathFileChooseButton.addActionListener(this);
        comboSprache = new JComboBox(sprache);
        comboSprache.setRenderer(new LanguageRenderer());
        comboSprache.setSelectedItem(ListerProperties.getLocale());
        
        comboDesign = new JComboBox(designs);
        comboDesign.setRenderer(new LookAndFeelRenderer());
        comboDesign.setSelectedItem(currentLAF);
        int width = (int) comboSprache.getPreferredSize().getWidth();
        if (width < comboDesign.getPreferredSize().getWidth())
        {
            width = (int) comboDesign.getPreferredSize().getWidth();
        }
        Dimension boxDimension = new Dimension(width, (int) comboSprache.getPreferredSize().getHeight());
        comboSprache.setPreferredSize(boxDimension);
        comboDesign.setPreferredSize(boxDimension);
    }


    private void setEinstellungenPanel(GridBagConstraints gridBag)
    {
        einstellungenPanel.add(pathLabel, gridBag);
        gridBag.gridx = 1;
        einstellungenPanel.add(pathTextField, gridBag);
        gridBag.gridx = 2;
        einstellungenPanel.add(pathFileChooseButton, gridBag);
        gridBag.gridy = 1;
        gridBag.gridx = 0;
        einstellungenPanel.add(importFileLabel, gridBag);
        gridBag.gridx = 1;
        einstellungenPanel.add(importFileTextField, gridBag);
        gridBag.gridx = 2;
        einstellungenPanel.add(importFileChooseButton, gridBag);
        
        gridBag.gridy = 2;
        gridBag.gridx = 0;
        einstellungenPanel.add(languageLabel, gridBag);
        gridBag.gridx = 1;
        einstellungenPanel.add(comboSprache, gridBag);
        gridBag.gridy = 3;
        gridBag.gridx = 0;
        einstellungenPanel.add(designLabel, gridBag);
        gridBag.gridx = 1;
        einstellungenPanel.add(comboDesign, gridBag);
        gridBag.gridy = 4;
        gridBag.gridx = 0;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.anchor = GridBagConstraints.CENTER;
        beschreibungTextField.setHorizontalAlignment(SwingConstants.CENTER);
        einstellungenPanel.add(beschreibungTextField, gridBag);
    }


    private void setButtonPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
    }


    private void setKeyListerner()
    {
        okButton.addKeyListener(this);
        cancelButton.addKeyListener(this);
        pathTextField.addKeyListener(this);
        importFileTextField.addKeyListener(this);
        comboDesign.addKeyListener(this);
        comboSprache.addKeyListener(this);
    }


    public void reactivate()
    {
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e)
    {

    	if(e.getSource().equals(importFileChooseButton) || e.getSource().equals(pathFileChooseButton))
    	{

            fileChooser = new JFileChooser();
        	if(e.getSource().equals(importFileChooseButton))
        	{
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setCurrentDirectory(new File(importFileTextField.getText()));
        	}
        	else if (e.getSource().equals(pathFileChooseButton))
        	{
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooser.setCurrentDirectory(new File(pathTextField.getText()));
        	}
            fileChooser.showOpenDialog(this);
            if (fileChooser.getSelectedFile() != null)
            {
            	if(e.getSource().equals(importFileChooseButton))
            	{
            		importFileTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            	}
            	else if (e.getSource().equals(pathFileChooseButton))
            	{
            		pathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            	}
            }
    	}
        if (e.getSource().equals(cancelButton))
        {
            dispose();
        }
        else if (e.getSource().equals(okButton))
        {
            UIManager.LookAndFeelInfo lookAndFeelToSave = (LookAndFeelInfo) comboDesign.getSelectedItem();
            Locale localeToSave = (Locale) comboSprache.getSelectedItem();
            Properties temp = ListerProperties.getDefaultProperties();
            temp.put("design", lookAndFeelToSave.getClassName());
            temp.put("language", localeToSave.getLanguage().toString());
            temp.put("country", localeToSave.getCountry().toString());
            temp.put("import", importFileTextField.getText());
            temp.put("pfad", pathTextField.getText());
            ListerProperties.saveProperties(temp);
            if (!ListerProperties.getLocale().equals(localeToSave))
            {
                JOptionPane.showMessageDialog(null, Controller.stats.getObject("sDWlanguageChangeMsg").toString(),
                        Controller.stats.getObject("sDWlanguageChange").toString(), JOptionPane.INFORMATION_MESSAGE);
            }
            // To have the current Language when the setting Dialog is restarted
            ListerProperties.setCurrentLocale(localeToSave);
            dispose();
            if (!lookAndFeelToSave.getClassName().equalsIgnoreCase(UIManager.getLookAndFeel().getClass().getName()))
            {
                try
                {
                    UIManager.setLookAndFeel(lookAndFeelToSave.getClassName());
                    SwingUtilities.updateComponentTreeUI(gui);
                    gui.pack();
                    if (lookAndFeelToSave.getName().equals("Nimbus"))
                    {
                        gui.setSize(new Dimension(650, 770));
                    }
                    else
                    {
                        gui.setSize(MainWindow.GUI_DIMENSION);
                    }
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (e.getSource().equals(okButton))
            {
                okButton.doClick();
            }
            else if (e.getSource().equals(cancelButton))
            {
                cancelButton.doClick();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            cancelButton.doClick();
        }
    }


    @Override
    public void keyReleased(KeyEvent arg0)
    {
        // init
    }


    @Override
    public void keyTyped(KeyEvent arg0)
    {
        // init
    }
}

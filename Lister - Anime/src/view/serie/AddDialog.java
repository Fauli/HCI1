package view.serie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modell.SerienListManager;
import util.EpisodesHelper;
import util.SerieVO;
import view.helper.GuiHelper;
import view.main.MainWindow;
import controller.Controller;


public class AddDialog extends JDialog implements ActionListener, KeyListener, ChangeListener
{
	private static final long serialVersionUID = 683564463117647503L;
	private JCheckBox completeSerieCheckbox;
    private JTextField titelTextField, episodeTextField;
    private JFormattedTextField totalEpisodeTextField = new JFormattedTextField(NumberFormat.getInstance());
    private JComboBox<String> languageComboBox, genreComboBox;
    private JButton addButton, cancelButton, resetButton, newLanguage, newGenre;
    private MainWindow gui;
    private static final Border NOT_FILLED_BORDER = BorderFactory.createLineBorder(Color.RED);
    private static final Border NORMAL_BORDER = BorderFactory.createEmptyBorder();

    
    public AddDialog(MainWindow gui, SerieVO preparedSerie)
    {
        super(gui, Controller.stats.getObject("titleAddDialog").toString(), true);
        commonAddDialog(gui);
        setDialogInfosBySerienVO(preparedSerie);
        setVisible(true);
    }
    
    public AddDialog(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleAddDialog").toString(), true);
        commonAddDialog(gui);
        setVisible(true);
    }
    
    private void commonAddDialog(MainWindow startgui)
    {
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = startgui;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
    }
    
    private void setDialogInfosBySerienVO(SerieVO serie)
    {
    	titelTextField.setText(serie.getTitle());
    	titelTextField.setBorder(NORMAL_BORDER);
    	if(("1-" + serie.getEpisodesTotal()).equals(serie.getEpisodes()))
    	{
    	    completeSerieCheckbox.setSelected(true);
    	    episodeTextField.setEnabled(false);
    	}
    	else
    	{
    	    episodeTextField.setText(serie.getEpisodes());
    	}
    	totalEpisodeTextField.setText(serie.getEpisodesTotal().toString());
    	totalEpisodeTextField.setBorder(NORMAL_BORDER);
    	languageComboBox.setSelectedItem(serie.getLanguage());
    	genreComboBox.setSelectedItem(serie.getGenre());
    }


    private void setDialogInfos()
    {
        setComponentsInfos();
        createInformationPanel();
        createOverallPanel();
        this.add(createOverallPanel());
        this.pack();
        setResizable(false);
        setLocationRelativeTo(gui);
    }


    private void setComponentsInfos()
    {
        setButtons();
        titelTextField = new JTextField(20);
        titelTextField.setBorder(NOT_FILLED_BORDER);
        episodeTextField = new JTextField(5);
        episodeTextField.setBorder(NOT_FILLED_BORDER);
        totalEpisodeTextField.setColumns(5);
        totalEpisodeTextField.setBorder(NOT_FILLED_BORDER);
        completeSerieCheckbox = new JCheckBox(MainWindow.STRING_COMPLETE);
        completeSerieCheckbox.setHorizontalTextPosition(2);
        completeSerieCheckbox.addChangeListener(this);
        setComboBox();
        addActionListnerToButton();
        addKeyListenerToComponents();
    }


    private void setButtons()
    {
        addButton = new JButton(Controller.stats.getObject("buttonAdd").toString());
        cancelButton = new JButton(Controller.stats.getObject("buttonCancel").toString());
        resetButton = new JButton(Controller.stats.getObject("buttonReset").toString());
        newLanguage = new JButton(MainWindow.ADD_STRING);
        newLanguage.setPreferredSize(new Dimension(20, 20));
        newLanguage.setName(MainWindow.STRING_LANGUAGE);
        newLanguage.setFocusable(false);
        newGenre = new JButton(MainWindow.ADD_STRING);
        newGenre.setPreferredSize(new Dimension(20, 20));
        newGenre.setName(MainWindow.STRING_GENRE);
        newGenre.setFocusable(false);
    }


    private void addActionListnerToButton()
    {
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);
        resetButton.addActionListener(this);
        newLanguage.addActionListener(this);
        newGenre.addActionListener(this);
        languageComboBox.addActionListener(this);
        genreComboBox.addActionListener(this);
    }


    private void addKeyListenerToComponents()
    {
        newLanguage.addKeyListener(this);
        completeSerieCheckbox.addKeyListener(this);
        titelTextField.addKeyListener(this);
        episodeTextField.addKeyListener(this);
        totalEpisodeTextField.addKeyListener(this);
        languageComboBox.addKeyListener(this);
        genreComboBox.addKeyListener(this);
        addButton.addKeyListener(this);
        cancelButton.addKeyListener(this);
        resetButton.addKeyListener(this);
        newGenre.addKeyListener(this);
    }


    private void setComboBox()
    {
        languageComboBox = new JComboBox<String>((String[]) gui.getController().getSerien().getLanguageListe().toArray());
        languageComboBox.setBorder(NOT_FILLED_BORDER);
        genreComboBox = new JComboBox<String>((String[])gui.getController().getSerien().getGenreListe().toArray());
        genreComboBox.setBorder(NOT_FILLED_BORDER);
        resizeBoxes();
    }


    private void resizeBoxes()
    {
        int width = (int) languageComboBox.getPreferredSize().getWidth();
        if (width < genreComboBox.getPreferredSize().getWidth())
        {
            width = (int) genreComboBox.getPreferredSize().getWidth();
        }
        if (width < 70)
        {
            width = 70;
        }
        Dimension boxDimension = new Dimension(width, (int) languageComboBox.getPreferredSize().getHeight());
        languageComboBox.setPreferredSize(boxDimension);
        genreComboBox.setPreferredSize(boxDimension);
    }


    /**
     * Hier werden die Elemente positioniert.
     * 
     * @param dialog
     */
    public JPanel createInformationPanel()
    {
        JPanel informationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        // Zeile 1
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        informationPanel.add(new JLabel(MainWindow.STRING_TITEL), gridBag);
        gridBag.gridx = 1;
        gridBag.gridwidth = 3;
        informationPanel.add(titelTextField, gridBag);
        gridBag.gridwidth = 1;
        gridBag.gridx = 4;
        gridBag.insets.set(0, 3, 0, 0);
        informationPanel.add(new JLabel(MainWindow.STRING_LANGUAGE), gridBag);
        gridBag.insets.set(0, 0, 0, 0);
        gridBag.gridx = 5;
        informationPanel.add(languageComboBox, gridBag);
        gridBag.insets.set(5, 5, 5, 5);
        gridBag.gridx = 6;
        informationPanel.add(newLanguage, gridBag);
        gridBag.insets.set(0, 0, 0, 0);
        // Zeile 2
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        informationPanel.add(new JLabel(Controller.stats.getObject("existing").toString()), gridBag);
        gridBag.gridx = 1;
        informationPanel.add(episodeTextField, gridBag);
        gridBag.gridx = 2;
        gridBag.insets.set(0, 8, 0, 0);
        informationPanel.add(completeSerieCheckbox, gridBag);
        gridBag.gridx = 4;
        gridBag.insets.set(0, 3, 0, 0);
        informationPanel.add(new JLabel(MainWindow.STRING_GENRE), gridBag);
        gridBag.insets.set(0, 0, 0, 0);
        gridBag.gridx = 5;
        informationPanel.add(genreComboBox, gridBag);
        gridBag.insets.set(5, 5, 5, 5);
        gridBag.gridx = 6;
        informationPanel.add(newGenre, gridBag);
        gridBag.insets.set(0, 0, 0, 0);
        // Zeile 3
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        informationPanel.add(new JLabel(Controller.stats.getObject("total").toString()), gridBag);
        gridBag.gridx = 1;
        gridBag.gridwidth = 3;
        informationPanel.add(totalEpisodeTextField, gridBag);
        gridBag.gridwidth = 1;
        gridBag.gridx = 4;
        gridBag.insets.set(0, 3, 0, 0);
        informationPanel.add(new JLabel(MainWindow.STRING_WHERE), gridBag);
        return informationPanel;
    }

    public JPanel createOverallPanel()
    {
        JPanel allPanels = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(3, 3, 0, 0);
        // Buttons
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        gridBag.gridwidth = 2;
        allPanels.add(createInformationPanel(), gridBag);
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.insets.set(3, 3, 3, 3);
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridx = 1;
        allPanels.add(GuiHelper.createPanelAligned(addButton, resetButton, cancelButton), gridBag);
        return allPanels;
    }


    /**
     * Überprüft ob die Daten in der DialogBox zulässig sind.
     * 
     * @return true falls die Werte zuläsig sind.
     */
    private boolean checkData()
    {
        if (titelTextField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, Controller.stats.getObject("aWWTitleMsg").toString(), Controller.stats
                    .getObject("aWWTitle").toString(), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (!EpisodesHelper.checkEpisodeInput(episodeTextField.getText())
                || SerienListManager.existingEpisodes(episodeTextField.getText()) <= 0)
        {
            JOptionPane.showMessageDialog(null, Controller.stats.getObject("aWWEpisodesMsg").toString(),
                    Controller.stats.getObject("aWWEpisodes").toString(), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (totalEpisodeTextField.getText().equals("") 
                || !EpisodesHelper.isNumber(totalEpisodeTextField.getText()))
        {
            JOptionPane.showMessageDialog(null, Controller.stats.getObject("aWWTotalEpisodesMsg").toString(),
                    Controller.stats.getObject("aWWTotalEpisodes").toString(), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (languageComboBox.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, Controller.stats.getObject("aWWLanguageMsg").toString(),
                    Controller.stats.getObject("aWWLanguage").toString(), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (genreComboBox.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, Controller.stats.getObject("aWWGenreMsg").toString(), Controller.stats
                    .getObject("aWWGenre").toString(), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (SerienListManager.existingEpisodes(episodeTextField.getText()) >= new Integer(totalEpisodeTextField.getText()).intValue()
                && !completeSerieCheckbox.isSelected())
        {
            JOptionPane.showMessageDialog(null, Controller.stats.getObject("aWWTooManyEpisMsg").toString(),
                    Controller.stats.getObject("aWWTooManyEpis").toString(), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(addButton))
        {
            if (completeSerieCheckbox.isSelected() && !(totalEpisodeTextField.getText().equals("")))
            {
                if (!(totalEpisodeTextField.getText().trim().equals("1")))
                {
                    episodeTextField.setText("1-" + totalEpisodeTextField.getText());
                }
                else
                {
                    episodeTextField.setText(totalEpisodeTextField.getText());
                }
            }
            if (checkData())
            {
                SerieVO newSerie = new SerieVO();
                newSerie.setTitle(titelTextField.getText());
                newSerie.setEpisoden(episodeTextField.getText());
                newSerie.setEpisodesTotal(new Integer(totalEpisodeTextField.getText()));
                newSerie.setLanguage(languageComboBox.getSelectedItem().toString());
                newSerie.setGenre(genreComboBox.getSelectedItem().toString());
                if (gui.getController().getSerien().addSerie(newSerie))
                {
                    this.setVisible(false);
                }
            }
        }
        else if (e.getSource().equals(cancelButton))
        {
            this.dispose();
//            this.setVisible(false);
        }
        else if (e.getSource().equals(resetButton))
        {
            titelTextField.setText(MainWindow.EMPTY_STRING);
            episodeTextField.setText(MainWindow.EMPTY_STRING);
            totalEpisodeTextField.setText(MainWindow.EMPTY_STRING);
            languageComboBox.setSelectedIndex(0);
            genreComboBox.setSelectedIndex(0);
        }
        else if (e.getSource() instanceof JComboBox)
        {
            JComboBox<String> tempComboBox = (JComboBox<String>) e.getSource();
            if(tempComboBox.getSelectedIndex() < 1)
                tempComboBox.setBorder(NOT_FILLED_BORDER);
            else
                tempComboBox.setBorder(NORMAL_BORDER);
        }
        else
        {
            JButton temp = (JButton) e.getSource();
            new NewEditInfos(gui, temp.getName());
            refreshBoxes();
        }
    }


    private void refreshBoxes()
    {
        int selectedLanguage, selectedGenre;
        selectedLanguage = languageComboBox.getSelectedIndex();
        selectedGenre = genreComboBox.getSelectedIndex();
        languageComboBox.removeAllItems();
        genreComboBox.removeAllItems();
        for (String language : gui.getController().getSerien().getLanguageListe())
        {
            languageComboBox.addItem(language);
        }
        for (String genre : gui.getController().getSerien().getGenreListe())
        {
            genreComboBox.addItem(genre);
        }
        resizeBoxes();
        languageComboBox.setSelectedIndex(selectedLanguage);
        genreComboBox.setSelectedIndex(selectedGenre);
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_F2)
        {
            resetButton.doClick();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (e.getSource().equals(completeSerieCheckbox))
            {
                completeSerieCheckbox.setSelected(!completeSerieCheckbox.isSelected());
            }
            else
            {
                try
                {
                    JButton temp = (JButton) e.getSource();
                    temp.doClick();
                }
                catch (ClassCastException cce)
                {
                    addButton.doClick();
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
        if (e.getSource() instanceof JTextField && !e.getSource().equals(totalEpisodeTextField))
        {
            JTextField tempTextField = (JTextField) e.getSource();
            if (tempTextField.getText().isEmpty())
                tempTextField.setBorder(NOT_FILLED_BORDER);
            else
                tempTextField.setBorder(NORMAL_BORDER);

        }
        if(e.getSource() instanceof JFormattedTextField)
        {
            if (!totalEpisodeTextField.isEditValid())
                totalEpisodeTextField.setBorder(NOT_FILLED_BORDER);
            else
                totalEpisodeTextField.setBorder(NORMAL_BORDER);
        }
    }

    public void keyTyped(KeyEvent e)
    {
        // init
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        if(e.getSource().equals(completeSerieCheckbox))
        {
            episodeTextField.setEnabled(!completeSerieCheckbox.isSelected());
            if(completeSerieCheckbox.isSelected())
                episodeTextField.setBorder(NORMAL_BORDER);
            else if(episodeTextField.getText().isEmpty())
                episodeTextField.setBorder(NOT_FILLED_BORDER);
                    
        }
    }
}
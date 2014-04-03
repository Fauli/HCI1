package view.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.helper.GuiHelper;
import controller.Controller;
import controller.ListerProperties;
import controller.SerienTable;


/**
 * MainGui mit der Übersicht die der User am Anfang hat.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame
{

    public static String EMPTY_STRING = "";
    public static String STRING_TITEL = Controller.stats.getObject("title").toString();
    public static String STRING_GENRE = Controller.stats.getObject("genre").toString();
    public static String STRING_COMPLETE = Controller.stats.getObject("complete").toString();
    public static String STRING_INCOMPLETE = Controller.stats.getObject("incomplete").toString();
    public static String STRING_EPISODEN_COMPLETE = Controller.stats.getObject("episodeTotal").toString();
    public static String STRING_EPISODEN = Controller.stats.getObject("episodeExisting").toString();
    public static String STRING_LANGUAGE = Controller.stats.getObject("language").toString();
    public static String STRING_WHERE = Controller.stats.getObject("where").toString();
    public static String STRING_DELETE = Controller.stats.getObject("delete").toString();
    public static String STRING_AMOUNT_OF_RESULTS = Controller.stats.getObject("searchResults").toString();
    public static String ADD_STRING = "...";
    public static Image APPLICATION_ICON;
    public static Dimension GUI_DIMENSION = new Dimension(550, 700);
    private Controller controller;
    private SerienTable table;
    private JButton addButton, editButton, deleteButton, resetButton;
    private JButton newLanguageButton, newGenreButton;
    private JButton clearLanguage, clearGenre, disEnable;
    private JButton addOneButton, removeOneButton;
    private JCheckBox searchComplete, searchIncomplete, editComplete, searchLent, searchNotLent;
    private JComboBox searchLanguageBox, searchGenreBox, editLanguageBox, editGenreBox;
    private JScrollPane serieLibScrollPane;
    private JTextField searchTiteltf, searchFulltf, editTiteltf, editEpisodetf, editFulltf, editLentTf;
    private JLabel searchTitelLabel, searchFullLabel, searchGenreLabel, searchLanguageLabel, searchResultsLabel;
    private JLabel editTitelLabel, editEpisodeLabel, editFullLabel, editGenreLabel, editLanguageLabel, editLentLabel;
    private JLabel titleSearchLabel, lastChange, newGenreLabel, newLanguageLabel;
    private JPanel searchPanel, listPanel, editPanel, allLabelPanel;
    private JPanel editButtonPanel, editInfosPanel;
    private List<JButton> componentsActionButton;


    /**
     * Konstruktor der alles nötigen Methoden aufruft.
     * 
     * @param controller
     */
    public MainWindow(Controller controller)
    {
        super(Controller.stats.getObject("titleGUI").toString());
        this.controller = controller;
        installNewLAF();
        setUI();
        getContentPane().setLayout(new BorderLayout());
        setGUIInfos();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(allLabelPanel, BorderLayout.CENTER);
        getContentPane().add(lastChange, BorderLayout.SOUTH);
        pack();
        if (UIManager.getLookAndFeel().getName().equals("Nimbus"))
        {
            setSize(new Dimension(650, 770));
        }
        else
        {
            setSize(GUI_DIMENSION);
        }
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this.controller);
        setVisible(true);
        this.setIconImage(APPLICATION_ICON);
    }


    private void installNewLAF()
    {
        UIManager.LookAndFeelInfo[] install = new UIManager.LookAndFeelInfo[ListerProperties.getLAFProperties().size() / 2];
        for (int key = 1, index = 0; key < ListerProperties.getLAFProperties().size(); key += 2, index++)
        {
            install[index] = new UIManager.LookAndFeelInfo(ListerProperties.getLAFProperties().getProperty(
                    Integer.toString(key)), ListerProperties.getLAFProperties().getProperty(Integer.toString(key + 1)));
        }
        UIManager.setInstalledLookAndFeels(install);
    }


    /**
     * Wird gebraucht damit das System LAF geladen wird.
     */
    private void setUI()
    {
        try
        {
            UIManager.setLookAndFeel(ListerProperties.getDefaultProperties().get("design").toString());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
    }


    private void setGUIInfos()
    {
        componentsActionButton = new ArrayList<JButton>();
        setButtons();
        setLabelsNamesAndMenubar();
        setTextfield();
        setPanel();
        setSearchPanel();
        setListPanel();
        setEditPanel();
        setAllPanel();
        setListener();
    }


    private void setLabelName()
    {
        titleSearchLabel = new JLabel(Controller.stats.getObject("search").toString());
        Font f = new Font("Sans Sarif", Font.CENTER_BASELINE, 14);
        titleSearchLabel.setFont(f);
        lastChange = new JLabel(EMPTY_STRING);
        searchTitelLabel = new JLabel(STRING_TITEL);
        searchGenreLabel = new JLabel(STRING_GENRE);
        searchLanguageLabel = new JLabel(STRING_LANGUAGE);
        searchFullLabel = new JLabel(Controller.stats.getObject("episodesTotal").toString());
        searchResultsLabel = new JLabel(STRING_AMOUNT_OF_RESULTS);
        editTitelLabel = new JLabel(STRING_TITEL);
        editGenreLabel = new JLabel(STRING_GENRE);
        editEpisodeLabel = new JLabel(Controller.stats.getObject("episodes").toString());
        editLanguageLabel = new JLabel(STRING_LANGUAGE);
        editFullLabel = new JLabel(Controller.stats.getObject("total").toString());
        editLentLabel = new JLabel(Controller.stats.getObject("lent").toString());
        newGenreLabel = new JLabel(EMPTY_STRING);
        newGenreLabel.setPreferredSize(new Dimension(50, 20));
        newLanguageLabel = new JLabel(EMPTY_STRING);
        newLanguageLabel.setPreferredSize(newGenreLabel.getPreferredSize());
    }


    private void setPanel()
    {
        searchPanel = new JPanel();
        listPanel = new JPanel();
        editPanel = new JPanel();
        allLabelPanel = new JPanel();
        editButtonPanel = new JPanel();
        editInfosPanel = new JPanel();
        editLanguageBox = new JComboBox(controller.getSerien().getLanguageListe().toArray());
        editGenreBox = new JComboBox(controller.getSerien().getGenreListe().toArray());
        searchLanguageBox = new JComboBox(controller.getSerien().getLanguageListe().toArray());
        searchGenreBox = new JComboBox(controller.getSerien().getGenreListe().toArray());
        resizeBoxes();
        searchPanel.setLayout(new GridBagLayout());
        listPanel.setLayout(new GridBagLayout());
        editPanel.setLayout(new GridBagLayout());
        allLabelPanel.setLayout(new GridBagLayout());
        editButtonPanel.setLayout(new GridBagLayout());
        editInfosPanel.setLayout(new GridBagLayout());
    }


    public void resizeBoxes()
    {
        int width = (int) editLanguageBox.getPreferredSize().getWidth();
        if (width < editGenreBox.getPreferredSize().getWidth())
        {
            width = (int) editGenreBox.getPreferredSize().getWidth();
        }
        if (width < 70)
        {
            width = 70;
        }
        Dimension boxDimension = new Dimension(width, (int) editLanguageBox.getPreferredSize().getHeight());
        editLanguageBox.setPreferredSize(boxDimension);
        editGenreBox.setPreferredSize(boxDimension);
        searchLanguageBox.setPreferredSize(boxDimension);
        searchGenreBox.setPreferredSize(boxDimension);
    }


    private void setTextfield()
    {
        searchTiteltf = new JTextField(20);
        searchTiteltf.setFocusAccelerator('f');
        searchIncomplete = new JCheckBox(STRING_INCOMPLETE);
        searchIncomplete.setHorizontalTextPosition(SwingConstants.LEFT);
        searchLent = new JCheckBox(Controller.stats.getObject("lent").toString());
        searchLent.setHorizontalTextPosition(SwingConstants.LEFT);
        searchNotLent = new JCheckBox(Controller.stats.getObject("notLent").toString());
        searchNotLent.setHorizontalTextPosition(SwingConstants.LEFT);
        searchComplete = new JCheckBox(STRING_COMPLETE);
        searchComplete.setHorizontalTextPosition(SwingConstants.LEFT);
        editComplete = new JCheckBox(STRING_COMPLETE);
        searchComplete.setHorizontalTextPosition(SwingConstants.LEFT);
        searchFulltf = new JTextField(4);
        editTiteltf = new JTextField(20);
        editEpisodetf = new JTextField(8);
        editFulltf = new JTextField(4);
        editLentTf = new JTextField(8);
        editTiteltf.setFocusAccelerator('e');
    }


    private void setLabelsNamesAndMenubar()
    {
        APPLICATION_ICON = getToolkit().createImage(this.getClass().getResource("../../data/logo-A-Lister.png"));
        table = controller.getSerienTable();
        serieLibScrollPane = new JScrollPane(table);
        serieLibScrollPane.setPreferredSize(new Dimension(500, 300));
        setLabelName();
        this.setJMenuBar(new MyMenu(this));
    }


    public void disEnableAll()
    {
        addButton.setEnabled(controller.isViewOnly());
        editButton.setEnabled(controller.isViewOnly());
        addOneButton.setEnabled(controller.isViewOnly());
        removeOneButton.setEnabled(controller.isViewOnly());
        deleteButton.setEnabled(controller.isViewOnly());
        editTiteltf.setEnabled(controller.isViewOnly());
        editEpisodetf.setEnabled(controller.isViewOnly());
        editFulltf.setEnabled(controller.isViewOnly());
        editComplete.setEnabled(controller.isViewOnly());
        editGenreBox.setEnabled(controller.isViewOnly());
        editLanguageBox.setEnabled(controller.isViewOnly());
        newGenreButton.setEnabled(controller.isViewOnly());
        newLanguageButton.setEnabled(controller.isViewOnly());
        controller.setViewOnly(!controller.isViewOnly());
    }


    private void setButtons()
    {
        componentsActionButton.add(newGenreButton = new JButton(ADD_STRING));
        componentsActionButton.add(newLanguageButton = new JButton(ADD_STRING));
        componentsActionButton.add(clearGenre = new JButton(STRING_DELETE));
        componentsActionButton.add(clearLanguage = new JButton(STRING_DELETE));
        componentsActionButton.add(resetButton = new JButton(Controller.stats.getObject("buttonReset").toString()));
        componentsActionButton.add(addButton = new JButton(Controller.stats.getObject("buttonAdd").toString()));
        componentsActionButton.add(deleteButton = new JButton(Controller.stats.getObject("buttonDelete").toString()));
        componentsActionButton.add(editButton = new JButton(Controller.stats.getObject("buttonEdit").toString()));
        componentsActionButton.add(disEnable = new JButton(Controller.stats.getObject("buttonLeftView").toString()));
        componentsActionButton.add(addOneButton = new JButton(Controller.stats.getObject("buttonPlus").toString()));
        componentsActionButton.add(removeOneButton = new JButton(Controller.stats.getObject("buttonMinus").toString()));
        disEnable.setVisible(false);
        addOneButton.setPreferredSize(new Dimension(42, 23));
        addOneButton.setBorderPainted(false);
        addOneButton.setMnemonic('q');
        removeOneButton.setMnemonic('w');
        removeOneButton.setPreferredSize(new Dimension(39, 23));
        removeOneButton.setBorderPainted(false);
        newGenreButton.setPreferredSize(new Dimension(20, 20));
        newLanguageButton.setPreferredSize(newGenreButton.getPreferredSize());
        newGenreButton.setName(STRING_GENRE);
        newLanguageButton.setName(STRING_LANGUAGE);
        newGenreButton.setFocusable(false);
        newLanguageButton.setFocusable(false);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        addOneButton.setEnabled(false);
        removeOneButton.setEnabled(false);
        clearGenre.setVisible(false);
        clearLanguage.setVisible(false);
    }


    public void focus(boolean status)
    {
        editEpisodetf.setEnabled(status);
        editTiteltf.setEnabled(status);
        editFulltf.setEnabled(status);
    }


    private void setListener()
    {
        for (JButton component : componentsActionButton)
        {
            component.addActionListener(controller);
        }
        searchComplete.addActionListener(controller);
        searchIncomplete.addActionListener(controller);
        searchLent.addActionListener(controller);
        searchNotLent.addActionListener(controller);
        editLentTf.addActionListener(controller);
        searchComplete.addKeyListener(controller);
        searchIncomplete.addKeyListener(controller);
        searchLent.addKeyListener(controller);
        searchNotLent.addKeyListener(controller);
        editComplete.addActionListener(controller);
        clearGenre.addActionListener(controller);
        clearLanguage.addActionListener(controller);
        searchGenreBox.addItemListener(controller);
        searchLanguageBox.addItemListener(controller);
        editTiteltf.addKeyListener(controller);
        editEpisodetf.addKeyListener(controller);
        editFulltf.addKeyListener(controller);
        editLanguageBox.addKeyListener(controller);
        editGenreBox.addKeyListener(controller);
        editLentTf.addKeyListener(controller);
        searchTiteltf.addKeyListener(controller);
        table.addKeyListener(controller);
        table.addMouseListener(controller);
        table.addMouseMotionListener(controller.getMouseAdapter());
        searchFulltf.addKeyListener(controller);
        editComplete.addKeyListener(controller);
        table.getSelectionModel().addListSelectionListener(controller);
    }


    private void setSearchPanel()
    {
        // Zeile
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        searchPanel.add(titleSearchLabel, gridBag);
        // Zeile
        gridBag.gridy = 1;
        gridBag.gridx = 0;
        searchPanel.add(searchTitelLabel, gridBag);
        gridBag.gridx = 1;
        searchPanel.add(searchTiteltf, gridBag);
        gridBag.insets.set(0, 3, 0, 0);
        gridBag.gridx = 2;
        searchPanel.add(searchLanguageLabel, gridBag);
        gridBag.gridx = 3;
        searchPanel.add(searchLanguageBox, gridBag);
        gridBag.insets.set(0, 0, 0, 0);
        // Zeile 
        gridBag.gridy = 2;
        gridBag.gridx = 0;
        searchPanel.add(searchFullLabel, gridBag);
        gridBag.gridx = 1;
        searchPanel.add(searchFulltf, gridBag);
        gridBag.insets.set(0, 3, 0, 0);
        gridBag.gridx = 2;
        searchPanel.add(searchGenreLabel, gridBag);
        gridBag.gridx = 3;
        searchPanel.add(searchGenreBox, gridBag);
        gridBag.insets.set(0, 0, 0, 0);
        // Zeile
        gridBag.gridy = 3;
        gridBag.gridx = 0;
        searchPanel.add(searchComplete, gridBag);
        gridBag.gridx = 1;
        searchPanel.add(searchIncomplete, gridBag);
        gridBag.insets.set(0, 0, 0, 0);
        // Button
        gridBag.gridy = 4;
        gridBag.gridx = 0;
        searchPanel.add(searchLent, gridBag);
        gridBag.gridx = 1;
        searchPanel.add(searchNotLent, gridBag);
        gridBag.gridx = 3;
        gridBag.insets.set(0, 3, 0, 0);
        searchPanel.add(resetButton, gridBag);
        gridBag.insets.set(0, 0, 0, 0);
        // Zeile
        gridBag.gridy = 5;
        gridBag.gridx = 0;
        searchPanel.add(searchResultsLabel, gridBag);
    }


    private void setListPanel()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        gridBag.insets.set(3, 0, 0, 0);
        listPanel.add(serieLibScrollPane, gridBag);
        gridBag.gridy = 1;
        gridBag.anchor = GridBagConstraints.CENTER;
        listPanel.add(GuiHelper.createPanelAligned(deleteButton, addButton, disEnable), gridBag);
    }

    private void setEditPanel()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.gridwidth = 1;
        // Zeile 1
        int x = -1;
        gridBag.insets.set(0, 3, 0, 0);
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        editPanel.add(editTitelLabel, gridBag);
        gridBag.gridx = 1;
        gridBag.gridwidth = 3;
        editPanel.add(editTiteltf, gridBag);
        gridBag.gridwidth = 1;
        gridBag.gridx = 4;
        editPanel.add(editLanguageLabel, gridBag);
        gridBag.gridx = 5;
        editPanel.add(editLanguageBox, gridBag);
        gridBag.gridx = 6;
        editPanel.add(newLanguageButton, gridBag);
        gridBag.gridx = 7;
        editPanel.add(newLanguageLabel, gridBag);
        gridBag.gridx = 8;
        editPanel.add(clearLanguage, gridBag);
        // Zeile 2
        gridBag.gridy = 1;
        x = -1;
        gridBag.gridx = ++x;
        editPanel.add(editEpisodeLabel, gridBag);
        gridBag.gridx = ++x;
        editPanel.add(editEpisodetf, gridBag);
        gridBag.gridx = ++x;
        gridBag.gridwidth = 2;
        editPanel.add(editComplete, gridBag);
        gridBag.gridwidth = 1;
        x += 2;
        gridBag.gridx = x;
        editPanel.add(editGenreLabel, gridBag);
        gridBag.gridx = ++x;
        editPanel.add(editGenreBox, gridBag);
        gridBag.gridx = ++x;
        editPanel.add(newGenreButton, gridBag);
        gridBag.gridx = ++x;
        editPanel.add(newGenreLabel, gridBag);
        gridBag.gridx = ++x;
        editPanel.add(clearGenre, gridBag);
        // Zeile 3
        x = -1;
        gridBag.gridx = ++x;
        gridBag.gridy = 2;
        editPanel.add(editFullLabel, gridBag);
        gridBag.gridx = ++x;
        gridBag.gridwidth = 3;
        x += 2;
        editPanel.add(editFulltf, gridBag);
        gridBag.gridwidth = 1;
        // Zeile 4
        gridBag.insets.set(0, 3, 0, 0);
        x = 0;
        gridBag.gridy = 3;
        gridBag.gridx = x++;
        editPanel.add(editLentLabel, gridBag);
        gridBag.gridx = x;
        editPanel.add(editLentTf, gridBag);
        
        //row 5
        gridBag.gridy = 4;
        gridBag.gridx = ++x;
        gridBag.gridx = ++x;
        editPanel.add(editButton, gridBag);
        gridBag.gridx = ++x;
        editPanel.add(addOneButton, gridBag);
        gridBag.gridx = ++x;
        editPanel.add(removeOneButton, gridBag);
    }


    private void setAllPanel()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets.set(0, 0, 0, 0);
        gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.gridy = 0;
        allLabelPanel.add(searchPanel, gridBag);
        gridBag.gridy = 1;
        gridBag.insets.set(5, 0, 5, 0);
        allLabelPanel.add(listPanel, gridBag);
        gridBag.gridy = 2;
        gridBag.insets.set(5, 0, 0, 0);
        allLabelPanel.add(editPanel, gridBag);
    }


    public Controller getController()
    {
        return controller;
    }


    public void refreshBoxes()
    {
        searchLanguageBox.removeAllItems();
        searchGenreBox.removeAllItems();
        editLanguageBox.removeAllItems();
        editGenreBox.removeAllItems();
        for (String language : controller.getSerien().getLanguageListe())
        {
            searchLanguageBox.addItem(language);
            editLanguageBox.addItem(language);
        }
        for (String genre : controller.getSerien().getGenreListe())
        {
            searchGenreBox.addItem(genre);
            editGenreBox.addItem(genre);
        }
        resizeBoxes();
    }


    public void refreshGenreBox(String newItem)
    {
        editGenreBox.addItem(newItem);
        searchGenreBox.addItem(newItem);
    }


    public void refreshLanguageBox(String newItem)
    {
        editLanguageBox.addItem(newItem);
        searchLanguageBox.addItem(newItem);
    }

    // getter und setter für searchPanel
    public JPanel getSearchPanel()
    {
        return searchPanel;
    }
    
    public JTextField getSearchTiteltf()
    {
        return searchTiteltf;
    }


    public void setSearchTiteltf(String search)
    {
        searchTiteltf.setText(search);
    }


    public JCheckBox getSearchComplete()
    {
        return searchComplete;
    }


    public void setSearchComplete(boolean full)
    {
        searchComplete.setSelected(full);
    }


    public JCheckBox getSearchIncomplete()
    {
        return searchIncomplete;
    }


    public void setSearchIncomplete(boolean full)
    {
        searchIncomplete.setSelected(full);
    }


    public JCheckBox getSearchLent()
    {
        return searchLent;
    }


    public void setSearchLent(boolean lent)
    {
        searchLent.setSelected(lent);
    }
    
    public void setSearchResultsLabel(Integer results)
    {
        searchResultsLabel.setText(STRING_AMOUNT_OF_RESULTS + results);
    }


    public JCheckBox getSearchNotLent()
    {
        return searchNotLent;
    }


    public void setSearchNotLent(boolean lent)
    {
        searchNotLent.setSelected(lent);
    }


    public JTextField getSearchFulltf()
    {
        return searchFulltf;
    }


    public void setSearchFulltf(String search)
    {
        searchFulltf.setText(search);
    }


    public String getSearchLanguage()
    {
        return searchLanguageBox.getSelectedItem().toString();
    }


    public JComboBox getSearchLanguageBox()
    {
        return searchLanguageBox;
    }


    public void setSearchLanguageBox(int selection)
    {
        searchLanguageBox.setSelectedIndex(selection);
    }


    public String getSearchGenre()
    {
        return searchGenreBox.getSelectedItem().toString();
    }


    public JComboBox getSearchGenreBox()
    {
        return searchGenreBox;
    }

    public void setSearchGenreBox(int selection)
    {
        searchGenreBox.setSelectedIndex(selection);
    }

    public JButton getResetButton()
    {
        return resetButton;
    }

    // Getter und Setter für AnzeigePanel
    public SerienTable getTable()
    {
        return table;
    }

    public JButton getAddButton()
    {
        return addButton;
    }

    public JButton getDeleteButton()
    {
        return deleteButton;
    }

    // getter und setter für editPanel
    public JPanel getEditPanel()
    {
        return editPanel;
    }
    
    public JTextField getEditTiteltf()
    {
        return editTiteltf;
    }

    public void setEditTiteltf(String edit)
    {
        editTiteltf.setText(edit);
    }

    public JTextField getEditEpisodetf()
    {
        return editEpisodetf;
    }

    public void setEditEpisodetf(String edit)
    {
        editEpisodetf.setText(edit);
    }

    public JCheckBox getEditComplete()
    {
        return editComplete;
    }

    public JTextField getEditFulltf()
    {
        return editFulltf;
    }

    public void setEditFulltf(String edit)
    {
        editFulltf.setText(edit);
    }

    public void setEditLanguageBox(int selection)
    {
        editLanguageBox.setSelectedIndex(selection);
    }

    public JComboBox getEditLanguageBox()
    {
        return editLanguageBox;
    }

    public String getEditLanguage()
    {
        return editLanguageBox.getSelectedItem().toString();
    }

    public void setEditGenreBox(int selection)
    {
        editGenreBox.setSelectedIndex(selection);
    }

    public JComboBox getEditGenreBox()
    {
        return editGenreBox;
    }

    public String getEditGenre()
    {
        return editGenreBox.getSelectedItem().toString();
    }

    public JTextField getEditLentTf()
    {
        return editLentTf;
    }

    public void setEditLent(String lent)
    {
        editLentTf.setText(lent);
    }

    // Getter und Setter für neue Infos
    public JLabel getNewLanguageLabel()
    {
        return newLanguageLabel;
    }

    public JLabel getNewGenreLabel()
    {
        return newGenreLabel;
    }


    public JButton getNewGenreButton()
    {
        return newGenreButton;
    }

    public JButton getNewLanguageButton()
    {
        return newLanguageButton;
    }


    public JButton getClearGenre()
    {
        return clearGenre;
    }

    public JButton getClearLanguage()
    {
        return clearLanguage;
    }


    // Getter und Setter für Editeren
    public JButton getEditButton()
    {
        return editButton;
    }

    public JButton getAddOneButton()
    {
        return addOneButton;
    }

    public JButton getRemoveOneButton()
    {
        return removeOneButton;
    }

    // Getter und Setter für OnlyView und Edit ansicht
    public JButton getDisEnable()
    {
        return disEnable;
    }

    public void setLastChange(String newTime)
    {
        lastChange.setText(Controller.stats.getObject("lastChange").toString() + newTime);
    }

}
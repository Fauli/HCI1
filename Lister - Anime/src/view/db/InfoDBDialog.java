package view.db;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;

import view.main.MainWindow;
import controller.Controller;


/**
 * Zeigt ein Dialog an mit Informationen der Datenbank.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class InfoDBDialog extends JDialog implements ActionListener, KeyListener
{

    // TODO Zscrollbar
    // TODO Zrichtige daten
    private static String SPECIAL_CHARACTER_DURCHSCHNITT = "ø";
    public static String LANGUAGE = Controller.stats.getObject("language").toString();
    private static String EPISODES = Controller.stats.getObject("episodes").toString();
    private static String GENERAL = Controller.stats.getObject("general").toString();
    public static String EPISODES_CAT = Controller.stats.getObject("episodesCategorie").toString();
    public static String GENRE = Controller.stats.getObject("genre").toString();
    private JPanel allgemein, ort, sprache, episodes, genre;
    private List<List<JLabel>> languageLabels, genreLabels, episodeLabels;
    private Border border = new MetalBorders.TableHeaderBorder();
    private Font font;
    private MainWindow gui;
    private JTabbedPane contentPanel;


    public InfoDBDialog(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleInfoDBDialog").toString(), true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
    }


    private void setDialogInfos()
    {
        contentPanel = new JTabbedPane();
        allgemein = new JPanel();
        genre = new JPanel();
        ort = new JPanel();
        sprache = new JPanel();
        episodes = new JPanel();
        allgemein.setLayout(new GridBagLayout());
        genre.setLayout(new GridBagLayout());
        ort.setLayout(new GridBagLayout());
        sprache.setLayout(new GridBagLayout());
        episodes.setLayout(new GridBagLayout());
        createLabelArrays();
        berechnungen();
        createInformationPanels();
        setContentpane(contentPanel);
        this.setContentPane(contentPanel);
        this.pack();
        setResizable(false);
        setLocationRelativeTo(gui);
        setVisible(true);
    }


    private void createInformationPanels()
    {
        setInformationPanels(sprache, LANGUAGE);
        setInformationPanels(genre, GENRE);
        setInformationPanels(episodes, EPISODES_CAT);
        setInforamtionAllgemein();
    }


    private void createLabelArrays()
    {
    	languageLabels  = new ArrayList<List<JLabel>>(3);
    	genreLabels = new ArrayList<List<JLabel>>(3);
    	episodeLabels = new ArrayList<List<JLabel>>(3);
    }


    private void berechnungen()
    {
        berechnung(languageLabels, LANGUAGE);
        berechnung(genreLabels, GENRE);
        berechnung(episodeLabels, EPISODES_CAT);
    }
    
    private void berechnung(List<List<JLabel>> labelList, String type)
    {
    	labelList.add(0, getLeftTitel());
    	labelList.add(1, labelsTitel(type));
    	labelList.add(2, getInfos(type));
    }

    public void neuBerechnung()
    {
        berechnungen();
        setInforamtionAllgemein();
    }


    private void setContentpane(JTabbedPane contentPanel)
    {
        contentPanel.addKeyListener(this);
        contentPanel.add(GENERAL, allgemein);
        contentPanel.add(GENRE, genre);
        contentPanel.add(LANGUAGE, sprache);
        contentPanel.add(EPISODES, episodes);
    }


    private List<JLabel> getLeftTitel()
    {
        font = new Font("Sans Sarif", Font.CENTER_BASELINE, 14);
        List<JLabel> labelLeft = new ArrayList<JLabel>(3);
        labelLeft.add(0,  new JLabel(Controller.stats.getObject("amountOfSeries").toString()));
        labelLeft.get(0).setFont(font);
        labelLeft.add(1,new JLabel(Controller.stats.getObject("episodesTotal").toString()));
        labelLeft.get(1).setFont(font);
        labelLeft.add(2,new JLabel(SPECIAL_CHARACTER_DURCHSCHNITT + " "
                + Controller.stats.getObject("amountOfEp").toString()));
        labelLeft.get(2).setFont(font);
        return labelLeft;
    }


    private List<JLabel> getCommonHead()
    {
        font = new Font("Sans Sarif", Font.CENTER_BASELINE, 14);
        List<JLabel> labelLeft = new ArrayList<JLabel>();
        labelLeft.add(new JLabel(Controller.stats.getObject("generalInformation").toString()));
        labelLeft.get(0).setFont(font);
        labelLeft.add(new JLabel(Controller.stats.getObject("amount").toString()));
        labelLeft.get(0).setFont(font);
        labelLeft.add(new JLabel(SPECIAL_CHARACTER_DURCHSCHNITT + " "
                + Controller.stats.getObject("amountOfEpisodes").toString()));
        labelLeft.get(0).setFont(font);
        labelLeft.add(new JLabel(SPECIAL_CHARACTER_DURCHSCHNITT + " "
                + Controller.stats.getObject("amountOfSeries").toString()));
        labelLeft.get(0).setFont(font);
        return labelLeft;
    }


    private String[] getAllInformationPanel()
    {
        String[] infos = { GENRE, LANGUAGE, EPISODES_CAT };
        return infos;
    }


    private void setInforamtionAllgemein()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        // TitelZeile
        int i = 0;
        gridBag.gridx = 0;
        for (JLabel label : getCommonHead())
        {
            gridBag.gridy = i;
            allgemein.add(label, gridBag);
            i++;
        }
        i = 1;
        // Meta-Daten links
        for (String label : getAllInformationPanel())
        {
            gridBag.gridx = i;
            gridBag.gridy = 0;
            allgemein.add(new JLabel(label), gridBag);
            i++;
        }
        // Informationen
        i = 1;
        gridBag.gridx = i;
        gridBag.gridy = i;
        for (String label : getAllInformationPanel())
        {
            int anzahlTypes = getLabelArrayByType(label).get(1).size()- 1;
            int serienTotal = Integer.parseInt(getInfos(label).get(0).getText());
            int episodenTotal = Integer.parseInt(getInfos(label).get(getInfos(label).size()/ 3).getText());
            allgemein.add(new JLabel(new Integer(anzahlTypes).toString()), gridBag);
            gridBag.gridy++;
            allgemein.add(new JLabel(new Integer(episodenTotal / (anzahlTypes)).toString()), gridBag);
            gridBag.gridy++;
            allgemein.add(new JLabel(new Integer(serienTotal / (anzahlTypes)).toString()), gridBag);
            gridBag.gridx++;
            gridBag.gridy = i;
        }
        // Ok-Button
        gridBag.gridy = 4;
        gridBag.gridx = getCommonHead().size();
        gridBag.anchor = GridBagConstraints.EAST;
        allgemein.add(getOkButton(), gridBag);
    }


    private void setInformationPanels(JPanel all, String titel)
    {
        List<List<JLabel>> labels = getLabelArrayByType(titel);
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        // TitelZeile
        gridBag.gridy = 0;
        int i = 1;
        for (JLabel label : labels.get(0))
        {
            gridBag.gridx = i;
            all.add(label, gridBag);
            i++;
        }
        // Meta-Daten links
        i = 0;
        gridBag.gridx = 0;
        for (JLabel label : labels.get(1))
        {
            gridBag.gridy = i;
            all.add(label, gridBag);
            i++;
        }
        // Informationen
        i = 1;
        gridBag.gridx = i;
        gridBag.gridy = i;
        for (JLabel label : labels.get(2))
        {
            if (i == 4)
            {
                gridBag.gridy++;
                i = 1;
            }
            gridBag.gridx = i;
            all.add(label, gridBag);
            i++;
        }
        // Ok-Button
        gridBag.gridy = labels.get(2).size();
        gridBag.gridx = 0;
        all.add(new JLabel(titel + " insgesamt"), gridBag);
        gridBag.gridx = 1;
        all.add(new JLabel(new Integer(labels.get(1).size() - 1).toString()), gridBag);
        gridBag.gridx = labels.get(0).size() - 1;
        gridBag.anchor = GridBagConstraints.EAST;
        all.add(getOkButton(), gridBag);
    }


    private JButton getOkButton()
    {
        JButton ok = new JButton(Controller.stats.getObject("buttonOk").toString());
        ok.addActionListener(this);
        ok.addKeyListener(this);
        return ok;
    }


    private List<List<JLabel>> getLabelArrayByType(String titel)
    {
        if (titel.equals(LANGUAGE))
        {
            return languageLabels;
        }
        else if (titel.equals(GENRE))
        {
            return genreLabels;
        }
        else if (titel.equals(EPISODES_CAT))
        {
            return episodeLabels;
        }
        return languageLabels;
    }


    private int setLabelArrays(String titel)
    {
        if (titel.equals(LANGUAGE))
        {
            return gui.getController().getSerien().getLanguageListe().size();
        }
        else if (titel.equals(GENRE))
        {
            return gui.getController().getSerien().getGenreListe().size();
        }
        else if (titel.equals(EPISODES_CAT))
        {
            return gui.getController().getSerien().getEpisodeListe().size();
        }
        return 0;
    }


    private String getTitleTextByColumnNumber(int i, String type)
    {
        if (type.equals(LANGUAGE))
        {
            return gui.getController().getSerien().getLanguageListe().get(i);
        }
        else if (type.equals(GENRE))
        {
            return gui.getController().getSerien().getGenreListe().get(i);
        }
        else if (type.equals(EPISODES_CAT))
        {
            return gui.getController().getSerien().getEpisodeListe().get(i);
        }
        return "";
    }


    private List<JLabel> labelsTitel(String type)
    {
        font = new Font("Sans Sarif", Font.CENTER_BASELINE, 14);
        List<JLabel> tempLabelArray = new ArrayList<JLabel>();
        JLabel title = new JLabel (Controller.stats.getObject("showInfoStatistic").toString() + type);
        title.setBorder(border);
        tempLabelArray.add(title);
        tempLabelArray.add(new JLabel(Controller.stats.getObject("showInfoTotal").toString())); 
        for (int i = 1; i< setLabelArrays(type); i++)
        {
        	tempLabelArray.add(new JLabel(getTitleTextByColumnNumber(i , type)));
        }
        for(JLabel label : tempLabelArray)
        {
            label.setFont(font);
        }
        return tempLabelArray;
    }


    private List<JLabel> getInfos(String type)
    {
        List<JLabel> labelsLanguageTitel = new ArrayList<JLabel>();
    	for(Integer infos : gui.getController().getSerien().getDatabaseInfos(type))
    	{
    		labelsLanguageTitel.add(new JLabel(infos.toString()));
    	}
        return labelsLanguageTitel;
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (contentPanel.getSelectedIndex() == contentPanel.getTabCount() - 1)
                contentPanel.setSelectedIndex(0);
            else
                contentPanel.setSelectedIndex(contentPanel.getSelectedIndex() + 1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            this.setVisible(false);
        }
    }


    public void keyReleased(KeyEvent e)
    {}


    public void keyTyped(KeyEvent e)
    {}


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
            this.setVisible(false);
    }
}
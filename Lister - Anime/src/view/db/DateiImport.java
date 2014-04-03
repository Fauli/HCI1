package view.db;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.DateiImportHelper;
import util.EpisodesHelper;
import util.SerieVO;
import view.helper.GuiHelper;
import view.main.MainWindow;
import view.serie.AddDialog;
import controller.Controller;
import controller.ListerProperties;
import controller.SuchAbfrage;


@SuppressWarnings("serial")
public class DateiImport extends JDialog implements ActionListener, KeyListener, ListSelectionListener
{
    private static String FILENAME_CONTENT_EP = " - EP";
    private static String FILENAME_CONTENT_OVA = " - OVA";
    private static String SERIE_CONNECTION_NEW = "Neu";
    private static String SERIE_CONNECTION_SELF = "---";
    
    private MainWindow gui;
    private String pfad = ListerProperties.getDefaultProperties().getProperty("import");
    private JRadioButton sortByNameRadionButton, sortByAmountRadionButton;
    private ButtonGroup sortToRenameAnimesButtonGroup;
    private JCheckBox addToDatabaseCheckbox;
    private JTextField directoryTextField;
    private JTextArea copyPastedInfos;
    private JList<String> animeAuswahl, possibleAnimeList;
    private JLabel anzahlEpis;
    private List<JButton> buttonsList = new ArrayList<JButton>();
    private JButton cleanButton, refreshButton, linkAnisearchButton, linkFansubButton, renameButton;
    private JButton noTitleButton, addNameButton, closeButton, directoryButton;

    private Map<String, Integer> serienEpisodeList; // Serie mit der Anzahl an unbeannnten Episoden.
    private Map<Integer, String> episodeMap; // Mapping einer Episode mit ihrem Titel. Wird aus Textarea geholt.
    private List<String> animeFileList, possibleAnimes;
    private List<SerieVO> newAnimes; //Liste mit den zu hinzufügenden Serien
    private Set<String> animeListe; //Alle Serien
    
    private Map<String, List<Integer>> autoAddEpisodes, selfAddEpisodes; // Maps mit Seriennamen und Liste der benannten Episoden    

    public DateiImport(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleDateiImport").toString(), true);
        this.gui = gui;
        setIconImage(MainWindow.APPLICATION_ICON);
        setLayout(new GridBagLayout());
        setDialogInfos();
    }


    private void setDialogInfos()
    {
        autoAddEpisodes = new HashMap<String, List<Integer>>();
        selfAddEpisodes = new HashMap<String, List<Integer>>();
        initAnimeAuswahlListe();
        createLabelAndPanelInfos();
        positionComponents();
        pack();
        setLocationRelativeTo(gui);
        setResizable(false);
        super.setVisible(true);
    }


    /**
     * Hier werden die Dialoginformationen gestellt.
     */
    private void createLabelAndPanelInfos()
    {
        animeAuswahl = new JList<String>(animeFileList.toArray(new String[animeFileList.size()]));
        newAnimes = new ArrayList<SerieVO>();
        animeAuswahl.addListSelectionListener(this);
        possibleAnimes = new ArrayList<String>();
        possibleAnimeList = new JList<String>(possibleAnimes.toArray(new String[possibleAnimes.size()]));
        copyPastedInfos = new JTextArea();
        directoryTextField = new JTextField(25);
        directoryTextField.setEditable(false);
        if(new File(pfad).exists())
        {
            directoryTextField.setText(pfad);
        }
        else
        {
            JOptionPane.showMessageDialog(null, Controller.stats.getObject("dIWDirectoryNotExistingMsg").toString(),
                    Controller.stats.getObject("dIWDirectoryNotExisting").toString(), JOptionPane.INFORMATION_MESSAGE);
        }
        anzahlEpis = new JLabel("Episoden: ");
        // Buttons
        buttonsList.add(directoryButton = new JButton("..."));
        buttonsList.add(closeButton = new JButton(Controller.stats.getObject("buttonClose").toString()));
        buttonsList.add(noTitleButton = new JButton(Controller.stats.getObject("buttonNoTitle").toString()));
        buttonsList.add(addNameButton = new JButton(Controller.stats.getObject("buttonEdit").toString()));
        buttonsList.add(refreshButton = new JButton(Controller.stats.getObject("buttonRefresh").toString()));
        buttonsList.add(linkAnisearchButton = new JButton(Controller.stats.getObject("buttonLinkAnisearch").toString()));
        buttonsList.add(renameButton = new JButton(Controller.stats.getObject("buttonRename").toString()));
        buttonsList.add(linkFansubButton = new JButton(Controller.stats.getObject("buttonLinkFansub").toString()));
        buttonsList.add(cleanButton = new JButton(Controller.stats.getObject("buttonClean").toString()));
        Dimension buttonSize = new Dimension(90, 23);
        for(JButton button : buttonsList)
        {
            button.addKeyListener(this);
            button.addActionListener(this);
            if (directoryButton != button)
                button.setPreferredSize(buttonSize);
        }
        sortByNameRadionButton = new JRadioButton(Controller.stats.getObject("dIWName").toString());
        sortByNameRadionButton.setSelected(true);
        sortByAmountRadionButton = new JRadioButton(Controller.stats.getObject("dIWEpisode").toString());
        sortByAmountRadionButton.addActionListener(this);
        sortByNameRadionButton.addActionListener(this);
        sortToRenameAnimesButtonGroup = new ButtonGroup();
        sortToRenameAnimesButtonGroup.add(sortByNameRadionButton);
        sortToRenameAnimesButtonGroup.add(sortByAmountRadionButton);
        addToDatabaseCheckbox = new JCheckBox("Autoadd");
        addToDatabaseCheckbox.setSelected(true);
        
        noTitleButton.setEnabled(false);
        addNameButton.setEnabled(false);
        linkAnisearchButton.setEnabled(false);
        linkFansubButton.setEnabled(false);
        renameButton.setEnabled(false);
    }
    
    private JScrollPane createJScrollPane(Dimension preferredSize, Component content)
    {
        JScrollPane returnPane = new JScrollPane(content);
        returnPane.setPreferredSize(preferredSize);
        return returnPane;
    }


    /**
     * Erstellt ein HashSet mit allen Animes die in diesem Verzeichnis vorhanden sind.
     */
    private void initAnimeAuswahlListe()
    {
        animeListe = new HashSet<String>();
        File myDir = new File(pfad);
        serienEpisodeList = new HashMap<String, Integer>();
        String serienName = "";
        if (myDir.exists() && myDir.isDirectory())
        {
            for (File file : myDir.listFiles())
            {
                if (!file.isDirectory() && !file.isHidden()
                        && file.getName().charAt(file.getName().length() - 4) == '.'
                        && file.getName().charAt(file.getName().length() - 5) == ' ')
                {
                    if (file.getName().contains(FILENAME_CONTENT_EP))
                    {
                        serienName = file.getName().split(FILENAME_CONTENT_EP)[0];
                    }
                    else if (file.getName().contains(FILENAME_CONTENT_OVA))
                    {
                        serienName = file.getName().split(FILENAME_CONTENT_OVA)[0];
                    }
                    animeListe.add(serienName);
                    serienEpisodeList(serienName);
                }
            }
        }
        sortAnimeList();
    }
    
    @SuppressWarnings("boxing")
    private void serienEpisodeList(String serienName)
    {
        if (serienEpisodeList.containsKey(serienName))
        {
            int episodes = 1 + serienEpisodeList.get(serienName);
            serienEpisodeList.put(serienName, new Integer(episodes));
        }
        else
        {
            serienEpisodeList.put(serienName, new Integer(1));
        }
    }

    /**
     * Hier werden die Elemente positioniert.
     * 
     * @param dialog
     */
    public void positionComponents()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        // FileChooser
        gridBag.gridy = 0;
        this.add(GuiHelper.createPanelAligned(directoryTextField, directoryButton), gridBag);
        // Zeile 1 Radiobutton sortign
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        this.add(GuiHelper.createPanelAligned(sortByNameRadionButton, sortByAmountRadionButton), gridBag);
        // Zeile 2
        gridBag.gridy = 2;
        gridBag.gridx = 0;

        this.add(
                GuiHelper.createPanelAligned(createJScrollPane(new Dimension(210, 200), animeAuswahl),
                        GuiHelper.createPanelVertical(GridBagConstraints.NORTHWEST, 
                                cleanButton, refreshButton, anzahlEpis, 
                                linkAnisearchButton, linkFansubButton, renameButton),
                        createJScrollPane(new Dimension(210, 200), possibleAnimeList)), gridBag);

        // Zeile 4
        gridBag.gridy = 3;
        gridBag.gridx = 0;
        this.add(GuiHelper.createPanelAligned(
                createJScrollPane(new Dimension(420, 200), copyPastedInfos), 
                GuiHelper.createPanelVertical(GridBagConstraints.NORTHWEST, 
                        addNameButton, noTitleButton, addToDatabaseCheckbox)), gridBag);
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.gridy = 4;
        this.add(GuiHelper.createPanelAligned(closeButton), gridBag);
    }

    /**
     * Ruft nach wahl die Methoden generate...HashMap auf, die dann eine Map erstellen mit Episodenzahl - Episodentitel.
     */
    private void generateHashMap()
    {
        episodeMap = new HashMap<Integer, String>();
        generateFanSubHashMap();
        if(episodeMap.isEmpty())
            generateAniSearchHashMap();
    }

    private void generateFanSubHashMap()
    {
        String text = copyPastedInfos.getText();
        boolean name = true;
        boolean startText = false;
        for (String line : text.split("\n"))
        {
            if (startText && line.equals(""))
            {
                // Letzte Linie
                break;
            }
            if (startText && name && !episodeMap.containsKey(DateiImportHelper.getEpisodeNrOfFileName(line.split(" ")[0])))
            {
                episodeMap.put(DateiImportHelper.getEpisodeNrOfFileName(line.split(" ")[0]), line.split("\t")[1]);
            }
            if (line.equals("Release Infos"))
            {
                // Beginn des ersten Episodennamens
                startText = true;
                name = false;
            }
            name = !name;
        }
    }

    private void generateAniSearchHashMap()
    {
        String text = copyPastedInfos.getText();
        boolean startText = false;
        for (String line : text.split("\n"))
        {
            if (startText && line.equals(""))
            {
                break;
            }
            if (startText && !episodeMap.containsKey(DateiImportHelper.getEpisodeNrOfFileName(line.split(" ")[0])))
            {
                episodeMap.put(DateiImportHelper.getEpisodeNrOfFileName(line.split("\t\t")[0]), line.split("\t\t")[1]);
            }
            if (line.contains("Anime Episodenliste"))
            {
                startText = true;
            }
        }
    }

    private void setFileName(boolean name)
    {

        File myDir = new File(pfad);
        if (myDir.exists() && myDir.isDirectory())
        {
            String epiOVA = null;
            String filename = null;
            File newFileName;
            List<Integer> episodes = new ArrayList<Integer>();
            for (File file : myDir.listFiles())
            {
                filename = file.getName();
                if(file.isDirectory() || file.isHidden() || 
                        filename.charAt(filename.length() - 4) != '.')
                    continue;
                if (filename.contains(FILENAME_CONTENT_EP))
                {
                    epiOVA = "EP";
                }
                else if (filename.contains(FILENAME_CONTENT_OVA))
                {
                    epiOVA = "OVA";
                }
                if (filename.split(" - " + epiOVA)[0].equals(animeAuswahl.getSelectedValue()))
                {
                    if(name && filename.charAt(filename.length() - 5) == ' '
                        && filename.charAt(filename.length() - 4) == '.')
                    {
                        newFileName = new File(myDir.getPath() + "\\" + DateiImportHelper.cleanUpNewFileName(DateiImportHelper.createFileName(filename, epiOVA, episodeMap)));
                        episodes.add(new Integer(filename.split(" - " + epiOVA)[1].substring(0, 2)));
                        file.renameTo(newFileName);
                    }
                    else if(!name)
                    {
                        // Episodenzahl herausfinden
                        newFileName = new File(myDir.getPath() + "\\" + DateiImportHelper.createFileNameNoTitle(filename));
                        episodes.add(new Integer(filename.split(" - " + epiOVA)[1].substring(0, 2)));
                        file.renameTo(newFileName);
                    }

                }
            }
            insertIntoList(episodes);
        }
    }
    

	private void insertIntoList(List<Integer> episodes) 
	{
		if (addToDatabaseCheckbox.isSelected())
		{
			if(possibleAnimeList.getSelectedValue() == null || possibleAnimeList.getSelectedValue().toString().equals(SERIE_CONNECTION_SELF))
			{
		        selfAddEpisodes.put(animeAuswahl.getSelectedValue().toString(), episodes);
			}
			else if(possibleAnimeList.getSelectedValue().toString().equals(SERIE_CONNECTION_NEW))
			{
			    SerieVO createdSerie = DateiImportHelper.createSerieByCopyPaste(episodes, animeAuswahl.getSelectedValue().toString(),
                        copyPastedInfos.getText(), gui.getController().getSerien().getGenreListe());
			    if(createdSerie != null)
			        newAnimes.add(createdSerie);
			    else
			        autoAddEpisodes.put(animeAuswahl.getSelectedValue().toString(), episodes);
			}
			else
			{
	            addInformationIntoDatabase(episodes, possibleAnimeList.getSelectedValue().toString());
			}
		}
	}


    private void addInformationIntoDatabase(List<Integer> episodeList, String title)
    {
        for (SerieVO serie : gui.getController().getSerien().getAllSerien())
        {
            if (title.equalsIgnoreCase(serie.getTitle()))
            {
                List<Integer> addedEpisodesList = new ArrayList<Integer>();
                for (Integer episode : episodeList)
                {
                    if (EpisodesHelper.addingPossible(episode.intValue(), serie.getEpisodesTotal(), serie.getEpisodes()))
                    {
                        addIntoDB(serie, episode);
                        addedEpisodesList.add(episode);
                    }
                }
                for (Integer episode : addedEpisodesList)
                {
                	episodeList.remove(episode);
                }
                if (episodeList.size() != 0)
                {
                    autoAddEpisodes.put(title, episodeList);
                }
            }
        }
    }


	private void addIntoDB(SerieVO serie, Integer episode) 
	{
		if (serie.getEpisodes().equals("1") && episode.equals(new Integer(2)))
		{
		    gui.getEditEpisodetf().setText("1-2");
		}
		else if (serie.getEpisodes().contains("-") && serie.getEpisodes().split("-")[0].equals(new Integer(1)+"") 
				&& serie.getEpisodes().split("-")[1].equals(new Integer(episode.intValue() - 1) + ""))
		{
		    int newEpisode = new Integer(serie.getEpisodes().split("-")[1]).intValue() +1;
		    serie.setEpisoden("1-" + newEpisode);
		}
		else if (serie.getEpisodes().contains("+"))
		{
		    List<String> relatedList = new ArrayList<String>();
		    for (String epList : serie.getEpisodes().split("\\+"))
		    {
		        if (isNewEpisodeNextToShortList(episode, epList))
		        {
		            relatedList.add(epList);
		        }
		    }
		    String newList = serie.getEpisodes();;
		    if (relatedList.size() == 1)
		    {
		        newList = serie.getEpisodes().replaceAll(relatedList.get(0), addEpisodeIntoAppendedList(
		                relatedList.get(0), episode));
		    }
		    else if (relatedList.size() == 2)
		    {
		        String oldString = relatedList.get(0) + "+" + relatedList.get(1);
		        newList = newList.replace(oldString, addEpisodeIntoTwoAppendedLists(relatedList
		                .get(0), relatedList.get(1)));
		    }
		    else if (relatedList.size() == 0)
		    {
		        newList = addEpisodeIntoList(serie.getEpisodes(), episode);
		    }
		    serie.setEpisoden(newList);
		}
		else if(serie.getEpisodes().contains("-") && !serie.getEpisodes().split("-")[0].equals(new Integer(1)+"")
				&& episode + 1 == new Integer(serie.getEpisodes().split("-")[0]))
		{
			serie.setEpisoden(episode + "-" + serie.getEpisodes().split("-")[1]);
		}
		else if(serie.getEpisodes().contains("-") && episode == new Integer(serie.getEpisodes().split("-")[0]))
		{
			addNotNextEpisode(serie, serie.getEpisodes().split("-")[0], episode);
		}
		else
		{
			addNotNextEpisode(serie, serie.getEpisodes(), episode);
		}
	}
	
	private void addNotNextEpisode(SerieVO serie, String smallerInt,Integer episode)
	{
		if(new Integer(smallerInt)
		> episode)
		{
			serie.setEpisoden(episode + "+" + serie.getEpisodes());
		}
		else
		{
			serie.setEpisoden(serie.getEpisodes() + "+" + episode);
		}
	}

    /**
     * @param episode
     * @param shortEpList String like "8" or "8-10"
     * @return true if episode is next to the shortEpList
     */
    private boolean isNewEpisodeNextToShortList(Integer episode, String shortEpList)
    {
        if (DateiImportHelper.isNumber(shortEpList))
        {
            Integer tempInteger = new Integer(shortEpList);
            if (episode.intValue() + 1 == tempInteger.intValue() || episode.intValue() - 1 == tempInteger.intValue())
            {
                return true;
            }
        }
        else if (shortEpList.contains("-"))
        {
            if (shortEpList.split("-")[0].equals(new Integer(episode.intValue() + 1) + "")
                    || shortEpList.split("-")[1].equals(new Integer(episode.intValue() - 1) + ""))
            {
                return true;
            }
        }
        return false;
    }


    private String addEpisodeIntoAppendedList(String oldList, Integer episode)
    {
        if (oldList.split("-")[0].equals(new Integer(episode.intValue() + 1) + ""))
        {
            return episode + "-" + oldList.split("-")[1];
        }
        return oldList.split("-")[0] + "-" + episode;
    }

    private String addEpisodeIntoTwoAppendedLists(String toMixListPartA, String toMixListPartB)
    {
        String firstPart = "", lastPart = "";
        if (toMixListPartA.contains("-"))
        {
            firstPart = toMixListPartA.split("-")[0];
        }
        else
        {
            firstPart = toMixListPartA;
        }
        if (toMixListPartB.contains("-"))
        {
            lastPart = toMixListPartB.split("-")[1];
        }
        else
        {
            lastPart = toMixListPartB;
        }
        return firstPart + "-" + lastPart;
    }

    private String addEpisodeIntoList(String episodeList, Integer episode)
    {
        for (String episodeListpart : episodeList.split("\\+"))
        {
            if (episodeListpart.contains("-"))
            {
                if (episode.intValue() < new Integer(episodeListpart.split("-")[0]).intValue())
                {
                    return episodeList.replace(episodeListpart, "+" + episode + episodeListpart);
                }
            }
            else
            {
                if (episode.intValue() < new Integer(episodeListpart).intValue())
                {
                    return episodeList.replace(episodeListpart, "+" + episode + episodeListpart);
                }
            }
        }
        return episodeList + "+" + episode;
    }

    private String getWarningMessage()
    {
        StringBuffer episoden = new StringBuffer();
        for (String title : selfAddEpisodes.keySet())
        {
            episoden.append(createNotYetIncludetEpisodes(title, selfAddEpisodes.get(title)));
        }
        for (String title : autoAddEpisodes.keySet())
        {
            episoden.append(createNotYetIncludetEpisodes(title, autoAddEpisodes.get(title)));
        }
        return episoden.toString();
    }
    
    private String createNotYetIncludetEpisodes(String title, List<Integer> episodes)
    {
    	StringBuffer episoden = new StringBuffer();
        episoden.append(Controller.stats.getObject("dIWForTheSerie").toString());
        episoden.append(" '" + title + "' ");
        episoden.append(Controller.stats.getObject("dIWTheEpisodes").toString());
        episoden.append(" ");
        int i = 0;
        for (Integer episode : episodes)
        {
            if(i == 30)
                break;
            i++;
            episoden.append(episode);
            episoden.append(" ");
        }
        episoden.append(Controller.stats.getObject("dIWHaveToBeAdded").toString());
        return episoden.toString();
    }
    
    private void setPossibleAnimeList(String name)
    {
    	possibleAnimes.clear();
    	for(String serienTitle : gui.getController().getSerien().getAllSerienTitle())
    	{
    		if(name.trim().equalsIgnoreCase(serienTitle.trim()))
    		{
    			possibleAnimes.add(serienTitle);
    			possibleAnimes.add(SERIE_CONNECTION_NEW);
    			possibleAnimes.add(SERIE_CONNECTION_SELF);
            	possibleAnimeList.setListData(possibleAnimes.toArray(new String[possibleAnimes.size()]));
            	possibleAnimeList.setSelectedIndex(0);
    			return;
    		}
            else if (SuchAbfrage.compareTitel(name.toLowerCase(), serienTitle))
            {
                possibleAnimes.add(serienTitle);
            }
    	}
    	int loop = 0;
    	while(possibleAnimes.size() == 0 && loop < 3)
    	{
    		for(String serienTitle : gui.getController().getSerien().getAllSerienTitle())
        	{
    			for(int i = 4; i > 0; i--)
    			{
    				if(name.length() - i == 1)
    				{
    					break;
    				}
        			if(SuchAbfrage.compareTitel(name.substring(0, name.length()-i), serienTitle))
        			{
            			possibleAnimes.add(serienTitle);
            			break;
        			}
    			}
        	}
    		loop ++;
    	}
		possibleAnimes.add(SERIE_CONNECTION_NEW);
		possibleAnimes.add(SERIE_CONNECTION_SELF);
    	possibleAnimeList.setListData(possibleAnimes.toArray(new String[possibleAnimes.size()]));
    	if(possibleAnimes.size() == 2)
    	{
    		possibleAnimeList.setSelectedIndex(0);
    	}
    }

    
    private void sortAnimeList()
    {
    	animeFileList = new ArrayList<String>(animeListe);
    	if(sortByNameRadionButton != null && sortByAmountRadionButton.isSelected())
    	{
            Collections.sort(animeFileList, new Comparator<String>()
            {
                public int compare(String anime, String anime2)
                {
                    return serienEpisodeList.get(anime2) - serienEpisodeList.get(anime);
                }
            });
    	}
    	else
    	{
    		Collections.sort(animeFileList);
    	}
        if(animeAuswahl != null)
        {
        	animeAuswahl.setListData(animeFileList.toArray(new String[animeFileList.size()]));
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(directoryButton))
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(pfad));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(this);
            if (fileChooser.getSelectedFile() != null)
            {
                pfad = fileChooser.getSelectedFile().getAbsolutePath();
                directoryTextField.setText(pfad);
                refreshList();
            }
        }
        else if (e.getSource().equals(addNameButton))
        {
            generateHashMap();
            setFileName(true);
            copyPastedInfos.setText("");
            refreshList();
        }
        else if (e.getSource().equals(noTitleButton))
        {
            setFileName(false);
        }
        else if (e.getSource().equals(cleanButton))
        {
            DateiImportHelper.cleanUpNamesInFolder(pfad);
            refreshList();
        }
        else if (e.getSource().equals(refreshButton))
        {
            refreshList();
        }
        else if (e.getSource().equals(closeButton))
        {
        	for(SerieVO serie : newAnimes)
        	{
        		new AddDialog(gui, serie);
        	}
        	newAnimes.clear();
            if (selfAddEpisodes.size() != 0 || autoAddEpisodes.size() != 0)
            {
                JOptionPane.showMessageDialog(null, getWarningMessage(), Controller.stats.getObject("dIWGeneral")
                        .toString(), JOptionPane.INFORMATION_MESSAGE);
                autoAddEpisodes.clear();
                selfAddEpisodes.clear();
            }
            gui.getController().fillSerienList(false);
            this.dispose();
        }
        else if(e.getSource().equals(sortByAmountRadionButton) || e.getSource().equals(sortByNameRadionButton))
		{
        	initAnimeAuswahlListe();
		}
        else if (e.getSource().equals(linkAnisearchButton))
        {
            GuiHelper.openBrower(EpisodesHelper.SEARCH_LINK_ANISEARCH
                    + GuiHelper.makeInternetParam(animeAuswahl.getSelectedValue().toString()));
        }
        else if (e.getSource().equals(linkFansubButton))
        {
            GuiHelper.openBrower(EpisodesHelper.SEARCH_LINK_FANSUB
                    + GuiHelper.makeInternetParam(animeAuswahl.getSelectedValue().toString()));
        }
        else if (e.getSource().equals(renameButton))
        {
            new SerieRenameDialog(this, animeAuswahl.getSelectedValue().toString(), pfad);
        }
    }
    
    @Override
    public void setVisible(boolean b)
    {
    	super.setVisible(b);
        gui.getController().getSerien().save();
        refreshList();
    }

    void refreshList()
    {
        initAnimeAuswahlListe();
        animeAuswahl.setListData(animeFileList.toArray(new String[animeFileList.size()]));
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            JButton tempButton = (JButton) e.getSource();
            tempButton.doClick();
        }
    }


    public void keyReleased(KeyEvent e)
    {
        // Auto-generated method stub
    }


    public void keyTyped(KeyEvent e)
    {
        // Auto-generated method stub
    }


    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if (animeAuswahl.getSelectedValue() != null)
        {
            anzahlEpis.setText(Controller.stats.getObject("episodesToRename").toString()
                    + serienEpisodeList.get(animeAuswahl.getSelectedValue().toString()));
            noTitleButton.setEnabled(true);
            addNameButton.setEnabled(true);
            linkAnisearchButton.setEnabled(true);
            linkFansubButton.setEnabled(true);
            renameButton.setEnabled(true);
            setPossibleAnimeList(animeAuswahl.getSelectedValue().toString());
        }
        else
        {
            noTitleButton.setEnabled(false);
            addNameButton.setEnabled(false);
            linkAnisearchButton.setEnabled(false);
            linkFansubButton.setEnabled(false);
            renameButton.setEnabled(false);
        }
    }
}
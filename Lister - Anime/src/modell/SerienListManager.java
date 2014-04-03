package modell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import util.SearchSerienVO;
import util.SerieVO;
import view.db.InfoDBDialog;
import controller.Controller;
import controller.Sorter;
import controller.SuchAbfrage;


/**
 * Hier werden die Daten der Serien geladen, gespeichert und für die einzelnen GUI-Elemente aufbereitet.
 * 
 * @author Rofus
 */
public class SerienListManager
{
    private static final String FILE_DELIMITER_GENRE = "--genre--";
    private static final String FILE_DELIMITER_LANGUAGE = "--language--";
    private static final String FILE_DELIMITER_TIME = "--time--";
    private static final String FILE_DELIMITER_SERIE = "--serie--";
    private static final String FILE_DELIMITER_TAB = "\t";
    private static final String FILE_DELIMITER_EQUAL = "=";
    
    private SearchAlgorithm suche = new SearchAlgorithm();
    private List<SerieVO> allSeries, searchSerien;
    private List<String> serienTitle;
    private Integer[] informationArray;
    private Map<String, Integer> languageMap = new HashMap<String, Integer>(), genreMap = new HashMap<String, Integer>();
    private String directory, time;


    /**
     * Liest das Textfile aus und verarbeitet die Daten. Die erste Zeile wird als Datum gelesen und die der Rest als
     * Datensätzen.
     * 
     * @return Ein ListArray mit allen Datensätzen in der Datenbank
     */
    public boolean loadAllSeries(String directory)
    {
        this.directory = directory;
        if (allSeries == null)
        {
            allSeries = new ArrayList<SerieVO>();
            serienTitle = new ArrayList<String>();
        }
        else
        {
            allSeries.clear();
            serienTitle.clear();
        }
        FileReader fr;
        String readFileLine;
        try
        {
            fr = new FileReader(directory);
            BufferedReader br = new BufferedReader(fr);
            if(br.ready())
            {
            	readFileLine = br.readLine();
            	if(readFileLine.equals(FILE_DELIMITER_LANGUAGE))
            	{
        			readFileLine = br.readLine();
            		while(br.ready() && !readFileLine.equalsIgnoreCase(FILE_DELIMITER_GENRE))
            		{
            			putIntoLanguageGenreMap(readFileLine);
            			readFileLine = br.readLine();
            		}
            	}
            	if(readFileLine.equals(FILE_DELIMITER_GENRE))
            	{
        			readFileLine = br.readLine();
            		while(br.ready() && !readFileLine.equalsIgnoreCase(FILE_DELIMITER_TIME))
            		{
            			putIntoGenreMap(readFileLine);
            			readFileLine = br.readLine();
            		}
            	}
            	if (readFileLine.equals(FILE_DELIMITER_TIME))
                {
                	readFileLine = br.readLine();
                    time = readFileLine;
                	readFileLine = br.readLine();
                }
            	if(readFileLine.equals(FILE_DELIMITER_SERIE))
            	{
                    while (br.ready())
                    {
                        readFileLine = br.readLine();
                        addSerieIntoList(readFileLine.split(FILE_DELIMITER_TAB));
                    }
            	}
            }
            br.close();
        }
//        catch (FileNotFoundException e)
//        {
//        	e.printStackTrace();
//            JOptionPane.showMessageDialog(null, Controller.stats.getObject("sLMWNoDataMsg").toString(),
//                    Controller.stats.getObject("sLMWNoData").toString(), JOptionPane.ERROR_MESSAGE);
//        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (allSeries.size() != 0)
        {
            Sorter.sortByTitle(allSeries);
            return true;
        }
        return true;
//        return false;
    }

    
    private void putIntoGenreMap(String genreLine)
    {
    	String[] genreMapping = genreLine.split(FILE_DELIMITER_EQUAL);
    	genreMap.put(genreMapping[1], new Integer(genreMapping[0]));
    }

    
    private void putIntoLanguageGenreMap(String languageLine)
    {
    	String[] languageMapping = languageLine.split(FILE_DELIMITER_EQUAL);
    	languageMap.put(languageMapping[1], new Integer(languageMapping[0]));
    }
    
    private void addSerieIntoList(String[] input)
    {
        SerieVO serie;
        serie = new SerieVO();
        serie.setTitle(input[0]);
        serie.setEpisoden(input[1]);
        serie.setEpisodesTotal(new Integer(input[2]));
        serie.setLanguage(getLanguageById(input[3]));
        serie.setGenre(getGenreById(input[4]));
        if(input.length == 6)
            serie.setLent(input[5]);
        serienTitle.add(serie.getTitle());
        allSeries.add(serie);
    }
    
    private String getLanguageById(String languageId)
    {
    	for(String language : languageMap.keySet())
    	{
    		if(languageMap.get(language).equals(new Integer(languageId)))
    		{
    			return language;
    		}
    	}
    	return "";    
    }
    
    private String getGenreById(String genreId)
    {
    	for(String genre : genreMap.keySet())
    	{
    		if(genreMap.get(genre).equals(new Integer(genreId)))
    		{
    			return genre;
    		}
    	}
    	return "";    
    }


    /**
     * Berechnet wieviele Folgen und Serien gespeichert sind und sortiert nach Sprache und nach Ort.
     * 
     * @return IntArray mit Informationen wieviele Folgen auf welcher Sprache und Wo sie sind. Genauso wie wieviele
     *         Serien wo und auf welcher Sprache sind.
     */
    @SuppressWarnings("boxing")
    public Integer[] getDatabaseInfos(String type)
    {
        List<String> liste;
        if (type.equals(InfoDBDialog.LANGUAGE))
        {
            liste = getLanguageListe();
        }
        else if (type.equals(InfoDBDialog.GENRE))
        {
            liste = getGenreListe();
        }
        else if (type.equals(InfoDBDialog.EPISODES_CAT))
        {
            liste = getEpisodeListe();
        }
        else
        {
            liste = getLanguageListe();
        }
        int listSize = liste.size();
        informationArray = new Integer[liste.size() * 3];
        for (int index = 0; index <= informationArray.length - 1; index++)
        {
            informationArray[index] = 0; // Initialisieren des IntegerArrays
        }
        for (SerieVO s : allSeries)
        {
            int i = 0;
            for (String languageString : liste)
            {
                if (s.getInformationByType(type).equals(languageString)
                        && !languageString.equals(Controller.stats.getObject("all").toString()))
                {
                    informationArray[i] += 1;
                    informationArray[i + listSize] += existingEpisodes(s.getEpisodes());
                }
                i++;
            }
        }
        if (type.equals(InfoDBDialog.EPISODES_CAT))
        {
            setCompleteInteger(informationArray);
        }
        // Anzahl Episoden geteilt durch Serien
        for (int index = listSize * 3 - 1; index >= listSize * 2; index--)
        {
            informationArray[0] += informationArray[index - listSize * 2];
            informationArray[listSize] += informationArray[index - listSize];
            informationArray[index] = (informationArray[index - listSize])
                    / (informationArray[index - listSize * 2]);
        }
        // Korrektur für gesamtstatistik
        informationArray[0] /= 2;
        informationArray[listSize] /= 2;
        return informationArray;
    }


    /**
     * Füllt den IntegerArray mit den Anzahl von Episoden die Vorhanden sind ab.
     * 
     * @param toComplete
     */
    @SuppressWarnings("boxing")
    private void setCompleteInteger(Integer[] toComplete)
    {
        for (SerieVO s : allSeries)
        {
            if (s.isComplete())
            {
                toComplete[3] += 1;
                toComplete[8] += existingEpisodes(s.getEpisodes());
            }
            else
            {
                toComplete[4] += 1;
                toComplete[9] += existingEpisodes(s.getEpisodes());
            }
        }
        toComplete[0] = 0 - toComplete[3] - toComplete[4];
        toComplete[5] = 0 - toComplete[8] - toComplete[9];
    }


    /**
     * @param berechnung
     * @return Anzahl Epis die vorhanden sind für diese Serie.
     */
    public static int existingEpisodes(String berechnung)
    {
        String[] tempzahlen = null;
        int temp = 0;
        if (berechnung.contains("+"))
        {
            tempzahlen = berechnung.split("\\+");
            for (int i = 0; i < tempzahlen.length; i++)
            {
                temp += SuchAbfrage.sum(tempzahlen[i].trim());
            }
        }
        else
        {
            temp = SuchAbfrage.sum(berechnung);
        }
        return temp;
    }



    /**
     * Erhält zwei SerieObjekte und ändert den Eintrag im textfile.
     * 
     * @param newSerie
     * @param oldSerie
     */
    public void updateSerie(SerieVO newSerie, SerieVO oldSerie)
    {
        for (SerieVO serie : allSeries)
        {
            if (serie.equals(oldSerie))
            {
                allSeries.remove(serie);
                break;
            }
        }
        addSerie(newSerie);
    }


    /**
     * Löscht das ausgewählte Objekt aus der Datenbank.
     * 
     * @param oldSerie
     *            SerienObjekt, das gelöscht werden soll.
     */
    public void deleteSerie(SerieVO oldSerie)
    {
        for (SerieVO serie : allSeries)
        {
            if (serie.equals(oldSerie))
            {
                allSeries.remove(serie);
                break;
            }
        }
        save();
    }


    public void save(String tdirection)
    {
        this.directory = tdirection;
        save();
    }

    /**
     * converting from old filesystem to new
     */
    private void setMaps()
    {
    	Set<String> genreSet = new HashSet<String>(), languageSet = new HashSet<String>(); 
    	for (SerieVO serie : allSeries)
    	{
    		genreSet.add(serie.getGenre());
    		languageSet.add(serie.getLanguage());
    	}
    	languageMap = new HashMap<String, Integer>();
    	for(String language : languageSet)
    	{
    		languageMap.put(language, languageMap.size());
    	}
    	genreMap = new HashMap<String, Integer>();
    	for(String genre : genreSet)
    	{
    		genreMap.put(genre, genreMap.size());
    	}
    }

    /**
     * Speichert die Daten die Im Array auf dem Textfiel ab. Erste Zeile ist das Aktuelle Datum, der Rest sind die
     * Datenbankeinträge.
     */
    public void save()
    {
        Sorter.sortByTitle(allSeries);
        try
        {
            time = new Date(System.currentTimeMillis()) + " " + new Time(System.currentTimeMillis());
            BufferedWriter writer = new BufferedWriter(new FileWriter(directory));
            setMaps(); //Decomment for converting from old to new filesystem
            writer.write(FILE_DELIMITER_LANGUAGE);
            writer.newLine();
            for(String language : languageMap.keySet())
            {
            	writer.write(languageMap.get(language)+ FILE_DELIMITER_EQUAL + language );
                writer.newLine();
            }
            writer.write(FILE_DELIMITER_GENRE);
            writer.newLine();
            for(String genre : genreMap.keySet())
            {
            	writer.write(genreMap.get(genre) + FILE_DELIMITER_EQUAL + genre);
                writer.newLine();
            }
            writer.write(FILE_DELIMITER_TIME);
            writer.newLine();
            writer.write(time);
            writer.newLine();
            writer.write(FILE_DELIMITER_SERIE);
            writer.newLine();
            for (SerieVO s : allSeries)
            {
                writer.write(s.toString() + FILE_DELIMITER_TAB
                        + languageMap.get(s.getLanguage()) + FILE_DELIMITER_TAB 
                        + genreMap.get(s.getGenre()) + FILE_DELIMITER_TAB 
                        + s.getLent());
                writer.newLine();
            }
            writer.close();
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }

    /**
     * @param newSerie
     *            SerienObjekt das in die Datenbank hinzugefügt werden soll.
     * @return True falls die übergebene Serie in die Datenbank gespeichert wurde.
     */
    public boolean addSerie(SerieVO newSerie)
    {
        for (SerieVO s : allSeries)
        {
            if (s.getTitle().equals(newSerie.getTitle()))
            {
                if (s.equals(newSerie))
                {
                    JOptionPane.showMessageDialog(null, Controller.stats.getObject("sLMWSerieExistsMsg").toString(),
                            Controller.stats.getObject("sLMWSerieExists").toString(), JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }
        allSeries.add(newSerie);
        serienTitle.add(newSerie.getTitle());
        save();
        return true;
    }


    /**
     * Ruft das SuchAlgorithmus auf und übergibt die gesuchte Serie. Falls keine Daten gesucht werden. Werden alle
     * Serien die in der Datenbank eingetragen sind übergeben.
     * 
     * @param serie
     *            Gesuchte Serie
     * @return Liste mit allen Serien die mit der gesuchten Serie übereinstimmen, oder Liste mit allen Serien.
     */
    public List<SerieVO> searchList(SearchSerienVO serie)
    {
        searchSerien = new ArrayList<SerieVO>();
        if (serie.isEmpty())
        {
            searchSerien = allSeries;
        }
        else
        {
            searchSerien = searchSerie(serie);
        }
        return searchSerien;
    }


    /**
     * Ruft die suche-Methode von der Suchalgorithmus Klasse auf und übergibt im die gesuchte und die zu vergleichende
     * Serie.
     * 
     * @param serie
     *            Serienobjekt mit den gesuchten Daten.
     * @return Liste mit allen Serien die mit der gesuchten Serie übereinstimmen.
     */
    private List<SerieVO> searchSerie(SearchSerienVO serie)
    {
        suche.resetSearchSerien();
        for (SerieVO gespeicherteSerie : allSeries)
        {
            suche.suche(gespeicherteSerie, serie);
        }
        return suche.getSearchSerien();
    }


    /**
     * @return Alle Serien die in der Datenbank sind.
     */
    public List<SerieVO> getAllSerien()
    {
        return allSeries;
    }
    
    /**
     * @return Alle Serientitel die in der Datenbank sind.
     */
    public List<String> getAllSerienTitle()
    {
        return serienTitle;
    }


    /**
     * @return ein StringArray mit allen Sprachen die aus der Datenbank gelesen wurden.
     */
    public List<String> getLanguageListe()
    {
        List<String> tempList = new ArrayList<String>();
    	for(String language : languageMap.keySet())
    	{
    		tempList.add(language);
    	}
        Collections.sort(tempList, new Comparator<String>()
        {

            public int compare(String o1, String o2)
            {
                return o1.compareTo(o2);
            }
        });
        tempList.remove(Controller.stats.getObject("all").toString());
        tempList.add(0, Controller.stats.getObject("all").toString());
        return tempList;
    }


    public List<String> getEpisodeListe()
    {
    	List<String> tempList = new ArrayList<String>();
    	tempList.add(Controller.stats.getObject("all").toString());
    	tempList.add(Controller.stats.getObject("lessThan26").toString());
    	tempList.add(Controller.stats.getObject("moreThan26").toString());
    	tempList.add(Controller.stats.getObject("slmComplete").toString());
    	tempList.add(Controller.stats.getObject("slmIncomplete").toString());
    	return tempList;
    }


    /**
     * @return ein StringArray mit allen Genres die aus der Datenbank gelesen wurden.
     */
    public List<String> getGenreListe()
    {
        List<String> tempList = new ArrayList<String>();
    	for(String genre : genreMap.keySet())
    	{
    		tempList.add(genre);
    	}
        Collections.sort(tempList, new Comparator<String>()
        {

            public int compare(String o1, String o2)
            {
                return o1.compareTo(o2);
            }
        });
        tempList.remove(Controller.stats.getObject("all").toString());
        tempList.add(0, Controller.stats.getObject("all").toString());
        return tempList;
    }

    public void clearSerien()
    {
        allSeries.clear();
    }

    /**
     * Addiert die neue Sprache in die Liste mit allen Sprachen.
     * 
     * @param newGenre
     */
    public void setLangaugeListe(String newLanguage)
    {
        languageMap.put(newLanguage, languageMap.size());
    }


    /**
     * Addiert den neuen Genre in die Liste mit allen Genres.
     * 
     * @param newGenre
     */
    public void setGenreListe(String newGenre)
    {
        genreMap.put(newGenre, genreMap.size());
    }


    /**
     * @return Die einen String mit der Zeit und dem Datum der letzten Änderung der Datenbank.
     */
    public String getTime()
    {
        return time;
    }


    /**
     * @return Liste mit den Suchresultaten.
     */
    public List<SerieVO> getSearchSerien()
    {
        return searchSerien;
    }
}
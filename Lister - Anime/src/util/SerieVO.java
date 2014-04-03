package util;

import view.db.InfoDBDialog;
import controller.Controller;


/**
 * @author srzzco Serien-Klasse mit den Attriubuten die eine Serie braucht.
 */
public class SerieVO
{

    private String title = null;
    private String episodes = null;
    private Integer episodesTotal = null;
    private String genre = null;
    private String language = null;
    private String lent = "";


    public String getInformationByType(String type)
    {
        if (type.equals(InfoDBDialog.LANGUAGE))
        {
            return language;
        }
        else if (type.equals(InfoDBDialog.GENRE))
        {
            return genre;
        }
        else if (type.equals(InfoDBDialog.EPISODES_CAT))
        {
            if (episodesTotal.intValue() > 26)
            {
                return Controller.stats.getObject("moreThan26").toString();
            }
            return Controller.stats.getObject("lessThan26").toString();
        }
        return "";
    }


    public String getLent()
    {
        return lent;
    }


    public void setLent(String lent)
    {
        this.lent = lent;
    }


    public String getLanguage()
    {
        return language;
    }


    public void setLanguage(String language)
    {
        this.language = language;
    }


    public String getEpisodes()
    {
        return episodes;
    }


    public void setEpisoden(String ep)
    {
        this.episodes = ep;
    }


    public String getGenre()
    {
        return genre;
    }


    public void setGenre(String genre)
    {
        this.genre = genre;
    }


    public Integer getEpisodesTotal()
    {
        return episodesTotal;
    }


    public void setEpisodesTotal(Integer gesamt)
    {
        this.episodesTotal = gesamt;
    }


    public String getTitle()
    {
        return title;
    }


    public void setTitle(String titel)
    {
        this.title = titel;
    }


    public boolean isEasyAddOne()
    {
        return isEasyEditable() && (episodes.equals("1") || (episodes.split("-").length == 2 && !isComplete()));
    }


    public boolean isEasyRemoveOne()
    {
        return isEasyEditable() && episodes.split("-").length == 2;
    }


    public boolean isEasyEditable()
    {
        if ((episodes.equals("1") && !isComplete() || episodes.split("-").length == 2
                && EpisodesHelper.isNumber(episodes.split("-")[0]) && EpisodesHelper.isNumber(episodes.split("-")[1]) || isComplete()
                && !episodes.equals("1")))
        {
            return true;
        }
        return false;
    }


    public boolean isComplete()
    {
        return isComplete(episodes, episodesTotal.toString());
    }


    /**
     * Analysiert den String und berechnet mit der Methode zusammenzaehlen den Wert des Strings der gesucht wird.
     * 
     * @return true falls die die gespeicherte Serie vollständig ist.
     */
    @SuppressWarnings("boxing")
    public static boolean isComplete(String containingEpisodes, String totalEpisodes)
    {
        String[] tempzahlen = null;
        int temp = 0;
        if (containingEpisodes.contains("+"))
        {
            tempzahlen = containingEpisodes.split("\\+");
            String empty = "";
            String space = " ";
            for (int i = 0; i < tempzahlen.length; i++)
            {
                tempzahlen[i] = tempzahlen[i].replaceAll(space, empty);
                temp += EpisodesHelper.addUpNumbers(tempzahlen[i]);
            }
        }
        else
        {
            temp = EpisodesHelper.addUpNumbers(containingEpisodes);
        }
        return temp == new Integer(totalEpisodes);
    }


    @Override
    public String toString()
    {
        return title + "\t" + episodes + "\t" + episodesTotal;
    }
    
    public boolean equals(Object toCompare)
    {
        if(this.getClass().equals(toCompare.getClass()))
        {
            SerieVO compareSerie = (SerieVO) toCompare;
            return title.equals(compareSerie.title) 
                    && episodes.equals(compareSerie.episodes)
                    && episodesTotal.equals(compareSerie.episodesTotal) 
                    && genre.equals(compareSerie.genre)
                    && language.equals(compareSerie.language);
        }
        return false;
    }
    
}
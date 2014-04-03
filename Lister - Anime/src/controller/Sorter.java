package controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import util.SerieVO;

import modell.SerienListManager;


/**
 * Verschiedene Comparator-Klassen zum sortieren der SerienListe.
 * 
 * @author Rofus
 */
public class Sorter
{

    public static List<SerieVO> sortByTitle(List<SerieVO> serienListe)
    {
        return sortByTitle(serienListe, false);
    }

    public static List<SerieVO> sortByTitle(List<SerieVO> serienListe, boolean order)
    {
        if (!order)
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                    return serie1.getTitle().toLowerCase().compareTo(serie2.getTitle().toLowerCase());
                }
            });
        else
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                    return serie2.getTitle().toLowerCase().compareTo(serie1.getTitle().toLowerCase());
                }
            });
        return serienListe;
    }


    public static List<SerieVO> sortByEpisodes(List<SerieVO> serienListe, boolean order)
    {
        if (!order)
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                	int epis1 = SerienListManager.existingEpisodes(serie1.getEpisodes());
                	int epis2 = SerienListManager.existingEpisodes(serie2.getEpisodes());
                    if (epis1 == epis2)
                    {
                        return serie1.getTitle().toLowerCase().compareTo(serie2.getTitle().toLowerCase());
                    }
                	if(epis1 < epis2)
                	{
                		return -1;
                	}
                	return 1;
                }
            });
        else
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                	int epis1 = SerienListManager.existingEpisodes(serie1.getEpisodes());
                	int epis2 = SerienListManager.existingEpisodes(serie2.getEpisodes());
                    if (epis1 == epis2)
                    {
                        return serie1.getTitle().toLowerCase().compareTo(serie2.getTitle().toLowerCase());
                    }
                	if(epis1 < epis2)
                	{
                		return 1;
                	}
                	return -1;
                }
            });
        return serienListe;
    }


    public static List<SerieVO> sortByEpisodeTotal(List<SerieVO> serienListe, boolean order)
    {
        if (!order)
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                    if (serie1.getEpisodesTotal().compareTo(serie2.getEpisodesTotal()) == 0)
                    {
                        return serie1.getTitle().toLowerCase().compareTo(serie2.getTitle().toLowerCase());
                    }
                    return serie1.getEpisodesTotal().compareTo(serie2.getEpisodesTotal());
                }
            });
        else
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                    if (serie2.getEpisodesTotal().compareTo(serie1.getEpisodesTotal()) == 0)
                    {
                        return serie2.getTitle().toLowerCase().compareTo(serie1.getTitle().toLowerCase());
                    }
                    return serie2.getEpisodesTotal().compareTo(serie1.getEpisodesTotal());
                }
            });
        return serienListe;
    }


    public static List<SerieVO> sortByLanguage(List<SerieVO> serienListe, boolean order)
    {
        if (!order)
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                    if (serie1.getLanguage().toLowerCase().compareTo(serie2.getLanguage().toLowerCase()) == 0)
                    {
                        return serie1.getTitle().toLowerCase().compareTo(serie2.getTitle().toLowerCase());
                    }
                    return serie1.getLanguage().toLowerCase().compareTo(serie2.getLanguage().toLowerCase());
                }
            });
        else
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                    if (serie2.getLanguage().toLowerCase().compareTo(serie1.getLanguage().toLowerCase()) == 0)
                    {
                        return serie2.getTitle().toLowerCase().compareTo(serie1.getTitle().toLowerCase());
                    }
                    return serie2.getLanguage().toLowerCase().compareTo(serie1.getLanguage().toLowerCase());
                }
            });
        return serienListe;
    }


    public static List<SerieVO> sortByGenre(List<SerieVO> serienListe, boolean order)
    {
        if (!order)
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                    if (serie1.getGenre().toLowerCase().compareTo(serie2.getGenre().toLowerCase()) == 0)
                    {
                        return serie1.getTitle().toLowerCase().compareTo(serie2.getTitle().toLowerCase());
                    }
                    return serie1.getGenre().toLowerCase().compareTo(serie2.getGenre().toLowerCase());
                }
            });
        else
            Collections.sort(serienListe, new Comparator<SerieVO>()
            {

                public int compare(SerieVO serie1, SerieVO serie2)
                {
                    if (serie2.getGenre().toLowerCase().compareTo(serie1.getGenre().toLowerCase()) == 0)
                    {
                        return serie2.getTitle().toLowerCase().compareTo(serie1.getTitle().toLowerCase());
                    }
                    return serie2.getGenre().toLowerCase().compareTo(serie1.getGenre().toLowerCase());
                }
            });
        return serienListe;
    }
}

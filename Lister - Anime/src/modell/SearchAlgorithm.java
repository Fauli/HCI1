package modell;

import java.util.ArrayList;
import java.util.List;

import util.SearchSerienVO;
import util.SerieVO;

import controller.Controller;
import controller.SuchAbfrage;


/**
 * @author srzzco Klasse, das eine gesuchte Serie mit einer bestehenden Serien vergleicht und speichert bei
 *         Übereinstimmung die Serie.
 */
public class SearchAlgorithm
{

    private List<SerieVO> searchSerien;
    private SuchAbfrage suche;
    private boolean eintrag = false;


    public SearchAlgorithm()
    {
        searchSerien = new ArrayList<SerieVO>();
        suche = new SuchAbfrage();
    }


    /**
     * Ruft die einzelnen Suchalgorithmen auf falls Daten vorhanden sind.
     * 
     * @param gespeicherteSerie
     * @param gesuchteSerie
     *            SerienObjekt mit der gesuchtenSerie
     */
    public void suche(SerieVO gespeicherteSerie, SearchSerienVO gesuchteSerie)
    {
        eintrag = true;
        suche.setGespSerie(gespeicherteSerie);
        suche.setGesuSerie(gesuchteSerie);
        String EMPTY = "";
        if (!(gesuchteSerie.getTitle().equals(EMPTY)))
        {
            eintrag = suche.compareTitle();
        }
        if (gesuchteSerie.getComplete() && eintrag)
        {
            eintrag = suche.compareComplete();
        }
        if (gesuchteSerie.getIncomplete() && eintrag)
        {
            eintrag = !suche.compareComplete();
        }
        if (!(gesuchteSerie.getEpisodes().equals(EMPTY)) && eintrag)
        {
            eintrag = suche.compareCompleteness();
        }
        if (!(gesuchteSerie.getLanguage().equals(Controller.stats.getObject("all").toString())) && eintrag)
        {
            eintrag = suche.compareLanguage();
        }
        if (!(gesuchteSerie.getGenre().equals(Controller.stats.getObject("all").toString())) && eintrag)
        {
            eintrag = suche.compareGenre();
        }
        if (gesuchteSerie.isLent() && eintrag)
        {
            eintrag = !gespeicherteSerie.getLent().isEmpty();
        }
        if (gesuchteSerie.isNotLent() && eintrag)
        {
            eintrag = gespeicherteSerie.getLent().isEmpty();
        }
        if (eintrag)
            searchSerien.add(gespeicherteSerie);
    }


    public void resetSearchSerien()
    {
        searchSerien.clear();
    }

    public List<SerieVO> getSearchSerien()
    {
        return searchSerien;
    }
}

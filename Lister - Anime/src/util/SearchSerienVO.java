package util;

import view.main.MainWindow;


/**
 * @author srzzco SuchSerien-Klasse mit den Attributen um eine Suche nach Serien zu machen.
 */
public class SearchSerienVO
{

    private String title = null;
    private boolean complete = false;
    private boolean incomplete = false;
    private String episodes = null;
    private String genre = null;
    private String language = null;
    private boolean lent = false;
    private boolean notLent = false;


    /**
     * @param gui
     * @return Ein SuchSerienobject mit den gesuchten Werten.
     */
    public SearchSerienVO(MainWindow gui)
    {
        title = gui.getSearchTiteltf().getText().trim();
        complete = gui.getSearchComplete().isSelected();
        incomplete = gui.getSearchIncomplete().isSelected();
        lent = gui.getSearchLent().isSelected();
        notLent = gui.getSearchNotLent().isSelected();
        episodes = gui.getSearchFulltf().getText().trim();
        genre = gui.getSearchGenre();
        language = gui.getSearchLanguage();
    }


    public String getLanguage()
    {
        return language;
    }


    public boolean getComplete()
    {
        return complete;
    }


    public boolean getIncomplete()
    {
        return incomplete;
    }


    public String getGenre()
    {
        return genre;
    }


    public String getEpisodes()
    {
        return episodes;
    }


    public void setEpisodes(String episodes)
    {
        this.episodes = episodes;
    }


    public String getTitle()
    {
        return title;
    }


    public boolean isLent()
    {
        return lent;
    }


    public boolean isNotLent()
    {
        return notLent;
    }
    
    public boolean isEmpty()
    {
        return title.isEmpty() && !complete && !incomplete 
                && episodes.isEmpty() && genre.isEmpty() 
                && language.isEmpty();
    }
}
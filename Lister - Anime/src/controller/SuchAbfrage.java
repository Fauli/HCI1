package controller;

import util.EpisodesHelper;
import util.SearchSerienVO;
import util.SerieVO;


/**
 * Klasse mit Verschiedenen Suchalgorithmen für ein Serien-Objekt.
 * 
 * @author Rofus
 */
public class SuchAbfrage
{
    private SerieVO gespSerie;
    private SearchSerienVO gesuSerie;
    private static String EMTPY = "";


	/**
	 * @param searchTerm in lower case.
	 * @param comparteTerm
	 * @return
	 */
	public static boolean compareTitel(String searchTerm, String comparteTerm)
    {
        String searchedTitle = searchTerm;
        String savedTitle = comparteTerm.toLowerCase();
    	if (searchedTitle.charAt(0) == '_')
        {
    	    return searchedTitle.length() == 1  || 
    	                (searchedTitle.length() - 1 <= savedTitle.length()
    	              && savedTitle.startsWith(searchedTitle.substring(1)));
        }
        else if (searchedTitle.contains("*"))
        {
            searchedTitle = searchedTitle.replaceAll("\\*\\*", "\\*");
            if(searchedTitle.length() == 1)
                return true;
            savedTitle = replaceWhiteSpaceAndDash(savedTitle);
            if (searchedTitle.charAt(0) == '*')
            {
                searchedTitle = searchedTitle.substring(1, searchedTitle.length());
            }
            
            String[] searchArray = searchedTitle.split("\\*");
            int charAt = 0, counter = 0;
            for (String searchPart : searchArray)
            {
                searchedTitle = replaceWhiteSpaceAndDash(searchPart);
                for (; charAt <= savedTitle.length() - searchedTitle.length(); charAt++)
                {
                    if (savedTitle.substring(charAt, charAt + searchedTitle.length()).equalsIgnoreCase(searchedTitle))
                    {
                        counter++;
                        break;
                    }
                    if (charAt > savedTitle.length() - searchedTitle.length())
                    {
                        return false;
                    }
                }
            }
            return counter == searchArray.length;
        }
        else
        {
            savedTitle = replaceWhiteSpaceAndDash(savedTitle);
            searchedTitle = replaceWhiteSpaceAndDash(searchedTitle);
            for (int index = 0; index <= savedTitle.length() - searchedTitle.length(); index++)
            {
                if (savedTitle.substring(index, index + searchedTitle.length()).equals(searchedTitle))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Durchläuft den gespeicherten Titel und vergleicht den gesuchten Titel mit den Substrings des gespeicherten
     * Titels.
     * 
     * @return true falls der gesuchte Titel im Titel des gespeicherten Titel enthalten ist.
     */
    public boolean compareTitle()
    {
    	return SuchAbfrage.compareTitel(gesuSerie.getTitle().toLowerCase(), gespSerie.getTitle());
    }


    public static String replaceWhiteSpaceAndDash(String toReplace)
    {
        return toReplace.replaceAll(" ", EMTPY).replaceAll("-", EMTPY);
    }


    /**
     * Vergleicht den gesuchten Genre mit dem gesetzten Genre.
     * 
     * @return true falls die Genreas gleich sind
     */
    public boolean compareGenre()
    {
        return gesuSerie.getGenre().equals(gespSerie.getGenre());
    }


    /**
     * Vergleicht die gesuchte Sprach mit der gesetzten Sprache.
     * 
     * @return true falls die Sprache gleich ist
     */
    public boolean compareLanguage()
    {
        return gesuSerie.getLanguage().equals(gespSerie.getLanguage());
    }


    /**
     * Vergleicht den gesetzten mit den gesuchten Episoden Wert. Falls bei der Suche ein '<' oder '>' benutzt wird,
     * wird wird die Methode greaterAndLessThan aufgerufen.
     * 
     * @return true wenn die gesuchten Episoden gleich sind wie die gesetzen Episoden
     */
    public boolean compareCompleteness()
    {
        if (gesuSerie.getEpisodes().equals(gespSerie.getEpisodesTotal().toString()))
        {
            return true;
        }
        else if (gesuSerie.getEpisodes().contains("-"))
        {
            String[] suche = gesuSerie.getEpisodes().trim().split("-");
            if (suche.length == 2)
            {
                if (suche[0].equals("") && !suche[1].equals(""))
                {
                    gesuSerie.setEpisodes(suche[1]);
                    return compareComplete();
                }
                if(!EpisodesHelper.isNumber(suche[0].trim()) || !EpisodesHelper.isNumber(suche[1].trim()))
                    return false;
                
                return Integer.parseInt(suche[0].trim()) - 1 < gespSerie.getEpisodesTotal().intValue()
                    && Integer.parseInt(suche[1].trim()) + 1 > gespSerie.getEpisodesTotal().intValue();
            }
            else if (suche.length == 1 && !suche[0].equals(""))
            {
                gesuSerie.setEpisodes(suche[0]);
                return compareComplete();
            }
        }
        else if (!(gesuSerie.getEpisodes().equals(gespSerie.getEpisodesTotal().toString()))
                && gesuSerie.getEpisodes().length() > 1)
        {
            return greaterAndLessThan(gesuSerie.getEpisodes());
        }
        return false;
    }
    


    /**
     * Wandelt den String um so das '<' oder '>' zum berechnen benutzt werden kann.
     * 
     * @return true falls nach der Umrechnung der gespeicherte Wert im Suchradius des gesuchten Werts drin liegt.
     */
    @SuppressWarnings("boxing")
    private boolean greaterAndLessThan(String totalEpi)
    {
        String temp = "0", zeichen = "";
        char zero = '0', one = '1', two = '2', three = '3', four = '4', five = '5', six = '6', seven = '7', eight = '8', nine = '9';
        for (int i = 0; i <= totalEpi.length() - 1; i++)
        {
            char toCompare = totalEpi.charAt(i);
            if (toCompare == zero || toCompare == one || toCompare == two || toCompare == three || toCompare == four
                    || toCompare == five || toCompare == six || toCompare == seven || toCompare == eight
                    || toCompare == nine)
            {
                temp += toCompare;
            }
            // Es wird > gesetzt wenn der User < an erster oder > an zweiter Stelle eingibt
            else if (toCompare == '<' && temp.length() == 1 || toCompare == '>' && temp.length() > 1)
            {
                zeichen = ">";
            }
            // Es wird < gesetzt wenn der User > an erster oder < an an letzter Stelle eingibt
            else if (toCompare == '>' && temp.length() == 1 || toCompare == '<' && temp.length() > 1)
            {
                zeichen = "<";
            }
        }
        if (gespSerie.getEpisodesTotal() > new Integer(temp) && zeichen.equals("<"))
        {
            return true;
        }
        return gespSerie.getEpisodesTotal() < new Integer(temp) && zeichen.equals(">");
    }


    /**
     * Analysiert den String und berechnet mit der Methode zusammenzaehlen den Wert des Strings der gesucht wird.
     * 
     * @return true falls die die gespeicherte Serie vollständig ist.
     */
    @SuppressWarnings("boxing")
    public boolean compareComplete()
    {
        String[] tempzahlen = null;
        String berechnung = gespSerie.getEpisodes();
        int temp = 0;
        if (berechnung.contains("+"))
        {
            tempzahlen = berechnung.split("\\+");
            String EMPTY = "";
            String EMPTY2 = " ";
            for (int i = 0; i < tempzahlen.length; i++)
            {
                tempzahlen[i] = tempzahlen[i].replaceAll(EMPTY2, EMPTY);
                temp += sum(tempzahlen[i]);
            }
        }
        else
        {
            temp = sum(berechnung);
        }
        return temp == gespSerie.getEpisodesTotal();
    }


    /**
     * Erhölt einen String mit 2 Zahlen die mit einem Bindestrich verbunden sind. Teilt den String auf in zwei zahlen
     * und berechnet dann den Wert der zwei Zahlen zusammen.
     * 
     * @param berechne
     *            String mit zwei Zahlen die mit einem Bindestrich verbunden sind.
     * @return den Wert der beiden Zahlen im String.
     */
    public static int sum(String berechne)
    {
        if (!EpisodesHelper.isNumber(berechne))
        {
            for (int index = 0; index < berechne.length(); index++)
            {
                char nice = berechne.charAt(index);
                if (nice != '-' && nice != '+' && !EpisodesHelper.isNumber(nice))
                {
                    return 0;
                }
            }
        }
        if (berechne.contains("-"))
        {
            String[] tempzahlen = berechne.split("-");
            int grosseZahl = new Integer(tempzahlen[1].trim()).intValue();
            int kleineZahl = new Integer(tempzahlen[0].trim()).intValue();
            return grosseZahl - kleineZahl + 1;
        }
        return new Integer(berechne).intValue();
    }

    public void setGespSerie(SerieVO gespSerie) 
    {
		this.gespSerie = gespSerie;
	}

	public void setGesuSerie(SearchSerienVO gesuSerie) 
	{
		this.gesuSerie = gesuSerie;
	}
}
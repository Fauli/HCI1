package util;

/**
 * @author srzzco Klasse die die Methoden zur Überprüfung von Eingaben in einem GUI enthält.
 */
public class EpisodesHelper
{

    public static final String SEARCH_LINK_ANISEARCH = "http://anisearch.de/index.php?page=suche&mode=anime&qsearch=";
    public static final String SEARCH_LINK_FANSUB = "http://fan-sub.de/suchen.rhtml?suchen=";
    public static final String SEARCH_LINK_GOOGLE = "http://images.google.ch/images?q=";
    
    /**
     * Überprüft ob der String der übergeben wird eine gültige Eingabe für die Episoden ist.
     * 
     * @param epis
     * @return false falls die Eingabe für die vorhanden Episoden ungültig ist
     */
    public static boolean checkEpisodeInput(String epis)
    {
        String toCheck = epis.trim();
        if (toCheck.equals(""))
        {
            return false;
        }
        if (!isNumber(toCheck))
        {
            for (int index = 0; index < toCheck.length(); index++)
            {
                char nice = toCheck.charAt(index);
                if (nice != '-' && nice != '+' && !isNumber(nice) && nice != ' ')
                {
                    return false;
                }
            }
        }
        if ((toCheck.length() > 0 && toCheck.charAt(toCheck.length() - 1) == '+')
                || (toCheck.length() > 0 && toCheck.charAt(toCheck.length() - 1) == '-')
                || (toCheck.length() > 0 && toCheck.charAt(0) == '+')
                || (toCheck.length() > 0 && toCheck.charAt(0) == '-'))
        {
            return false;
        }
        String[] plusNumbers = toCheck.split("\\+");
        String[] dashNumbers;
        for (String plusNumber : plusNumbers)
        {
            if ((plusNumber.length() > 0 && plusNumber.charAt(plusNumber.length() - 1) == '+')
                    || (plusNumber.length() > 0 && plusNumber.charAt(plusNumber.length() - 1) == '-')
                    || (plusNumber.length() > 0 && plusNumber.charAt(0) == '+')
                    || (plusNumber.length() > 0 && plusNumber.charAt(0) == '-') || plusNumber.contains("--"))
            {
                return false;
            }
            dashNumbers = plusNumber.split("-");
            if (dashNumbers.length > 2)
            {
                return false;
            }
            for (String dashNumber : dashNumbers)
            {
                if ((dashNumber.length() > 0 && dashNumber.charAt(dashNumber.length() - 1) == '-')
                        || (dashNumber.length() > 0 && dashNumber.charAt(0) == '-'))
                {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean isNumber(String number)
    {
        try
        {
            new Integer(number);
            return true;
        }
        catch (NumberFormatException noNumber)
        {
            return false;
        }
    }


    /**
     * Checkt ob ein Char eine Zahl ist.
     * 
     * @param toCheckChar
     * @return True falls der übergebene Char eine Zahl ist.
     */
    public static boolean isNumber(char number)
    {
        int check = new Integer(number).intValue();
        return !(check > 58 || check < 47);
    }


    /**
     * @param newEpisode
     * @param episodeList
     * @return true if Episode is already added
     */
    public static boolean episodeAlreadyAdded(int newEpisode, String episodeList)
    {
        if (!episodeList.contains("-") && !episodeList.contains("\\+"))
        {
            return newEpisode == new Integer(episodeList).intValue();
        }
        else if (episodeList.split("-").length == 2)
        {
            if (newEpisode <= new Integer(episodeList.split("-")[1]).intValue()
                    && new Integer(episodeList.split("-")[0]).intValue() <= newEpisode)
            {
                return true;
            }
        }
        else if (episodeList.contains("\\+"))
        {
            String[] episodelistParts = episodeList.split("\\+");
            for (String epList : episodelistParts)
            {
                if (episodeAlreadyAdded(newEpisode, epList))
                {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @param episodeToAdd
     * @param episodesTotal
     * @param episodeList
     * @return true if new Episode isn't added yet and the new episode is in the range of the amount of episode of the
     *         serie.
     */
    public static boolean addingPossible(int episodeToAdd, Integer episodesTotal, String episodeList)
    {
        return (!episodeAlreadyAdded(episodeToAdd, episodeList)) && episodeToAdd <= episodesTotal.intValue();
    }


    /**
     * Erhält einen String mit 2 Zahlen die mit einem Bindestrich verbunden sind. Teilt den String auf in zwei zahlen
     * und berechnet dann den Wert der zwei Zahlen zusammen.
     * 
     * @param toAddUp
     * @return den Wert der beiden Zahlen im String.
     */
    public static int addUpNumbers(String toAddUp)
    {
        int result;
        if (toAddUp.contains("-"))
        {
            String[] tempzahlen = toAddUp.split("-");
            if (tempzahlen.length == 1 && isNumber(tempzahlen[0]))
            {
                return new Integer(tempzahlen[0]).intValue();
            }
            if (tempzahlen.length > 2)
            {
                return 0;
            }
            result = new Integer(tempzahlen[1]).intValue() - new Integer(tempzahlen[0]).intValue() + 1;
        }
        else
        {
            result = new Integer(toAddUp).intValue();
        }
        return result;
    }
}
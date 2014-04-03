package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import view.main.MainWindow;


public class DateiImportHelper
{
    public static String cleanUpNewFileName(String toCleanUp)
    {
        String returnString = toCleanUp;
        returnString = returnString.replace("\\", "");
        returnString = returnString.replace("/", "");
        returnString = returnString.replace(":", "");
        returnString = returnString.replace("*", "");
        returnString = returnString.replace("?", "");
        returnString = returnString.replace("\"", "");
        returnString = returnString.replace(">", "");
        returnString = returnString.replace("<", "");
        returnString = returnString.replace("|", "");
        return returnString;
    }


    public static Integer getEpisodeNrOfFileName(String toCheck)
    {
        if (toCheck.length() > 2 && toCheck.substring(toCheck.length() - 2, toCheck.length() - 1).equalsIgnoreCase("v"))
        {
            return new Integer(toCheck.substring(0, toCheck.length() - 2));
        }
        if(isNumber(toCheck))
            return new Integer(toCheck);
        return null;
    }


    public static SerieVO createSerieByCopyPaste(List<Integer> episodes, String title,
            String copyPaste, List<String> genres)
    {
        SerieVO newSerie = new SerieVO();
        newSerie.setTitle(title);
        if (episodes.size() == 1)
        {
            newSerie.setEpisoden(episodes.get(0).toString());
        }
        else
        {
            newSerie.setEpisoden("1-" + episodes.size());
        }
        newSerie = getGenreAndTotalOfTextFanSub(copyPaste, genres, newSerie);
        if(newSerie.getEpisodesTotal() != null)
            newSerie.setLanguage("German Sub");
        else
        {
            newSerie = getGenreAndTotalOfTextAnisearch(copyPaste, genres, newSerie);
            newSerie.setLanguage("English Sub");
        }
        if(newSerie.getEpisodesTotal() == null)
            return null;
        return newSerie;
    }

    private static SerieVO getGenreAndTotalOfTextFanSub(String copiedText, List<String> genres, SerieVO serie)
    {
        for (String line : copiedText.split("\n"))
        {
            if (line.length() >= 7)
            {
                if(line.startsWith("Anzahl "))
                {
                    serie.setEpisodesTotal(new Integer(line.split("\t")[1].trim()));
                }
                else if (line.startsWith("Genres "))
                {
                    line = line.substring(8);
                    for (String genre : line.split(", "))
                    {
                        if (genres.contains(genre))
                        {
                            serie.setGenre(genre);
                        }
                    }
                    break;
                }
            }
        }
        return serie;
    }

    private static SerieVO getGenreAndTotalOfTextAnisearch(String copiedText, List<String> genres, SerieVO serie)
    {
        for (String line : copiedText.split("\n"))
        {
            if (line.length() >= 7)
            {
                if(line.startsWith("Typ / Jahr"))
                {
                    serie.setEpisodesTotal(new Integer(line.split("\t")[1].split(" ")[1].trim()));
                }
                else if (line.startsWith("Genre "))
                {
                    line = line.substring(7);
                    for (String genre : line.split(", "))
                    {
                        if (genres.contains(genre))
                        {
                            serie.setGenre(genre);
                        }
                    }
                }
                else if(line.startsWith("Setting") && !MainWindow.EMPTY_STRING.equals(serie.getGenre()))
                {
                    line = line.substring(9);
                    for (String genre : line.split(", "))
                    {
                        if (genres.contains(genre))
                        {
                            serie.setGenre(genre);
                        }
                    }
                }
            }
        }
        return serie;
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
    
    public static void main(String[] args)
    {
        cleanUpNamesInFolder("F:\\Balthasar\\Incoming");
    }

    /**
     * Setzt alle Filenamen in den Standard ohne Titel.
     */
    public static void cleanUpNamesInFolder(String path)
    {
        File myDir = new File(path);
        if (myDir.exists() && myDir.isDirectory())
        {
            String filename;
            File newFileName;
            for (File file : myDir.listFiles())
            {
                if(file.isDirectory() || file.isHidden())
                    continue;
                filename = file.getName();
                if (filename.charAt(filename.length() - 4) == '.')
                {
                    filename = cleanUpFileName(filename);
                    if (filename != null && !filename.toLowerCase().startsWith("- "))
                    {
                        newFileName = new File(myDir.getPath() + "\\" + filename);
                        file.renameTo(newFileName);
                    }
                }
            }
        }
    }


    private static String cleanUpFileName(final String filename) 
    {
        String convertFilename = filename;
        // save filetyp
        String ending = convertFilename.subSequence(convertFilename.length() - 4, convertFilename.length()).toString();
        convertFilename = convertFilename.substring(0, convertFilename.length() - 4);

        // replace all separators with withspace
        convertFilename = convertFilename.replace("_", " ");
        convertFilename = convertFilename.replace(".", " ");
        convertFilename = convertFilename.replaceAll("  ", " ");
        
        // replace all [] and ()
        if (convertFilename.contains("["))
        {
            convertFilename = deleteContentBetweenParam(convertFilename, "\\[", ']');
        }
        if (convertFilename.contains("("))
        {
            convertFilename = deleteContentBetweenParam(convertFilename, "\\(", ')');
        }
        
        convertFilename = filenameCleanUp(convertFilename.trim());
        return convertFilename + ending;
    }

    private static String deleteContentBetweenParam(String toConvert, String start, char end)
    {
        StringBuffer convertedString = new StringBuffer();
        int temp = 0;
        if (toConvert.split(start)[0].length() > 0)
        {
            temp = 1;
            convertedString.append(toConvert.split(start)[0]);
        }
        for (int index = temp; index < toConvert.split(start).length; index++)
        {
            for (int i = index + 1; i < toConvert.split(start)[index].length(); i++)
            {
                if (toConvert.split(start)[index].charAt(i) == end)
                {
                    convertedString.append(toConvert.split(start)[index].replace(
                            toConvert.split(start)[index].subSequence(0, i), "").trim());
                }
            }
        }
        return convertedString.toString().replace(end, ' ').trim();
    }

    private static String filenameCleanUp(String file)
    {
        String filename = replaceVersion(file);
        String episodeNumber = getEpisodeNumber(filename, " ", false);
        filename = filename.split(episodeNumber)[0];
        boolean ova = false;
        if (file.contains(" OVA ") || filename.contains(" ova "))
        {
            filename = filename.replace(" ova ", " ").replace(" OVA ", " ");
            ova = true;
        }
        if (filename.contains(" ep ") || filename.contains(" EP ") || filename.contains(" Episode ")
                || filename.contains(" - ") || filename.contains("-Ep")
                || filename.contains("ep") || filename.contains("EP"))
        {
            filename = filename.replace(" ep ", " ").replace(" EP ", " ");
            filename = filename.replace("ep", "").replace("EP", "");
            filename = filename.replace("-Ep", " ").replace(" Episode ", " ").replace(" - ", " ");
        }
        String kind;
        if (ova)
        {
            kind = " - OVA";
        }
        else
        {
            kind = " - EP";
        }
        if(episodeNumber.length() == 1)
        {
            episodeNumber = "0" + episodeNumber;
        }
        filename = filename.trim() + kind + episodeNumber + " - ";
        return filename.trim() + " ";
    }


    private static String replaceVersion(String filename)
    {
        String returnString = filename;
        if (filename.contains("v2") || filename.contains("v3") || filename.contains("v4") || filename.contains("v5")
                || filename.contains("v6") || filename.contains("v7") || filename.contains("v8")
                || filename.contains("v9"))
        {
            returnString = returnString.replace("v2", "");
            returnString = returnString.replace("v3", "");
            returnString = returnString.replace("v4", "");
            returnString = returnString.replace("v5", "");
            returnString = returnString.replace("v6", "");
            returnString = returnString.replace("v7", "");
            returnString = returnString.replace("v8", "");
            returnString = returnString.replace("v9", "");
        }
        return returnString;
    }


    private static String getEpisodeNumber(final String toConvert, final String splitArg, boolean recursive)
    {
        String[] name = toConvert.split(splitArg);
        List<String> numbers = new ArrayList<String>();
        for (String split : name)
        {
            if (isNumber(split))
            {
                numbers.add(split);
            }
            else if (split.toLowerCase().startsWith("ep")
                    && isNumber(split.substring(2)))
            {
                numbers.add(split.substring(2));
            }
        }
        if (numbers.size() == 1)
        {
            return numbers.get(0);
        }
        else if (numbers.size() == 2)
        {
            if (numbers.get(1).equalsIgnoreCase("5"))
            {
                return numbers.get(0) + "." + numbers.get(1);
            }
            return numbers.get(1);
        }
        else if(numbers.size() == 0 && !recursive)
        {
            return getEpisodeNumber(toConvert.toLowerCase(), "ep", true);
        }
        return "";
    }

    public static String createFileName(String filename, String epiOVA, Map<Integer, String> episodeMap)
    {
        if (filename.contains(" - " + epiOVA))
        {
            String endung = getEndung(filename);
            String episodeTitel = episodeMap.get(new Integer(filename.substring(filename.length() - 9, filename
                    .length() - 7)));
            if (episodeTitel == null)
            {
                return filename;
            }
            StringBuffer newFileName = new StringBuffer();
            newFileName.append(filename.substring(0, filename.length() - 4));
            newFileName.append(episodeTitel);
            newFileName.append(endung);
            return newFileName.toString();
        }
        return filename;
    }


    private static String getEndung(String filename)
    {
        return "." + filename.subSequence(filename.length() - 3, filename.length());
    }


    public static String createFileNameNoTitle(String filename)
    {
        StringBuffer newFileName = new StringBuffer();
        newFileName.append(filename.split(" - ")[0])
        .append(" - ")
        .append(filename.split(" - ")[1])
        .append(filename.split(" - ")[2]);
        return newFileName.toString();
    }
}

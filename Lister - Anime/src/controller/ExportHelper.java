package controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.SerieVO;
import view.main.MainWindow;


/**
 * Handelt das exportieren der Daten
 * 
 * @author Rofus
 */
public class ExportHelper
{

    private static String SHORTCUT_TAB = "\t";
    private static String EXPORT_ALL = Controller.stats.getObject("menuExportAll").toString();
    private static String EXPORT_SEARCHED = Controller.stats.getObject("menuExportSearch").toString();
    private static String EXPORT_SELECTED = Controller.stats.getObject("menuExportSelection").toString();


    /**
     * Organisiert das Exportieren der Daten in ein File.
     * 
     * @param type
     * @param gui
     * @param selection
     * @param ort
     */
    public void makeExport(String type, MainWindow gui, boolean[] selection, String ort)
    {
        List<SerieVO> exportlist = createExportList(type, gui);
        if (exportlist != null && exportlist.size() > 1)
        {
            FileWriter filewriter;
            try
            {
                filewriter = new FileWriter(ort);
                BufferedWriter writer = new BufferedWriter(filewriter);
                writer.write(new Date(System.currentTimeMillis()) + " " + new Time(System.currentTimeMillis()));
                writer.newLine();
                for (SerieVO serie : exportlist)
                {
                    writeIntoFile(selection, writer, serie);
                    writer.newLine();
                }
                writer.close();
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            }
            catch (IOException e2)
            {
                e2.printStackTrace();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, Controller.stats.getObject("eHWErrorMsg").toString(), Controller.stats
                    .getObject("eHWError").toString(), JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * @param type
     * @param gui
     * @return Liste mit allen Elementen die der User ausgesucht hat.
     */
    private List<SerieVO> createExportList(String type, MainWindow gui)
    {
        if (type.equals(EXPORT_ALL))
        {
            return gui.getController().getSerien().getAllSerien();
        }
        else if (type.equals(EXPORT_SEARCHED))
        {
            return gui.getController().getSerien().getSearchSerien();
        }
        else if (type.equals(EXPORT_SELECTED))
        {
            List<SerieVO> tempList = gui.getController().getSerien().getAllSerien();
            List<SerieVO> exportlist = new ArrayList<SerieVO>();
            int[] selectedSeries = gui.getTable().getSelectedRows();
            if (gui.getTable().getSelectedRowCount() > 1)
            {
                for (int index : selectedSeries)
                {
                    exportlist.add(tempList.get(index));
                }
            }
            return exportlist;
        }
        return null;
    }


    /**
     * Setzt den BufferedString zusammen, je nach dem was der User eingegeben hat.
     * 
     * @param selection
     * @param br
     * @param serie
     * @throws IOException
     */
    private void writeIntoFile(boolean[] selection, BufferedWriter br, SerieVO serie) throws IOException
    {
        br.write(serie.getTitle());
        if (selection[1])
        {
            br.write(SHORTCUT_TAB + serie.getEpisodes());
        }
        if (selection[2])
        {
            br.write(SHORTCUT_TAB + serie.getEpisodesTotal().toString());
        }
        if (selection[3])
        {
            br.write(SHORTCUT_TAB + serie.getLanguage());
        }
        if (selection[4])
        {
            br.write(SHORTCUT_TAB + serie.getGenre());
        }
    }
}
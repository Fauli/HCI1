package modell;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import util.SerieVO;

import controller.Controller;
import controller.Sorter;


/**
 * @author Rafa TableModel-Klasse das die Daten für die JTable im GUI aufbereitet.
 */
@SuppressWarnings("serial")
public class SerieTableModel extends AbstractTableModel
{

    private boolean[] sortColumnDesc = new boolean[getColumnCount()];
    private List<SerieVO> serienList = new ArrayList<SerieVO>();
    private static String[] columnNames = new String[] { Controller.stats.getObject("title").toString(),
            Controller.stats.getObject("episodes").toString(), Controller.stats.getObject("total").toString(),
            Controller.stats.getObject("language").toString(), Controller.stats.getObject("genre").toString() };


    @Override
    public Class<?> getColumnClass(int column)
    {
        switch (column)
        {
            case 0:
                return new String().getClass();
            case 1:
                return new String().getClass();
            case 2:
                return new Integer(0).getClass();
            case 3:
                return new String().getClass();
            case 4:
                return new String().getClass();
        }
        return null;
    }


    /**
     * Sortier je nach gewählten Kolumne die Liste neu.
     * 
     * @param column
     * @param toSortList
     * @return sortierte Liste
     */
    public List<SerieVO> sortByColumn(int column, List<SerieVO> toSortList)
    {
        switch (column)
        {
            case 0:
                sortColumnDesc[0] = setAllOrderFalse(sortColumnDesc[0]);
                return Sorter.sortByTitle(toSortList, !sortColumnDesc[0]);
            case 1:
                sortColumnDesc[1] = setAllOrderFalse(sortColumnDesc[1]);
                return Sorter.sortByEpisodes(toSortList, !sortColumnDesc[1]);
            case 2:
                sortColumnDesc[2] = setAllOrderFalse(sortColumnDesc[2]);
                return Sorter.sortByEpisodeTotal(toSortList, !sortColumnDesc[2]);
            case 3:
                sortColumnDesc[3] = setAllOrderFalse(sortColumnDesc[3]);
                return Sorter.sortByLanguage(toSortList, !sortColumnDesc[3]);
            case 4:
                sortColumnDesc[4] = setAllOrderFalse(sortColumnDesc[4]);
                return Sorter.sortByGenre(toSortList, !sortColumnDesc[4]);
        }
        return toSortList;
    }


    private boolean setAllOrderFalse(boolean order)
    {
        sortColumnDesc[0] = false;
        sortColumnDesc[1] = false;
        sortColumnDesc[2] = false;
        sortColumnDesc[3] = false;
        sortColumnDesc[4] = false;
        return !order;
    }


    public SerieVO getSelectedSerie(int row)
    {
        return serienList.get(row);
    }


    public Object getValueAt(int row, int col)
    {
        SerieVO serie = serienList.get(row);
        switch (col)
        {
            case 0:
                return serie.getTitle();
            case 1:
                return serie.getEpisodes();
            case 2:
                return serie.getEpisodesTotal();
            case 3:
                return serie.getLanguage();
            case 4:
                return serie.getGenre();
        }
        return null;
    }


    /**
     * Aktualisiert den ArrayList mit allen Einträgen die angezeigt werden sollen.
     * 
     * @param data
     *            ArrayList mit allen gespeicherten Serien.
     */
    public void setData(List<SerieVO> data)
    {
        serienList = data;
    }


    public boolean[] getSortColumnDesc()
    {
        return sortColumnDesc;
    }


    public int getColumnCount()
    {
        return columnNames.length;
    }


    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }


    public int getRowCount()
    {
        return serienList.size();
    }
}
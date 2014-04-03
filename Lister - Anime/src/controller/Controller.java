package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;

import modell.SerieTableModel;
import modell.SerienListManager;
import util.EpisodesHelper;
import util.SearchSerienVO;
import util.SerieVO;
import view.helper.TableCellRenderer;
import view.helper.TableHeaderRenderer;
import view.main.MainWindow;
import view.serie.AddDialog;
import view.serie.DeleteDialog;
import view.serie.EditDialog;
import view.serie.InfoSerieDialog;
import view.serie.NewEditInfos;

public class Controller implements ActionListener, KeyListener, ItemListener, ListSelectionListener, MouseListener
{
    private MainWindow gui;
    private static String EMPTY_STRING = "";
    private MouseAdapter mouseAdapter; // benutzen, falls reagiert werden soll wenn Maus über table element
    private SerienTable serienTable;
    private SerieVO deleteOrChange = new SerieVO();
    private InfoSerieDialog serienDialog = null;
    private SerienListManager serien = new SerienListManager();
    private SerieTableModel serienModel = null;
    private boolean viewOnly = false;
    private int mouseoverIndex = -1;
    private JCheckBox temporaryCheckBox;
    public static ResourceBundle stats;


    public static void main(String[] args) throws FileNotFoundException
    {
        new Controller();
    }


    public Controller() throws FileNotFoundException
    {
        stats = ResourceBundle.getBundle("data/ResourceLanguage", ListerProperties.getLocale());
        serienModel = new SerieTableModel();
        if(!serien.loadAllSeries(ListerProperties.getDefaultProperties().getProperty("pfad")))
            System.exit(1);
        System.out.println(4);
        serienModel.setData(serien.getAllSerien());
        mouseAdapter = new MouseAdapter(this);
        serienTable = new SerienTable(new SerieTableModel(), this);
        gui = new MainWindow(this);
        fillSerienList(false);
        setTableInfos();
        System.out.println("n");
    }


    private void setTableInfos()
    {
        serienModel.getSortColumnDesc()[0] = true;
        gui.getTable().setModel(serienModel);
        gui.getTable().getColumn(serienModel.getColumnName(0)).setPreferredWidth(300);
        gui.getTable().getColumn(serienModel.getColumnName(1)).setPreferredWidth(110);
        gui.getTable().getColumn(serienModel.getColumnName(1)).setCellRenderer(new TableCellRenderer());
        gui.getTable().getColumn(serienModel.getColumnName(2)).setPreferredWidth(70);
        gui.getTable().getColumn(serienModel.getColumnName(2)).setCellRenderer(new TableCellRenderer());
        gui.getTable().getColumn(serienModel.getColumnName(3)).setPreferredWidth(100);
        gui.getTable().getColumn(serienModel.getColumnName(4)).setPreferredWidth(110);
        for (int i = 0; i < gui.getTable().getColumnCount() - 1; i++)
        {
            gui.getTable().getColumn(gui.getTable().getColumnName(i)).setResizable(false);
        }
        gui.getTable().getTableHeader().addMouseListener(this);
        gui.getTable().getTableHeader().setDefaultRenderer(new TableHeaderRenderer(serienModel));
        gui.getTable().getTableHeader().setReorderingAllowed(false);
    }


    /**
     * Speichert die Daten des ausgewählten Objects auf der Table. Setzt die Buttons Edit und Delete auf enabled.
     * 
     * @param select
     */
    private void changeSerie(int select)
    {
        if (select > -1 && select < gui.getTable().getRowCount())
        {
            deleteOrChange = serienModel.getSelectedSerie(select);
            gui.getEditTiteltf().setText(deleteOrChange.getTitle());
            gui.setEditEpisodetf(deleteOrChange.getEpisodes());
            gui.setEditFulltf(deleteOrChange.getEpisodesTotal().toString());
            gui.getEditLanguageBox().setSelectedItem(deleteOrChange.getLanguage());
            gui.getEditGenreBox().setSelectedItem(deleteOrChange.getGenre());
            gui.getEditComplete().setSelected(deleteOrChange.isComplete());
            gui.getEditEpisodetf().setEnabled(!deleteOrChange.isComplete());
            gui.getEditLentTf().setText(deleteOrChange.getLent());
            if (!viewOnly)
            {
                if (isEasyEditable())
                {
                    gui.getRemoveOneButton().setEnabled(isEasyRemoveOne());
                    gui.getAddOneButton().setEnabled(isEasyAddOne());
                }
                else
                {
                    gui.getRemoveOneButton().setEnabled(false);
                    gui.getAddOneButton().setEnabled(false);
                }
                gui.getEditButton().setEnabled(true);
                gui.getDeleteButton().setEnabled(true);
            }
        }
    }


    private boolean isEasyEditable()
    {
        String episode = gui.getEditEpisodetf().getText();
        if ((episode.equals("1") && !gui.getEditComplete().isSelected()) || episode.split("-").length == 2
                && EpisodesHelper.isNumber(episode.split("-")[0]) && EpisodesHelper.isNumber(episode.split("-")[1])
                || (gui.getEditComplete().isSelected() && !episode.equals("1")))
        {
            return true;
        }
        return false;
    }


    private boolean isEasyAddOne()
    {
        String episode = gui.getEditEpisodetf().getText();
        return episode.equals("1") || (episode.split("-").length == 2 && !gui.getEditComplete().isSelected());
    }


    private boolean isEasyRemoveOne()
    {
        return gui.getEditEpisodetf().getText().split("-").length == 2;
    }


    /**
     * Aktualisiert die Liste mit den neusten Werten. Falls eine Suche gestartet wurde, werden die Suchresultate
     * reingeschrieben.
     * 
     * @param makeSearch
     */
    public void fillSerienList(boolean makeSearch)
    {
        if (makeSearch)
        {
            serienModel.setData(serien.searchList(new SearchSerienVO(gui)));
            gui.setSearchResultsLabel(serien.getSearchSerien().size());
        }
        else
        {
            try
            {
                serienModel.setData(serien.getAllSerien());
                gui.setSearchResultsLabel(serien.getAllSerien().size());
            }
            catch (Exception e)
            {
                System.err.println("Fehler beim neuladen der Table");
            }
        }
        gui.getTable().setModel(serienModel);
        gui.getTable().tableChanged(new TableModelEvent(serienModel));
        if (serienModel.getRowCount() == 1)
        {
            gui.getTable().changeSelection(0, 0, false, false);
        }
        else if (serienModel.getRowCount() == 0)
        {
            gui.getEditButton().setEnabled(false);
            gui.getDeleteButton().setEnabled(false);
        }
        gui.setLastChange(serien.getTime());
    }


    /**
     * Setzt die Editierfelder leer.
     */
    private void resetEditForm()
    {
        gui.setEditTiteltf(EMPTY_STRING);
        gui.setEditEpisodetf(EMPTY_STRING);
        gui.setEditFulltf(EMPTY_STRING);
        gui.setEditLanguageBox(0);
        gui.setEditGenreBox(0);
        gui.setEditLent(EMPTY_STRING);
        gui.getEditComplete().setSelected(false);
        gui.getEditEpisodetf().setEnabled(true);
        gui.getEditButton().setEnabled(false);
        gui.getDeleteButton().setEnabled(false);
    }


    private void resetSearchForm()
    {
        gui.setSearchTiteltf(EMPTY_STRING);
        gui.setSearchFulltf(EMPTY_STRING);
        gui.setSearchComplete(false);
        gui.setSearchLent(false);
        gui.setSearchNotLent(false);
        gui.setSearchIncomplete(false);
        gui.setSearchLanguageBox(0);
        gui.setSearchGenreBox(0);
    }


    /**
     * Löscht alle Eingabefelder
     */
    private void afterEdit()
    {
        gui.getTable().setSelectionMode(0);
        resetEditForm();
    }


    /**
     * @return Liste mit allen Serien in der DB.
     */
    public SerienListManager getSerien()
    {
        return serien;
    }


    private void setGUIToNotCompleteSerie(int newEpisode)
    {
        if (newEpisode == new Integer(gui.getEditFulltf().getText()).intValue())
        {
            gui.getEditComplete().setSelected(false);
            gui.getEditEpisodetf().setEnabled(true);
            gui.getAddOneButton().setEnabled(true);
        }
    }


    private void saveSerie()
    {
        int selectedSerie = gui.getTable().getSelectedRow();
        SerieVO serie = new SerieVO();
        serie.setTitle(gui.getEditTiteltf().getText());
        serie.setEpisoden(gui.getEditEpisodetf().getText());
        serie.setEpisodesTotal(new Integer(gui.getEditFulltf().getText()));
        serie.setLanguage(gui.getEditLanguage());
        serie.setGenre(gui.getEditGenre());
        if (!(gui.getNewGenreLabel().getText().equals(EMPTY_STRING)))
        {
            serie.setGenre(gui.getNewGenreLabel().getText());
        }
        if (!(gui.getNewLanguageLabel().getText().equals(EMPTY_STRING)))
        {
            serie.setLanguage(gui.getNewLanguageLabel().getText());
        }
        serien.updateSerie(serie, deleteOrChange);
        fillSerienList(true);
        gui.getTable().setRowSelectionInterval(selectedSerie, selectedSerie);
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(gui.getAddOneButton()))
        {
            String epi = gui.getEditEpisodetf().getText();
            if (epi.equals("1"))
            {
                gui.getEditEpisodetf().setText("1-2");
                gui.getRemoveOneButton().setEnabled(true);
            }
            else
            {
                String[] newEpisodeValue = epi.split("-");
                int newEpisode = new Integer(newEpisodeValue[1]).intValue();
                gui.getEditEpisodetf().setText("1-" + ++newEpisode);
            }
            if (gui.getEditEpisodetf().getText().split("-")[1].equals(gui.getEditFulltf().getText()))
            {
                gui.getAddOneButton().setEnabled(false);
                gui.getEditComplete().setSelected(true);
                gui.getEditEpisodetf().setEnabled(false);
            }
            saveSerie();
        }
        else if (e.getSource().equals(gui.getRemoveOneButton()))
        {
            String epi = gui.getEditEpisodetf().getText();
            if (epi.equals("1-2"))
            {
                gui.getEditEpisodetf().setText("1");
                setGUIToNotCompleteSerie(2);
                gui.getRemoveOneButton().setEnabled(false);
            }
            else
            {
                String[] newEpisodeValue = epi.split("-");
                int newEpisode = new Integer(newEpisodeValue[1]).intValue();
                setGUIToNotCompleteSerie(newEpisode);
                gui.getEditEpisodetf().setText("1-" + --newEpisode);
            }
            saveSerie();
        }
        else if (e.getSource().equals(gui.getSearchComplete()) || e.getSource().equals(gui.getSearchIncomplete()))
        {
            unSelectComplete((JCheckBox) e.getSource());
            fillSerienList(true);
        }
        else if (e.getSource().equals(gui.getSearchLent()) || e.getSource().equals(gui.getSearchNotLent()))
        {
            unSelectLent((JCheckBox) e.getSource());
            fillSerienList(true);
        }
        else if (e.getSource().equals(gui.getAddButton()))
        {
            dialogCall((JButton) e.getSource());
        }
        else if (e.getSource().equals(gui.getEditButton()))
        {
            dialogCall((JButton) e.getSource());
            afterEdit();
        }
        else if (e.getSource().equals(gui.getDeleteButton()))
        {
            dialogCall((JButton) e.getSource());
            afterEdit();
        }
        else if (e.getSource().equals(gui.getResetButton()))
        {
            resetSearchForm();
            afterEdit();
            fillSerienList(false);
            gui.getTable().changeSelection(-1, 0, false, false);
        }
        else if (e.getSource().equals(gui.getNewGenreButton()) || e.getSource().equals(gui.getNewLanguageButton()))
        {
            JButton temp = (JButton) e.getSource();
            new NewEditInfos(gui, temp.getName());
            if (temp.getName().equals(MainWindow.STRING_LANGUAGE) && !gui.getNewLanguageLabel().getText().equals(EMPTY_STRING))
            {
                gui.getClearLanguage().setVisible(true);
            }
            else if (temp.getName().equals(MainWindow.STRING_GENRE) && !gui.getNewGenreLabel().getText().equals(EMPTY_STRING))
            {
                gui.getClearGenre().setVisible(true);
            }
        }
        else if (e.getSource().equals(gui.getEditComplete()))
        {
            gui.getEditEpisodetf().setEnabled(!gui.getEditComplete().isSelected());
        }
        else if (e.getSource().equals(gui.getClearLanguage()))
        {
            gui.getNewLanguageLabel().setText(EMPTY_STRING);
            gui.getClearLanguage().setVisible(false);
        }
        else if (e.getSource().equals(gui.getClearGenre()))
        {
            gui.getNewGenreLabel().setText(EMPTY_STRING);
            gui.getClearGenre().setVisible(false);
        }
        else if (e.getSource().equals(gui.getDisEnable()))
        {
            gui.refreshBoxes();
            gui.disEnableAll();
            serien.loadAllSeries(ListerProperties.getDefaultProperties().getProperty("pfad"));
            fillSerienList(false);
            gui.getDisEnable().setVisible(false);
        }
    }


    public void openSerieDialog()
    {
        if (!gui.getEditTiteltf().getText().equals(EMPTY_STRING))
        {
            if (serienDialog == null)
            {
                serienDialog = new InfoSerieDialog(gui);
            }
            else
            {
                serienDialog.updateLabels();
                serienDialog.setVisible(true);
            }
        }
    }


    /**
     * Ruft die Dialogboxen auf je nach dem was für ein Button gedruckt wurde. Aktualisiert das Table nach dem der
     * Dialog zugemacht wurde.
     * 
     * @param button
     */
    private void dialogCall(JButton button)
    {
        if (button.equals(gui.getEditButton()))
        {
            if (gui.getEditComplete().isSelected()
                    && SerienListManager.existingEpisodes(gui.getEditFulltf().getText()) != 1)
            {
                gui.getEditEpisodetf().setText("1-" + gui.getEditFulltf().getText());
            }
            if (!EpisodesHelper.checkEpisodeInput(gui.getEditEpisodetf().getText()))
            {
                JOptionPane.showMessageDialog(null, stats.getObject("ctWEpisodesMsg").toString(), stats.getObject(
                        "ctWEpisodes").toString(), JOptionPane.WARNING_MESSAGE);
            }
            else if (!EpisodesHelper.checkEpisodeInput(gui.getEditFulltf().getText()))
            {
                JOptionPane.showMessageDialog(null, stats.getObject("ctWTotalEpisodesMsg").toString(), stats.getObject(
                        "ctWTotalEpisodes").toString(), JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                new EditDialog(gui, deleteOrChange);
                fillSerienList(true);
            }
        }
        else if (button.equals(gui.getDeleteButton()))
        {
            new DeleteDialog(gui, deleteOrChange);
            fillSerienList(true);
        }
        else
        {
            new AddDialog(gui);
            fillSerienList(true);
        }
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(e.getSource() instanceof JComponent)
            {
                JComponent tempComponent = (JComponent) e.getSource();
                if(tempComponent.getParent().equals(gui.getEditPanel()))
                    gui.getEditButton().doClick();
            }
            if (e.getSource().equals(gui.getSearchTiteltf()) 
                    || e.getSource().equals(gui.getSearchFulltf()))
            {
                gui.getTable().requestFocus();
                gui.getTable().changeSelection(0, 0, false, false);
            }
        }
        else if (e.getSource().equals(gui.getTable()) && e.getKeyCode() == KeyEvent.VK_DELETE)
        {
            gui.getDeleteButton().doClick();
        }
    }


    public void keyReleased(KeyEvent e)
    {
        if ((e.getSource().equals(gui.getSearchComplete()) || e.getSource().equals(gui.getSearchIncomplete()) || e
                .getSource().equals(gui.getEditComplete()))
                && e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            temporaryCheckBox = (JCheckBox) e.getSource();
            temporaryCheckBox.setSelected(!temporaryCheckBox.isSelected());
        }
        if(e.getSource() instanceof JComponent)
        {
            JComponent tempComponent = (JComponent) e.getSource();
            if(tempComponent.getParent().equals(gui.getSearchPanel()))
            {
                fillSerienList(true);
            }
        }
    }


    public void keyTyped(KeyEvent e)
    {
        // Initialize
    }


    public void itemStateChanged(ItemEvent e)
    {
        if (gui.getSearchGenreBox().getItemCount() != 0 
                && gui.getSearchLanguageBox().getItemCount() != 0)
        {
            fillSerienList(true);
        }
    }


    public void valueChanged(ListSelectionEvent e)
    {
        if (gui.getTable().getSelectedRow() == -1)
            resetEditForm();
        else
            changeSerie(gui.getTable().getSelectedRow());
    }


    private void unSelectComplete(JCheckBox selected)
    {
        if (selected.equals(gui.getSearchComplete()))
            gui.getSearchIncomplete().setSelected(false);
        else
            gui.getSearchComplete().setSelected(false);
    }


    private void unSelectLent(JCheckBox selected)
    {
        if (selected.equals(gui.getSearchNotLent()))
            gui.getSearchLent().setSelected(false);
        else
            gui.getSearchNotLent().setSelected(false);
    }


    public boolean isViewOnly()
    {
        return viewOnly;
    }


    public void setViewOnly(boolean viewOnly)
    {
        this.viewOnly = viewOnly;
    }


    public void mouseClicked(MouseEvent e)
    {
        if (e.getSource().equals(gui.getTable().getTableHeader()))
        {
            serienModel.sortByColumn(gui.getTable().columnAtPoint(e.getPoint()), serien.getAllSerien());
            fillSerienList(true);
            gui.getTable().getTableHeader().repaint();
        }
        else if (e.getSource().equals(gui.getTable()) && e.getClickCount() == 2)
        {
            openSerieDialog();
        }
    }


    public void mouseEntered(MouseEvent e)
    {
        // init mouse entered
    }


    public void mouseExited(MouseEvent e)
    {
        // init !mouseover
    }


    public void mousePressed(MouseEvent e)
    {
        // init weg mit mouse
    }


    public void mouseReleased(MouseEvent e)
    {
        // init mouseover
    }


    public MouseAdapter getMouseAdapter()
    {
        return mouseAdapter;
    }


    public int getMouseoverIndex()
    {
        return mouseoverIndex;
    }


    public void setMouseoverIndex(int mouseoverIndex)
    {
        this.mouseoverIndex = mouseoverIndex;
    }


    public MainWindow getGui()
    {
        return gui;
    }


    public SerienTable getSerienTable()
    {
        return serienTable;
    }
}
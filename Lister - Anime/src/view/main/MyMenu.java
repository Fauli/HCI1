package view.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import view.db.DateiImport;
import view.db.ExportDialog;
import view.db.ImportDialog;
import view.db.InfoDBDialog;
import view.db.SocketExport;
import view.helper.HelpDialog;
import view.helper.SettingsDialog;

import controller.Controller;


/**
 * Creates the JMenuBar for the GUI.
 * 
 * @author srzzco
 */
@SuppressWarnings("serial")
public class MyMenu extends JMenuBar implements ActionListener
{

    private MainWindow gui;
    private JMenuItem newSerie, addSeries, newSearch, edit, delete, settings, close;
    private JCheckBoxMenuItem onTop;
    private JMenuItem aboutSerie, aboutDB, socketExport, exportAll, exportSelected, exportSearched, importing,
            episodeImport;
    private JMenuItem contact, help;
    private JMenu menu, export;
    private SettingsDialog settingDialog = null;
    private InfoDBDialog dbDialog = null;
    private KontaktInfos contactDialog = null;
    private ExportDialog exportDialog = null;
    private ImportDialog importDialog = null;
    private DateiImport dateiImport = null;
    private SocketExport socketExportDialog = null;


    /**
     * For Setting the Dialogs modal and get methods.
     * 
     * @param gui
     */
    public MyMenu(MainWindow gui)
    {
        this.gui = gui;
        setMenuItemsNames();
        setAccelerator();
        add(setDatei());
        add(setDaten());
        add(setHilfe());
        setActionListener();
    }


    /**
     * Sets the names for the MenuItems.
     */
    private void setMenuItemsNames()
    {
        newSerie = new JMenuItem(Controller.stats.getObject("menuNew").toString());
        addSeries = new JMenuItem(Controller.stats.getObject("menuAdd").toString());
        addSeries.setMnemonic('A');
        newSearch = new JMenuItem(Controller.stats.getObject("menuNewSearch").toString());
        newSearch.setMnemonic('S');
        edit = new JMenuItem(Controller.stats.getObject("menuEdit").toString());
        edit.setMnemonic('E');
        delete = new JMenuItem(Controller.stats.getObject("menuDelete").toString());
        delete.setMnemonic('L');
        settings = new JMenuItem(Controller.stats.getObject("menuSettings").toString());
        onTop = new JCheckBoxMenuItem(Controller.stats.getObject("menuOnTop").toString());
        close = new JMenuItem(Controller.stats.getObject("menuClose").toString());
        close.setMnemonic('B');
        aboutSerie = new JMenuItem(Controller.stats.getObject("menuSerie").toString());
        aboutDB = new JMenuItem(Controller.stats.getObject("menuDatabase").toString());
        importing = new JMenuItem(Controller.stats.getObject("menuFileImport").toString());
        export = new JMenu(Controller.stats.getObject("menuExport").toString());
        exportAll = new JMenuItem(Controller.stats.getObject("menuExportAll").toString());
        socketExport = new JMenuItem(Controller.stats.getObject("menuExportAsServer").toString());
        exportSearched = new JMenuItem(Controller.stats.getObject("menuExportSearch").toString());
        exportSelected = new JMenuItem(Controller.stats.getObject("menuExportSelection").toString());
        episodeImport = new JMenuItem(Controller.stats.getObject("menuImport").toString());
        episodeImport.setMnemonic('i');
        contact = new JMenuItem(Controller.stats.getObject("menucontact").toString());
        help = new JMenuItem(Controller.stats.getObject("menuHelps").toString());
        help.setAccelerator(KeyStroke.getKeyStroke("F1"));
    }


    /**
     * Attaches the ActionListner to the specifics MenuItems.
     */
    private void setActionListener()
    {
        addSeries.addActionListener(this);
        newSerie.addActionListener(this);
        newSearch.addActionListener(this);
        edit.addActionListener(this);
        delete.addActionListener(this);
        settings.addActionListener(this);
        onTop.addActionListener(this);
        close.addActionListener(this);
        aboutSerie.addActionListener(this);
        aboutDB.addActionListener(this);
        importing.addActionListener(this);
        exportAll.addActionListener(this);
        socketExport.addActionListener(this);
        episodeImport.addActionListener(this);
        exportSearched.addActionListener(this);
        exportSelected.addActionListener(this);
        contact.addActionListener(this);
        help.addActionListener(this);
    }


    /**
     * Adds the MenuItems to the MenuPoint Daten.
     */
    private JMenu setDatei()
    {
        menu = new JMenu(Controller.stats.getObject("menuFile").toString());
        menu.add(newSerie);
        menu.add(addSeries);
        menu.add(newSearch);
        menu.add(edit);
        menu.add(delete);
        menu.addSeparator();
        menu.add(settings);
        menu.add(onTop);
        menu.addSeparator();
        menu.add(close);
        menu.setMnemonic('D');
        return menu;
    }


    /**
     * Adds the MenuItems to the MenuPoint Use.
     */
    private JMenu setDaten()
    {
        menu = new JMenu(Controller.stats.getObject("menuFiles").toString());
        menu.add(aboutSerie);
        menu.add(aboutDB);
        menu.add(episodeImport);
        setExport();
        menu.add(export);
        menu.setMnemonic('T');
        menu.add(importing);
        return menu;
    }


    /**
     * Adds the Menuitems to the Menupoint Hilfe.
     */
    private JMenu setHilfe()
    {
        menu = new JMenu(Controller.stats.getObject("menuHelp").toString());
        menu.add(contact);
        menu.addSeparator();
        menu.add(help);
        menu.setMnemonic('H');
        return menu;
    }


    private void setExport()
    {
        export.add(exportAll);
        export.add(exportSearched);
        export.add(exportSelected);
        export.add(socketExport);
    }


    private void setAccelerator()
    {
        addSeries.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
        newSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        onTop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_DOWN_MASK));
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        episodeImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
    }


    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource().equals(newSerie))
        {
            int choose = JOptionPane.showConfirmDialog(null, "Sind sie sich sicher das sie alle Daten löschen wollen?",
                    "Sicherheitsabfrage", JOptionPane.YES_NO_OPTION);
            if (choose == JOptionPane.YES_OPTION)
            {
                gui.getController().getSerien().clearSerien();
                gui.getController().fillSerienList(false);
                gui.getController().getSerien().save();
            }
        }
        else if (event.getSource().equals(aboutSerie))
        {
            gui.getController().openSerieDialog();
        }
        else if (event.getSource().equals(episodeImport))
        {
            if (dateiImport == null)
            {
                dateiImport = new DateiImport(gui);
            }
            else
            {
                dateiImport.setVisible(true);
            }
        }
        else if (event.getSource().equals(aboutDB))
        {
            if (dbDialog == null)
            {
                dbDialog = new InfoDBDialog(gui);
            }
            else
            {
                dbDialog.neuBerechnung();
                dbDialog.setVisible(true);
            }
        }
        else if (event.getSource().equals(importing))
        {
            if (importDialog == null)
                importDialog = new ImportDialog(gui);
            else
                importDialog.setVisible(true);
        }
        else if (event.getSource().equals(exportAll) || event.getSource().equals(exportSearched)
                || event.getSource().equals(exportSelected))
        {
            JMenuItem temp = (JMenuItem) event.getSource();
            if (exportDialog == null)
                exportDialog = new ExportDialog(gui, temp.getText());
            else
                exportDialog.reactivate(temp.getText());
        }
        else if (event.getSource().equals(socketExport))
        {
            if (socketExportDialog == null)
                socketExportDialog = new SocketExport(gui);
            else
                socketExportDialog.reactivate();
        }
        else if (event.getSource().equals(addSeries))
        {
            gui.getAddButton().doClick();
        }
        else if (event.getSource().equals(contact))
        {
            if (contactDialog == null)
                contactDialog = new KontaktInfos(gui);
            else
                contactDialog.setVisible(true);
        }
        else if (event.getSource().equals(newSearch))
        {
            gui.getResetButton().doClick();
        }
        else if (event.getSource().equals(delete))
        {
            gui.getDeleteButton().doClick();
        }
        else if (event.getSource().equals(settings))
        {
            if (settingDialog == null)
                settingDialog = new SettingsDialog(gui);
            else
                settingDialog.reactivate();
        }
        else if (event.getSource().equals(onTop))
        {
            gui.setAlwaysOnTop(!gui.isAlwaysOnTop());
        }
        else if (event.getSource().equals(edit))
        {
            gui.getEditButton().doClick();
        }
        else if (event.getSource().equals(help))
        {
            new HelpDialog(gui);
        }
        else if (event.getSource().equals(close))
        {
            System.exit(0);
        }
    }
}
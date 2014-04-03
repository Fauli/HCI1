package view.serie;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.SerieVO;
import view.helper.GuiHelper;
import view.main.MainWindow;
import controller.Controller;


/**
 * Dialog das abfrag ob die Serie tatsächlich gelöscht werden soll.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class DeleteDialog extends JDialog implements ActionListener, KeyListener
{
    private JLabel oldTitelLabel, oldEpisodeLabel, oldLanguageLabel, oldFullLabel, oldGenreLabel;
    private JLabel deleteTitelLabel, deleteEpisodeLabel, deleteFullLabel, deleteLanguagelabel, deleteGenreLabel;
    private JButton deleteButton, cancelButton;
    private SerieVO newS;
    private MainWindow gui;


    public DeleteDialog(MainWindow gui, SerieVO serie)
    {
        super(gui, Controller.stats.getObject("titleDeleteDialog").toString() + serie.getTitle(), true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = gui;
        newS = serie;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
        setVisible(true);
    }


    private void setDialogInfos()
    {
        setLabelAndPanelInfos();
        creataeInformationPanel();
        this.add(createComponents());
        this.pack();
        setResizable(false);
        setLocationRelativeTo(gui);
    }


    /**
     * Hier werden die Dialoginformationen gestellt.
     */
    private void setLabelAndPanelInfos()
    {
        deleteTitelLabel = new JLabel(MainWindow.STRING_TITEL);
        deleteGenreLabel = new JLabel(MainWindow.STRING_GENRE);
        deleteLanguagelabel = new JLabel(MainWindow.STRING_LANGUAGE);
        deleteEpisodeLabel = new JLabel(Controller.stats.getObject("existing").toString());
        deleteFullLabel = new JLabel(Controller.stats.getObject("total").toString());
        oldTitelLabel = new JLabel(gui.getEditTiteltf().getText());
        oldEpisodeLabel = new JLabel(gui.getEditEpisodetf().getText());
        oldFullLabel = new JLabel(gui.getEditFulltf().getText());
        oldLanguageLabel = new JLabel(gui.getEditLanguage());
        oldGenreLabel = new JLabel(gui.getEditGenre());
        deleteButton = new JButton(MainWindow.STRING_DELETE);
        deleteButton.addActionListener(this);
        deleteButton.addKeyListener(this);
        cancelButton = new JButton(Controller.stats.getObject("buttonCancel").toString());
        cancelButton.addActionListener(this);
        cancelButton.addKeyListener(this);
    }


    public JPanel creataeInformationPanel()
    {
        JPanel deleteSeriePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        // Zeile 1
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        deleteSeriePanel.add(deleteTitelLabel, gridBag);
        gridBag.gridx = 1;
        deleteSeriePanel.add(oldTitelLabel, gridBag);
        gridBag.gridx = 2;
        deleteSeriePanel.add(deleteLanguagelabel, gridBag);
        gridBag.gridx = 3;
        deleteSeriePanel.add(oldLanguageLabel, gridBag);
        // Zeile 2
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        deleteSeriePanel.add(deleteEpisodeLabel, gridBag);
        gridBag.gridx = 1;
        deleteSeriePanel.add(oldEpisodeLabel, gridBag);
        gridBag.gridx = 2;
        deleteSeriePanel.add(deleteGenreLabel, gridBag);
        gridBag.gridx = 3;
        deleteSeriePanel.add(oldGenreLabel, gridBag);
        // Zeile 3
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        deleteSeriePanel.add(deleteFullLabel, gridBag);
        gridBag.gridx = 1;
        deleteSeriePanel.add(oldFullLabel, gridBag);
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 2;
        return deleteSeriePanel;
    }


    public JPanel createComponents()
    {
        JPanel allPanels = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(3, 3, 0, 0);
        // Buttons
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        gridBag.gridwidth = 2;
        gridBag.anchor = GridBagConstraints.EAST;
        allPanels.add(creataeInformationPanel(), gridBag);
        gridBag.insets.set(3, 3, 3, 3);
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridx = 1;
        allPanels.add(GuiHelper.createPanelAligned(deleteButton, cancelButton), gridBag);
        return allPanels;
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (e.getSource().equals(deleteButton))
            {
                deleteButton.doClick();
            }
            else if (e.getSource().equals(cancelButton))
            {
                cancelButton.doClick();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            cancelButton.doClick();
        }
    }


    public void actionPerformed(final ActionEvent e)
    {
        if (e.getSource().equals(deleteButton))
        {
            newS = new SerieVO();
            newS.setTitle(oldTitelLabel.getText());
            newS.setEpisoden(oldEpisodeLabel.getText());
            newS.setEpisodesTotal(new Integer(oldFullLabel.getText()));
            newS.setGenre(oldGenreLabel.getText());
            newS.setLanguage(oldLanguageLabel.getText());
            gui.getController().getSerien().deleteSerie(newS);
            this.setVisible(false);
        }
        else if (e.getSource().equals(cancelButton))
        {
            this.setVisible(false);
        }
    }


    public void keyReleased(KeyEvent e)
    {
        // Auto-generated method stub
    }


    public void keyTyped(KeyEvent e)
    {
        // Auto-generated method stub
    }
}
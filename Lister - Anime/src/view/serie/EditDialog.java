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
 * Ein Dialogfenster das die neuen Informationen der Serien zur nochmaligen Überprüfung anzeigt.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class EditDialog extends JDialog implements ActionListener, KeyListener
{

    private JLabel titelLabel, episodeLabel, languageLabel, completeLabel, genreLabel, lentLabel;
    private JLabel serienTitelLabel, serienEpisodeLabel, serienLanguageLabel, serienCompleteLabel, serienGenreLabel,
            serienLentLabel;
    private JButton editButton, cancelButton;
    private SerieVO oldSerie, newSerie;
    private MainWindow gui;
    private static final String NOT_LENT = "-";


    public EditDialog(MainWindow gui, SerieVO serie)
    {
        super(gui, Controller.stats.getObject("titleEditDialog").toString() + serie.getTitle(), true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = gui;
        oldSerie = serie;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
    }


    private void setDialogInfos()
    {
        setLabelAndPanelInfos();
        this.add(createPanels());
        this.pack();
        setResizable(false);
        setLocationRelativeTo(gui);
        setVisible(true);
    }


    /**
     * Hier werden die Dialoginformationen gestellt.
     */
    private void setLabelAndPanelInfos()
    {
        titelLabel = new JLabel(Controller.stats.getObject("showInfoTitle").toString());
        genreLabel = new JLabel(Controller.stats.getObject("showInfoGenre").toString());
        languageLabel = new JLabel(Controller.stats.getObject("showInfoLanguage").toString());
        episodeLabel = new JLabel(Controller.stats.getObject("showInfoExisting").toString());
        completeLabel = new JLabel(Controller.stats.getObject("showInfoTotal").toString());
        lentLabel = new JLabel(Controller.stats.getObject("lent").toString() + ":");
        serienTitelLabel = new JLabel(gui.getEditTiteltf().getText());
        serienEpisodeLabel = new JLabel(gui.getEditEpisodetf().getText());
        serienCompleteLabel = new JLabel(gui.getEditFulltf().getText());
        serienGenreLabel = new JLabel(gui.getEditGenre());
        serienLanguageLabel = new JLabel(gui.getEditLanguage());
        serienLentLabel = new JLabel(gui.getEditLentTf().getText());
        if(serienLentLabel.getText().isEmpty())
        {
            serienLentLabel.setText(NOT_LENT);
        }
        editButton = new JButton("Edit");
        editButton.addActionListener(this);
        editButton.addKeyListener(this);
        cancelButton = new JButton(Controller.stats.getObject("buttonCancel").toString());
        cancelButton.addActionListener(this);
        cancelButton.addKeyListener(this);
        checkBeforeEdit();
    }


    public JPanel createInformationPanel()
    {
        JPanel informationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        // Zeile 1
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        informationPanel.add(titelLabel, gridBag);
        gridBag.gridx = 1;
        informationPanel.add(serienTitelLabel, gridBag);
        gridBag.gridx = 2;
        informationPanel.add(languageLabel, gridBag);
        gridBag.gridx = 3;
        informationPanel.add(serienLanguageLabel, gridBag);
        // Zeile 2
        gridBag.gridy = 1;
        gridBag.gridx = 0;
        informationPanel.add(episodeLabel, gridBag);
        gridBag.gridx = 1;
        informationPanel.add(serienEpisodeLabel, gridBag);
        gridBag.gridx = 2;
        informationPanel.add(genreLabel, gridBag);
        gridBag.gridx = 3;
        informationPanel.add(serienGenreLabel, gridBag);
        // Row 3
        gridBag.gridy = 2;
        gridBag.gridx = 0;
        informationPanel.add(completeLabel, gridBag);
        gridBag.gridx = 1;
        informationPanel.add(serienCompleteLabel, gridBag);
        // Row 4
        gridBag.gridy = 3;
        gridBag.gridx = 0;
        informationPanel.add(lentLabel, gridBag);
        gridBag.gridx = 1;
        informationPanel.add(serienLentLabel, gridBag);
        return informationPanel;
    }

    public JPanel createPanels()
    {
        JPanel allPanels = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets.set(3, 3, 0, 0);
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        gridBag.gridwidth = 2;
        gridBag.anchor = GridBagConstraints.EAST;
        allPanels.add(createInformationPanel(), gridBag);
        gridBag.insets.set(3, 3, 3, 3);
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridx = 1;
        allPanels.add(GuiHelper.createPanelAligned(editButton, cancelButton), gridBag);
        return allPanels;
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(editButton))
        {
            newSerie = new SerieVO();
            newSerie.setTitle(serienTitelLabel.getText());
            newSerie.setEpisoden(serienEpisodeLabel.getText());
            newSerie.setLanguage(serienLanguageLabel.getText());
            newSerie.setEpisodesTotal(new Integer(serienCompleteLabel.getText()));
            newSerie.setGenre(serienGenreLabel.getText());
            if(serienLentLabel.equals(NOT_LENT))
                newSerie.setLent(serienLentLabel.getText());
            gui.getController().getSerien().updateSerie(newSerie, oldSerie);
            this.setVisible(false);
        }
        else if (e.getSource().equals(cancelButton))
        {
            this.setVisible(false);
        }
    }


    /**
     * Überprüft ob die neuen Werte gültig sind.
     */
    private void checkBeforeEdit()
    {
        if (!(gui.getNewGenreLabel().getText().equals("")))
        {
            serienGenreLabel.setText(gui.getNewGenreLabel().getText());
        }
        if (!(gui.getNewLanguageLabel().getText().equals("")))
        {
            serienLanguageLabel.setText(gui.getNewLanguageLabel().getText());
        }
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (e.getSource().equals(editButton))
            {
                editButton.doClick();
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


    public void keyReleased(KeyEvent e)
    {
        // inti
    }


    public void keyTyped(KeyEvent e)
    {
        // inti
    }
}
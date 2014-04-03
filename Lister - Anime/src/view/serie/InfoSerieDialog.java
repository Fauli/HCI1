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

import util.EpisodesHelper;
import view.helper.GuiHelper;
import view.main.MainWindow;
import controller.Controller;


/**
 * Dialog das die Informationen einer Serie anzeigt.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class InfoSerieDialog extends JDialog implements ActionListener, KeyListener
{

    private JLabel staticTitelLabel, staticEpisodeLabel, staticLanguageLabel, staticFullLabel, staticGenreLabel,
            titelLabel, episodeLabel, languageLabel, fullLabel, genreLabel;
    private JButton okButton, internetButton, aniSearchButton;
    private MainWindow gui;

    public InfoSerieDialog(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleInfoSerieDialog").toString() + gui.getEditTiteltf().getText(), true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
    }


    private void setDialogInfos()
    {
        setLabelAndPanelInfos();
        locateComponents();
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
        staticTitelLabel = new JLabel(Controller.stats.getObject("showInfoTitle").toString());
        staticGenreLabel = new JLabel(Controller.stats.getObject("showInfoGenre").toString());
        staticLanguageLabel = new JLabel(Controller.stats.getObject("showInfoLanguage").toString());
        staticEpisodeLabel = new JLabel(Controller.stats.getObject("showInfoExisting").toString());
        staticFullLabel = new JLabel(Controller.stats.getObject("showInfoTotal").toString());
        titelLabel = new JLabel();
        genreLabel = new JLabel();
        languageLabel = new JLabel();
        episodeLabel = new JLabel();
        fullLabel = new JLabel();
        updateLabels();
        okButton = new JButton(Controller.stats.getObject("buttonOk").toString());
        okButton.addActionListener(this);
        okButton.addKeyListener(this);
        internetButton = new JButton(Controller.stats.getObject("buttonInternetGoogle").toString());
        internetButton.addActionListener(this);
        internetButton.addKeyListener(this);
        aniSearchButton = new JButton(Controller.stats.getObject("buttonInternetAnisearch").toString());
        aniSearchButton.addActionListener(this);
        aniSearchButton.addKeyListener(this);
    }



    private JPanel createInformationPanel()
    {
        JPanel informationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        // Spalte 1
        gridBag.gridy = 0;
        gridBag.gridy = 0;
        informationPanel.add(staticTitelLabel, gridBag);
        gridBag.gridy = 1;
        informationPanel.add(staticEpisodeLabel, gridBag);
        gridBag.gridy = 2;
        informationPanel.add(staticFullLabel, gridBag);
        gridBag.gridy = 3;
        informationPanel.add(staticLanguageLabel, gridBag);
        gridBag.gridy = 4;
        informationPanel.add(staticGenreLabel, gridBag);
        // Spalte 2
        gridBag.gridx = 1;
        gridBag.gridy = 0;
        informationPanel.add(titelLabel, gridBag);
        gridBag.gridy = 1;
        informationPanel.add(episodeLabel, gridBag);
        gridBag.gridy = 2;
        informationPanel.add(fullLabel, gridBag);
        gridBag.gridy = 3;
        informationPanel.add(languageLabel, gridBag);
        gridBag.gridy = 4;
        informationPanel.add(genreLabel, gridBag);
        return informationPanel;
    }


    public void locateComponents()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets.set(0, 0, 3, 10);
        // Spalte 1
        gridBag.gridy = 0;
        this.add(createInformationPanel(), gridBag);
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.gridy = 1;
        this.add(GuiHelper.createPanelAligned(aniSearchButton, internetButton, okButton), gridBag);
    }


    /**
     * Aktualisiert die Labels mit den Informationen der Serie.
     */
    public void updateLabels()
    {
        titelLabel.setText(gui.getEditTiteltf().getText());
        episodeLabel.setText(gui.getEditEpisodetf().getText());
        fullLabel.setText(gui.getEditFulltf().getText());
        languageLabel.setText(gui.getEditLanguage());
        genreLabel.setText(gui.getEditGenre());
        this.setTitle("Infos: " + gui.getEditTiteltf().getText());
        this.pack();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(okButton))
        {
            this.setVisible(false);
        }
        else if (e.getSource().equals(internetButton))
        {
            GuiHelper.openBrower(EpisodesHelper.SEARCH_LINK_GOOGLE
                    + GuiHelper.makeInternetParam(gui.getEditTiteltf().getText()));
        }
        else if(e.getSource().equals(aniSearchButton))
        {
            GuiHelper.openBrower(EpisodesHelper.SEARCH_LINK_ANISEARCH
                    + GuiHelper.makeInternetParam(gui.getEditTiteltf().getText()));
        }
    }


    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            this.setVisible(false);
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
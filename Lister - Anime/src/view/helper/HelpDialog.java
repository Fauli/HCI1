package view.helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;

import view.main.MainWindow;

import controller.Controller;


/**
 * Zeigt ein Dialog mit der Hilfe an. In Bearbeitung.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class HelpDialog extends JDialog implements KeyListener
{

    // TODO ZHilfemenu + language
    private JLabel titelSucheLabel, fullSucheLabel, episodesSucheLabel, genreSucheLabel, placeSucheLabel,
            languageSucheLabel, titelEditLabel, fullEditLabel;
    private JTextArea titelSucheText, fullSucheText, episodesSucheText, genreSucheText, whereSucheText,
            languageSucheText, titelEditText, fullEditText;
    private List<JLabel> labels;
    private List<JTextArea> textareas;
    private JPanel suche, edit;
    private JScrollPane sucheScrollPane, editScrollPane;
    private MainWindow gui;
    private Color background = new Color(255, 255, 255);


    public HelpDialog(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleHelpDialog").toString(), true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        JOptionPane.showMessageDialog(null, "dary... Legendary", "Awesome", JOptionPane.INFORMATION_MESSAGE);
        setDialogInfos();
    }


    private void setDialogInfos()
    {
        JTabbedPane contentPanel = new JTabbedPane();
        suche = new JPanel();
        edit = new JPanel();
        suche.setLayout(new GridBagLayout());
        edit.setLayout(new GridBagLayout());
        setInfos();
        setSuchInformation();
        setEditInformation();
        readHelpFile();
        sucheScrollPane = new JScrollPane(suche);
        editScrollPane = new JScrollPane(edit);
        contentPanel.add("Suchen", sucheScrollPane);
        contentPanel.add("Editieren", editScrollPane);
        setContentPane(contentPanel);
        this.pack();
        setResizable(false);
        setLocationRelativeTo(gui);
        setVisible(true);
    }


    /**
     * Hier werden die Elemente positioniert.
     */
    public void setSuchInformation()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        suche.add(titelSucheLabel, gridBag);
        gridBag.gridy = 1;
        suche.add(titelSucheText, gridBag);
        gridBag.gridy = 2;
        suche.add(fullSucheLabel, gridBag);
        gridBag.gridy = 3;
        suche.add(fullSucheText, gridBag);
        gridBag.gridy = 4;
        suche.add(episodesSucheLabel, gridBag);
        gridBag.gridy = 5;
        suche.add(episodesSucheText, gridBag);
        gridBag.gridy = 6;
        suche.add(languageSucheLabel, gridBag);
        gridBag.gridy = 7;
        suche.add(languageSucheText, gridBag);
        gridBag.gridy = 8;
        suche.add(genreSucheLabel, gridBag);
        gridBag.gridy = 9;
        suche.add(genreSucheText, gridBag);
        gridBag.gridy = 10;
        suche.add(placeSucheLabel, gridBag);
        gridBag.gridy = 11;
        suche.add(whereSucheText, gridBag);
    }


    /**
     * Hier werden die Elemente positioniert.
     */
    public void setEditInformation()
    {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        edit.add(titelEditLabel, gridBag);
        gridBag.gridy = 1;
        edit.add(titelEditText, gridBag);
        gridBag.gridy = 2;
        edit.add(fullEditLabel, gridBag);
        gridBag.gridy = 3;
        edit.add(fullEditText, gridBag);
    }


    /**
     * Hier werden die Dialoginformationen gestellt.
     */
    private void setInfos()
    {
        Font f = new Font("Sans Sarif", Font.CENTER_BASELINE, 14);
        Border border = new MetalBorders.TableHeaderBorder();
        labels = new ArrayList<JLabel>();
        labels.add(titelSucheLabel = new JLabel());
        labels.add(fullSucheLabel = new JLabel());
        labels.add(episodesSucheLabel = new JLabel());
        labels.add(placeSucheLabel = new JLabel());
        labels.add(genreSucheLabel = new JLabel());
        labels.add(languageSucheLabel = new JLabel());
        labels.add(titelEditLabel = new JLabel());
        labels.add(fullEditLabel = new JLabel());
        for (JLabel label : labels)
        {
            label.setFont(f);
            label.setBorder(border);
        }
        // Liste wird erstellt um eine einfache Schleife machen zu können.
        textareas = new ArrayList<JTextArea>();
        textareas.add(titelSucheText = new JTextArea());
        textareas.add(fullSucheText = new JTextArea());
        textareas.add(episodesSucheText = new JTextArea());
        textareas.add(genreSucheText = new JTextArea());
        textareas.add(whereSucheText = new JTextArea());
        textareas.add(languageSucheText = new JTextArea());
        textareas.add(titelEditText = new JTextArea());
        textareas.add(fullEditText = new JTextArea());
        for (JTextArea textarea : textareas)
        {
            textarea.setWrapStyleWord(true);
            textarea.setLineWrap(true);
            textarea.setPreferredSize(new Dimension(400, 50));
            textarea.setEditable(false);
            textarea.setBackground(background);
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
        // Init
    }


    public void keyTyped(KeyEvent e)
    {
        // Init
    }


    private void readHelpFile()
    {
        URL url = this.getClass().getResource("data/help.txt");
        try
        {
            String readFileLine = "";
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            for (JLabel label : labels)
            {
                readFileLine = bufferedReader.readLine();
                label.setText(readFileLine);
            }
            readFileLine = bufferedReader.readLine();
            for (JTextArea text : textareas)
            {
                readFileLine = bufferedReader.readLine();
                text.setText(readFileLine);
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
package view.db;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import view.main.MainWindow;

import controller.Controller;
import controller.Server;


/**
 * Dialog das die Kontaktinformationen anzeigt.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class SocketExport extends JDialog implements ActionListener, KeyListener
{

    private JTextArea staticIpAdress;
    private JTextPane statusPane;
    private JLabel downlaods;
    private JButton createConnectionButton, closeButton;
    private MainWindow gui;
    private GridBagConstraints gridBag;
    private JPanel buttonPanel, informationPanel, allPanels;
    private Server server;
    private int amountOfDownloads = 0;


    public SocketExport(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleSocketExport").toString(), true);
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        setDialogInfos();
    }


    private void setDialogInfos()
    {
        setLabelAndPanelInfos();
        setInformation();
        setButtonPanel();
        setAllPanel();
        this.add(allPanels);
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
        informationPanel = new JPanel();
        informationPanel.setLayout(new GridBagLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        allPanels = new JPanel();
        allPanels.setLayout(new GridBagLayout());
        staticIpAdress = new JTextArea();
        staticIpAdress.setEditable(false);
        try
        {
            staticIpAdress.setText(java.net.InetAddress.getLocalHost().getHostAddress());
        }
        catch (UnknownHostException e)
        {
            staticIpAdress.setText(Controller.stats.getObject("noConnection").toString());
            e.printStackTrace();
        }
        statusPane = new JTextPane();
        StyleContext.NamedStyle centerStyle = StyleContext.getDefaultStyleContext().new NamedStyle();
        StyleConstants.setAlignment(centerStyle, StyleConstants.ALIGN_CENTER);
        statusPane.setLogicalStyle(centerStyle);
        setNotReady();
        statusPane.setEditable(false);
        statusPane.setFocusable(false);
        statusPane.setPreferredSize(new Dimension(100, 20));
        downlaods = new JLabel(Controller.stats.getObject("amountOfDownloads").toString() + amountOfDownloads);
        closeButton = new JButton(Controller.stats.getObject("buttonClose").toString());
        closeButton.addActionListener(this);
        closeButton.addKeyListener(this);
        createConnectionButton = new JButton(Controller.stats.getObject("buttonServerStart").toString());
        createConnectionButton.addActionListener(this);
        createConnectionButton.addKeyListener(this);
    }


    /**
     * Hier werden die Elemente positioniert.
     * 
     * @param dialog
     */
    public void setInformation()
    {
        gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(0, 0, 3, 10);
        // Spalte 1
        gridBag.gridy = 0;
        gridBag.gridy = 0;
        informationPanel.add(staticIpAdress, gridBag);
        // Spalte 2
        gridBag.gridx = 1;
        informationPanel.add(statusPane, gridBag);
        gridBag.gridy = 1;
        gridBag.gridx = 0;
        informationPanel.add(downlaods, gridBag);
    }


    public void setButtonPanel()
    {
        // Buttons
        gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.gridy = 0;
        gridBag.gridx = 1;
        gridBag.insets.set(3, 3, 0, 0);
        buttonPanel.add(createConnectionButton, gridBag);
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.gridx = 2;
        buttonPanel.add(closeButton, gridBag);
    }


    public void setAllPanel()
    {
        gridBag = new GridBagConstraints();
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets.set(3, 3, 0, 0);
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        gridBag.gridwidth = 2;
        allPanels.add(informationPanel, gridBag);
        gridBag.insets.set(3, 3, 3, 3);
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridx = 1;
        allPanels.add(buttonPanel, gridBag);
    }


    public void setReady()
    {
        statusPane.setBackground(Color.GREEN);
        statusPane.setText(Controller.stats.getObject("ready").toString());
    }


    public void setNotReady()
    {
        statusPane.setBackground(Color.RED);
        statusPane.setText(Controller.stats.getObject("notReady").toString());
    }


    public void reactivate()
    {
        setVisible(true);
    }


    public void downloadUp()
    {
        amountOfDownloads += 1;
        downlaods.setText(Controller.stats.getObject("amountOfDownloads").toString() + amountOfDownloads);
    }


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(createConnectionButton))
        {
            if (closeButton.isEnabled())
            {
                server = new Server(this);
                new Thread(server).start();
                createConnectionButton.setText(Controller.stats.getObject("buttonServerStop").toString());
                closeButton.setEnabled(false);
                this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
            else
            {
                if (server.setStop())
                {
                    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    createConnectionButton.setText(Controller.stats.getObject("buttonServerStart").toString());
                    closeButton.setEnabled(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, Controller.stats.getObject("sEWSendingDataMsg").toString(),
                            Controller.stats.getObject("sEWSendingData").toString(), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getSource().equals(closeButton))
        {
            this.setVisible(false);
        }
    }


    public void keyPressed(KeyEvent e)
    {
        if ((e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource().equals(closeButton))
                || e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            closeButton.doClick();
        }
        else if (e.getSource().equals(createConnectionButton) && e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            createConnectionButton.doClick();
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
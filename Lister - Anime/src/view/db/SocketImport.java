package view.db;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import view.main.MainWindow;

import controller.Controller;


/**
 * Dialog das die Kontaktinformationen anzeigt.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class SocketImport extends JDialog implements ActionListener, KeyListener
{

    private JLabel ipLabel;
    private JTextField serverIP;
    private JTextPane transferStatus;
    private JButton createConnectionButton, closeButton;
    private MainWindow gui;
    private GridBagConstraints gridBag;
    private JPanel buttonPanel, informationPanel, allPanels;
    private Socket socket;


    public SocketImport(MainWindow gui)
    {
        super(gui, Controller.stats.getObject("titleSocketImport").toString(), true);
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
        ipLabel = new JLabel(Controller.stats.getObject("ip-adress").toString());
        serverIP = new JTextField(20);
        serverIP.addKeyListener(this);
        transferStatus = new JTextPane();
        StyleContext.NamedStyle centerStyle = StyleContext.getDefaultStyleContext().new NamedStyle();
        StyleConstants.setAlignment(centerStyle, StyleConstants.ALIGN_CENTER);
        transferStatus.setLogicalStyle(centerStyle);
        transferStatus.setBackground(Color.LIGHT_GRAY);
        transferStatus.setEditable(false);
        transferStatus.setPreferredSize(new Dimension(120, 20));
        closeButton = new JButton(Controller.stats.getObject("buttonClose").toString());
        closeButton.addActionListener(this);
        closeButton.addKeyListener(this);
        createConnectionButton = new JButton(Controller.stats.getObject("dataDownload").toString());
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
        // gridBag.gridy = 0;
        informationPanel.add(ipLabel, gridBag);
        gridBag.gridx = 1;
        informationPanel.add(serverIP, gridBag);
        // Spalte 2
        gridBag.gridx = 1;
        informationPanel.add(transferStatus, gridBag);
    }


    public void setButtonPanel()
    {
        // Buttons
        gridBag = new GridBagConstraints();
        gridBag.gridy = 0;
        gridBag.gridx = 1;
        gridBag.insets.set(3, 3, 0, 0);
        buttonPanel.add(createConnectionButton, gridBag);
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


    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(createConnectionButton))
        {
            BufferedReader reader;
            BufferedWriter writer;
            FileWriter fileWriter;
            if (serverIP.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, Controller.stats.getObject("sIWNoServerMsg").toString(),
                        Controller.stats.getObject("sIWNoServer").toString(), JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    socket = new Socket(serverIP.getText(), 8888);
                    try
                    {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        fileWriter = new FileWriter("import.txt");
                        String input;
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        boolean lastLine;
                        do
                        {
                            input = reader.readLine();
                            lastLine = input.equals("EOF");
                            if (!lastLine)
                            {
                                bufferedWriter.write(input);
                                bufferedWriter.newLine();
                            }
                            writer.write("next\n");
                            writer.flush();
                        }
                        while (!lastLine);
                        bufferedWriter.close();
                        socket.close();
                        transferStatus.setText(Controller.stats.getObject("dataLoaded").toString());
                        transferStatus.setBackground(Color.GREEN);
                    }
                    catch (FileNotFoundException e1)
                    {
                        e1.printStackTrace();
                    }
                    catch (IOException e1)
                    {
                        transferStatus.setText(Controller.stats.getObject("dataLoadingFailed").toString());
                        transferStatus.setBackground(Color.RED);
                    }
                }
                catch (IOException e1)
                {
                    JOptionPane.showMessageDialog(null, Controller.stats.getObject("sIWNoServerFoundMsg").toString(),
                            Controller.stats.getObject("sIWNoServerFound").toString(), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getSource().equals(closeButton))
        {
            this.setVisible(false);
        }
    }


    public void reactivate()
    {
        setVisible(true);
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


    public JTextPane getTransferStatus()
    {
        return transferStatus;
    }
}
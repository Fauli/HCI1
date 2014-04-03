package view.db;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import view.main.MainWindow;
import controller.Controller;


@SuppressWarnings("serial")
public class SerieRenameDialog extends JDialog implements ActionListener, KeyListener
{

    private JButton cancelButton, renameButton;
    private JTextField nameTextField;
    private DateiImport fileImport;
    private String oldName, path;
    
    //TODO &s=episodes
    public SerieRenameDialog(DateiImport fileImport, String name, String path)
    {
        super(fileImport, Controller.stats.getObject("titleDateiImport").toString(), true);
        this.fileImport = fileImport;
        this.oldName = name;
        this.path = path;
        
        this.setIconImage(MainWindow.APPLICATION_ICON);
        this.setLayout(new FlowLayout());
        add(nameTextField = new JTextField(name, 20));
        add(renameButton = new JButton(Controller.stats.getObject("buttonRename").toString()));
        add(cancelButton = new JButton(Controller.stats.getObject("buttonCancel").toString()));
        
        renameButton.addKeyListener(this);
        renameButton.addActionListener(this);
        cancelButton.addKeyListener(this);
        cancelButton.addActionListener(this);
        
        setLocationRelativeTo(fileImport);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(cancelButton))
        {
            dispose();
        }
        else if(e.getSource().equals(renameButton))
        {
            if (!nameTextField.getText().isEmpty())
            {
                File folder = new File(path);
                for(File file : folder.listFiles())
                {
                    if(!file.isHidden() && !file.isDirectory())
                    {
                        if(file.getName().split(" - ")[0].equals(oldName))
                        {
                            file.renameTo(new File(file.getAbsolutePath().replace(oldName, nameTextField.getText())));
                        }
                    }
                }
                fileImport.refreshList();
                dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Enter a new name!",
                        "No Name", JOptionPane.WARNING_MESSAGE);
//            JOptionPane.showMessageDialog(null, Controller.stats.getObject("aWWTooManyEpisMsg").toString(),
//                    Controller.stats.getObject("aWWTooManyEpis").toString(), JOptionPane.WARNING_MESSAGE);
            //TODO
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            JButton tempButton = (JButton) e.getSource();
            tempButton.doClick();
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
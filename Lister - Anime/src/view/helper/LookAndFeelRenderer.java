package view.helper;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class LookAndFeelRenderer extends JLabel implements ListCellRenderer
{

	private static final long serialVersionUID = 1L;


	public LookAndFeelRenderer()
    {
        setOpaque(true);
    }


    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus)
    {
        UIManager.LookAndFeelInfo temp = (LookAndFeelInfo) value;
        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
        }
        else
        {
            setBackground(list.getBackground());
        }
        setText(temp.getName());
        return this;
    }
}

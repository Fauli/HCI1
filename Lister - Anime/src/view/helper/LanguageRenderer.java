package view.helper;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


@SuppressWarnings("serial")
public class LanguageRenderer extends JLabel implements ListCellRenderer
{

    public LanguageRenderer()
    {
        setOpaque(true);
    }


    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus)
    {
        Locale temp = (Locale) value;
        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
        }
        else
        {
            setBackground(list.getBackground());
        }
        setText(temp.getDisplayLanguage());
        return this;
    }
}

package view.helper;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


/**
 * Formation der Zellen der Episoden und Total Kolumnen.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class TableCellRenderer extends DefaultTableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
           boolean isSelected, boolean hasFocus,
            int row, int column)
    {
        JTableHeader header = table.getTableHeader();
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setText(value == null ? "" : value.toString());
        setHorizontalAlignment(SwingConstants.CENTER);
        return this;
    }
}

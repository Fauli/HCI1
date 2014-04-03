package view.helper;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import modell.SerieTableModel;


/**
 * Formatiert die Headerzellen.
 * 
 * @author Rofus
 */
@SuppressWarnings("serial")
public class TableHeaderRenderer extends DefaultTableCellRenderer
{

    private Icon UP_ICON = UIManager.getIcon("Table.descendingSortIcon");
    private Icon DOWN_ICON = UIManager.getIcon("Table.ascendingSortIcon");
    private SerieTableModel model;

    public TableHeaderRenderer(SerieTableModel model)
    {
        super();
        this.model = model;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
             boolean isSelected, boolean hasFocus,
            int row, int column)
    {
        JTableHeader header = table.getTableHeader();
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
        setText(value == null ? "" : value.toString());
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.LEFT);
        if (model.getSortColumnDesc()[column])
        {
            setIcon(UP_ICON);
        }
        else
        {
            setIcon(DOWN_ICON);
        }
        return this;
    }
}

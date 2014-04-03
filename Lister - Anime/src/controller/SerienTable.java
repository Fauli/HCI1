package controller;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import modell.SerieTableModel;


@SuppressWarnings("serial")
public class SerienTable extends JTable
{

    // private Controller controller;
    // private Color darkGreen = new Color(150, 255, 0);


    public SerienTable(SerieTableModel serienTableModel, Controller control)
    {
        super(serienTableModel);
        // controller = control;
    }


    /**
     * @param row
     * @return Weiss oder hellgrau je nach Zeile.
     */
    protected Color colorForRow(int row)
    {
        return (row % 2 == 0) ? Color.LIGHT_GRAY : Color.WHITE;
    }


    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
    {
        Component component = super.prepareRenderer(renderer, row, column);
        // mouse over table line
        // if(row == controller.getMouseoverIndex())
        // {
        // component.setBackground(darkGreen);
        // component.setForeground(UIManager.getColor("Table.foreground"));
        // }
        // else
        if (isCellSelected(row, column) == false)
        {
            component.setBackground(colorForRow(row));
            component.setForeground(UIManager.getColor("Table.foreground"));
        }
        else
        {
            component.setBackground(Color.GRAY);
            // component.setBackground(UIManager.getColor("Table.selectionBackground"));
            component.setForeground(UIManager.getColor("Table.selectionForeground"));
        }
        return component;
    }
}

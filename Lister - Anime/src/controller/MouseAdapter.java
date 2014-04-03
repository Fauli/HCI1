package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class MouseAdapter extends MouseMotionAdapter
{

    private Controller controller;


    public MouseAdapter(Controller control)
    {
        controller = control;
    }


    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (e.getSource().equals(controller.getGui().getTable()))
        {
            int i = controller.getGui().getTable().rowAtPoint(e.getPoint());
            controller.setMouseoverIndex(i);
            // if (controller.getSerien().getSearchSerien() == null)
            // {
            // System.out.println(controller.getSerien().getAllSerien().get(i).getTitle());
            // }
            // else
            // {
            // System.out.println(controller.getSerien().getSearchSerien().get(i).getTitle());
            // }
            // controller.getGui().getTable().repaint();
        }
    }
}

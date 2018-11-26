package uet.oop.bomberman.input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener
{

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent)
    {
        mouseEvent.getComponent().setForeground(new Color(255, 0, 255));
        mouseEvent.getComponent().setBackground(new Color(35, 35, 35,100));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent)
    {
        mouseEvent.getComponent().setForeground(new Color(255, 255, 255));
        mouseEvent.getComponent().setBackground(new Color(35, 35, 35,50));
    }
}

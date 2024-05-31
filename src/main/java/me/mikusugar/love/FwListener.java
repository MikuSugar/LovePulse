package me.mikusugar.love;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Fang Jie
 * @version 1.0, 2022/11/5 00:06
 */
public class FwListener extends MouseAdapter
{

    private FireWorksThread ft;

    public FwListener(FireWorksThread ft) {
        this.ft = ft;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        ft.setStartXY(e.getX(), e.getY());
    }
}

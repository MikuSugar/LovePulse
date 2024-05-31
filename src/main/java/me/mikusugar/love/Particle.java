package me.mikusugar.love;

import java.awt.*;

/**
 * @author Fang Jie
 * @version 1.0, 2022/11/5 00:08
 */
public class Particle
{
    public VecT position, velocity, acceleration;

    public Color color;

    public boolean canChange;

    public double life, age, start_time;

    public int size;

    //在界面上绘制时的x,y坐标
    public int x, y;

    public int getX()
    {
        return (int)position.x;
    }

    public int getY()
    {
        return (int)position.y;
    }

}

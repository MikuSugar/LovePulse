package me.mikusugar.love;

/**
 * @author Fang Jie
 * @version 1.0, 2022/11/5 00:07
 */
public class VecT
{
    public double x, y;

    public VecT(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public VecT add(VecT p)
    {
        return new VecT(this.x + p.x, this.y + p.y);
    }

    public VecT multiply(double f)
    {
        return new VecT(this.x * f, this.y * f);
    }

}

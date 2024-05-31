package me.mikusugar.love;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Fang Jie
 * @version 1.0, 2022/11/5 00:09
 */
public class FireWorksThread extends Thread
{
    private static JPanel panel;

    Graphics g;

    // 保存粒子的队列
    private final ArrayList<Particle> pr = new ArrayList<>();

    // 起始位置
    private int startX = 400, startY = 400;

    private final Random random;

    public FireWorksThread(JPanel panel)
    {
        FireWorksThread.panel = panel;
        random = new Random(System.currentTimeMillis());
    }

    public void setStartXY(int x, int y)
    {
        this.startX = x;
        this.startY = y;
    }

    public void run()
    {
        // 时间增量
        double dt = 0.1d;
        while (true)
        {
            // 画背景
            g = panel.getGraphics();

            // 生成粒子放入链表
            Particle tp = new Particle();
            tp.position = new VecT(startX, startY);
            tp.velocity = new VecT(-20 + random.nextInt(40), -20 + random.nextInt(40));// 速度向量
            tp.acceleration = sampleDirection();
            tp.life = 300;
            tp.age = 1;
            tp.color = new Color(255, 0, 255);
            tp.size = 12;
            pr.add(tp);

            // 链表中的粒子画到缓冲区，再画到界面上
            Image image = panel.createImage(panel.getWidth(), panel.getHeight());
            Graphics2D bg = (Graphics2D)image.getGraphics();

            bg.setColor(Color.black);
            bg.fillRect(0, 0, panel.getWidth(), panel.getHeight());// 画背景
            bg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (int i = 0; i < pr.size(); ++i)
            {
                Particle p = pr.get(i);
                // 1.判断粒子的生命是否到期，到期后，从链表中移除
                p.age += dt;
                if (p.age >= p.life)
                {
                    pr.remove(i);
                }
                // 2.计算每个粒子的下一个位置
                int r = p.color.getRGB();
                r -= 1000;
                Color c = new Color(r);
                p.color = c;
                // 判断是否碰撞到墙
                isrebound(p);
                p.position = p.position.add(p.velocity.multiply(dt));
                p.velocity = p.velocity.add(p.acceleration.multiply(dt));
                // 画到缓冲区
                bg.setColor(p.color);
                bg.fillOval(p.getX(), p.getY(), p.size, p.size);
            }
            // 将缓冲图片画到面板上
            g.drawImage(image, 0, 0, null);
            try
            {
                Thread.sleep(10);
                time += 10;
                int max = 260;
                int min = 120;
                if (time % 2000 == 0)
                {
                    if (size == max)
                    {
                        size = min;
                    }
                    else
                    {
                        size = max;
                    }
                }
            }
            catch (Exception ignored)
            {
            }
        }
    }

    private long time = 0;

    private int size = 200;

    public void isrebound(Particle p)
    {
        int sizeT = size;
        double x = (p.getX() - 400) * 1d / sizeT;
        double y = -1 * (p.getY() - 400) * 1d / sizeT;
        double result = x * x + Math.pow((5.0 * y / 4.0 - Math.sqrt(Math.abs(x))), 2);
        if (result >= 1)
        {
            if (p.canChange)
            {
                p.velocity.x *= -1;
                p.velocity.y *= -1;
                p.canChange = false;
            }
        }
        else
        {
            p.canChange = true;
        }
    }

    // 生成一个随机方向
    public static VecT sampleDirection()
    {
        double theta = Math.random() * 2 * Math.PI;
        return new VecT((Math.cos(theta)), (Math.sin(theta)));
    }
}

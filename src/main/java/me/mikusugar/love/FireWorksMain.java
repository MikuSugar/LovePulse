package me.mikusugar.love;

import javax.swing.*;

/**
 * @author Fang Jie
 * @version 1.0, 2022/11/5 00:11
 */
public class FireWorksMain extends JPanel
{

    public static void main(String[] args)
    {

        FireWorksMain fw = new FireWorksMain();

        fw.initUI();

    }

    public void initUI()
    {

        JFrame f = new JFrame();

        f.setTitle("献给XX");

        f.setSize(800, 800);

        f.setDefaultCloseOperation(3);

        f.add(this);

        f.setVisible(true);//初始化窗口界面

        FireWorksThread ft = new FireWorksThread(this);

        ft.start();//开启线程

        FwListener fl = new FwListener(ft);

        this.addMouseMotionListener(fl);//鼠标监听事件

    }

}


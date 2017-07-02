package com.myblog.layout.gridlayout;

//这段代码主要是为读者展示如何使用GridLayout布局管理器，在程序中将九个普通按钮组件按照此布局管理器放置在内容面板中。

import javax.swing.*;

import java.awt.*;

public class GridLayoutTest1

{

    static final int WIDTH = 300;

    static final int HEIGHT = 200;

    public static void main(String[] args)

    {

        JFrame jf = new JFrame("测试程序");

        jf.setSize(WIDTH, HEIGHT);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setVisible(true);

        JPanel contentPane = new JPanel();

        jf.setContentPane(contentPane);

        JButton b1 = new JButton("港币");

        JButton b2 = new JButton("人民币");

        JButton b3 = new JButton("美元");

        JButton b4 = new JButton("欧元");

        JButton b5 = new JButton("英镑");

        JButton b6 = new JButton("主板");

        JButton b7 = new JButton("内存");

        JButton b8 = new JButton("硬盘");

        JButton b9 = new JButton("显示器");

        GridLayout gird = new GridLayout(3, 3); // 创建一个
                                                // GridLayout布局管理器对象，将之行数设为3，列数设为3,并且将之作为中间容器的布局管理器

        contentPane.setLayout(gird);

        contentPane.add(b1); // 将九个普通按钮组件一一添加到中间容器中

        contentPane.add(b2);

        contentPane.add(b3);

        contentPane.add(b4);

        contentPane.add(b5);

        contentPane.add(b6);

        contentPane.add(b7);

        contentPane.add(b8);

        contentPane.add(b9);

        jf.pack();

    }

}
package com.myblog.containers.panel;

import java.awt.*;

import javax.swing.*;

class TwoPanel extends JFrame {

    public TwoPanel(String title) {

        super(title);

    }

    public static void main(String args[]) {

        TwoPanel fr = new TwoPanel("Two Panel测试");

        JPanel pan1 = new JPanel();

        JPanel pan2 = new JPanel();

        fr.setLayout(null);

        fr.getContentPane().setBackground(Color.green); // 设置窗口的颜色

        fr.setSize(250, 250);

        pan1.setLayout(null); // 设置面板为空布局

        pan1.setBackground(Color.red);

        pan1.setSize(150, 150);

        pan2.setBackground(Color.yellow);

        pan2.setSize(50, 50);

        pan1.add(pan2); // 将面板pan2添加到pan1中

        // 将pan1添加到窗体中，因为pan2被添加到pan1中，所以pan1、pan2都被显示在窗体中

        fr.getContentPane().add(pan1);

        fr.setVisible(true);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
package com.myblog.containers.panel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
    public static void main(String[] args) {
        MyFrame F = new MyFrame();
        F.setTitle("嵌套的面板");
        F.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel JP1 = new JPanel();
        JP1.setPreferredSize(new Dimension(250, 100));// 添加面板1
        JP1.setBackground(Color.red);
        JLabel JL1 = new JLabel("one");// 面板1的标签
        JP1.add(JL1);

        JPanel JP2 = new JPanel();
        JP2.setPreferredSize(new Dimension(250, 100));// 添加面板2
        JP2.setBackground(Color.blue);
        JLabel JL2 = new JLabel("two");// 面板2的标签
        JP2.add(JL2);

        JPanel JP3 = new JPanel();// 设置面板3存放面板1和面板2
        JP3.setBackground(Color.CYAN);// 设置大面板的背景
        JP3.add(JP1);
        JP3.add(JP2);

        F.getContentPane().add(JP3);
        F.pack();
        F.setVisible(true);
    }

}

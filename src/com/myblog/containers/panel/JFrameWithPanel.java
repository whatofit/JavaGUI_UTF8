package com.myblog.containers.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFrameWithPanel {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Frame With Panel");
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.CYAN); // 将JFrame实例背景设置为蓝绿色
        JPanel panel = new JPanel(); // 创建一个JPanel的实例
        panel.setBackground(Color.yellow); // 将JPanel的实例背景设置为黄色
        JButton button = new JButton("Press me");
        panel.add(button); // 将JButton实例添加到JPanel中

        contentPane.add(panel, BorderLayout.SOUTH); // 将JPanel实例添加到JFrame的南侧
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
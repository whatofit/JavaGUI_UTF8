package com.myblog.containers.tabbedpane;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class test13 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Crystal");// 实例化窗口对象
        Container container = frame.getContentPane();// 得到窗体容器
        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);// 设置标签在顶部显示
        JPanel jPanel1 = new JPanel();// 设置面板
        JPanel jPanel2 = new JPanel();// 设置面板
        JButton button = new JButton("按钮");
        JLabel jLabel = new JLabel("标签");
        jPanel1.add(button);
        jPanel2.add(jLabel);
        tab.addTab("选项", jPanel1);
        tab.addTab("设置", jPanel2);
        container.add(tab);
        frame.setSize(230, 120);
        frame.setLocation(300, 200);
        frame.setVisible(true);
    }
}
package com.myblog.containers.tabbedpane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Test {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setContentPane(new MyPanel());
        jf.setSize(300, 300);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }
}

class MyPanel extends JPanel {
    public MyPanel() {

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(SwingConstants.LEFT);
        tabbedPane.addTab("Option", getOptionPanel());
        tabbedPane.addTab("View", getViewPanel());
        tabbedPane.addTab("First", getFirstPanel());
        tabbedPane.add(new JButton("Second"), 3);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel getFirstPanel() {
        return new JPanel();
    }

    private JPanel getViewPanel() {
        return new JPanel();
    }

    private JPanel getOptionPanel() {
        return new JPanel();
    }
}
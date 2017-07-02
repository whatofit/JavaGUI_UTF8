package com.myblog.containers.tabbedpane;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TestTabbedPane {
    static void addIt(JTabbedPane tabbedPane, String text) {
        JLabel label = new JLabel(text);
        JButton button = new JButton(text);
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(button);
        tabbedPane.addTab(text, panel);
        if (text.equals("tab4"))
            tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
                    new JTextField("插入了文本控件"));
        else
            tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, button);
    }

    public static void main(String args[]) {
        JFrame f = new JFrame(" JTabbedPane演示");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        addIt(tabbedPane, "tab1");
        addIt(tabbedPane, "tab2");
        addIt(tabbedPane, "tab3");
        addIt(tabbedPane, "tab4");
        addIt(tabbedPane, "tab5");
        f.add(tabbedPane, BorderLayout.CENTER);
        f.setSize(400, 200);
        f.setVisible(true);
    }
}
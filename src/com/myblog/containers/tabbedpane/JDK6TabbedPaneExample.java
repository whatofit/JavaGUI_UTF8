package com.myblog.containers.tabbedpane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JDK6TabbedPaneExample {

    private JFrame frame = new JFrame();

    private JTabbedPane tabbedPane = new JTabbedPane();

    private JButton addTabButton = new JButton("Add Tab");

    private ImageIcon closeXIcon = new ImageIcon("C:/CloseX.gif");

    private Dimension closeButtonSize;

    private int tabCounter = 0;

    public JDK6TabbedPaneExample() {

        addTabButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add();

            }

        });
        closeButtonSize = new Dimension(closeXIcon.getIconWidth() + 2,
                closeXIcon.getIconHeight() + 2);

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(addTabButton, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setVisible(true);

    }

    public void add() {
        final JPanel content = new JPanel();
        JPanel tab = new JPanel();
        tab.setOpaque(false);

        JLabel tabLabel = new JLabel("Tab " + (++tabCounter));

        JButton tabCloseButton = new JButton(closeXIcon);
        tabCloseButton.setPreferredSize(closeButtonSize);
        tabCloseButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int closeTabNumber = tabbedPane.indexOfComponent(content);
                tabbedPane.removeTabAt(closeTabNumber);
            }
        });

        tab.add(tabLabel, BorderLayout.WEST);
        tab.add(tabCloseButton, BorderLayout.EAST);

        tabbedPane.addTab(null, content);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab);
    }

    public static void main(String[] args) {
        JDK6TabbedPaneExample main = new JDK6TabbedPaneExample();
    }

}
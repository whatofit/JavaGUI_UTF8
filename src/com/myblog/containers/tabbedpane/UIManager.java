package com.myblog.containers.tabbedpane;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UIManager extends JFrame {

    public UIManager() {
        super("学生信息管理系统");
    }

    public void initUI() {
        this.setBounds(300, 300, 500, 400);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        layoutUI();
        this.setVisible(true);
    }

    private void layoutUI() {
        // 对象实例化
        //JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);//设置标签置放位置。
        JTabbedPane tab = new JTabbedPane();
//        tab.setTabPlacement(JTabbedPane.TOP);//设置标签置放位置。
        tab.setTabPlacement(JTabbedPane.BOTTOM);
//        tab.setTabPlacement(JTabbedPane.LEFT);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        p1.setLayout(new BorderLayout());
        
        final JPanel panelSouth = new JPanel();
//        panelSouth.setLayout(new BorderLayout());
        p1.add(panelSouth, BorderLayout.SOUTH);
//        p1.add(panelSouth);

        final JButton insertButton = new JButton("New Word");
        insertButton.addActionListener(new ActionListener() {// 添加事件
                    public void actionPerformed(ActionEvent e) {
                    }
                });
        panelSouth.add(insertButton);

        final JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {// 添加事件
                    public void actionPerformed(ActionEvent e) {
                    }
                });
        panelSouth.add(refreshButton);
        
        tab.add(p1, "Word");
        tab.add(p2, "Phrase");
        tab.add(p3, "Dialogue");
        tab.add(p4, "OralSentence");

        // 对象化面板
        //JPanel combop = new JPanel();
        //combop.add(new JLabel("学生信息管理系统"));
        
        // 容器
        Container container = this.getLayeredPane();
        container.setLayout(new BorderLayout());
        //container.add(combop, BorderLayout.NORTH);
        container.add(tab, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        UIManager ui = new UIManager();
        ui.initUI();
    }
}
package com.myblog.containers.tabbedpane;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JTabbedPaneTest extends JFrame {

    public JTabbedPaneTest() {
        super();
        setTitle("表格");
        setBounds(100, 100, 350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // final JTabbedPane tabbedPane = new
        // JTabbedPane(JTabbedPane.TOP);//TOP,BOTTOM,LEFT,RIGHT
        final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        // tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        // //设置选项卡的布局方式。滚动
        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT); // 设置选项卡的布局方式。
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex(); // 获得选中的选项卡索引
                String title = tabbedPane.getTitleAt(selectedIndex); // 获得选项卡标签
                System.out.println(title);
            }
        });
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        URL resource = JTabbedPaneTest.class.getResource("jam__jelly.jpg");
        ImageIcon imageIcon = new ImageIcon(resource);
        final JLabel tabLabelA = new JLabel();
        tabLabelA.setText("选项卡A");
        tabbedPane.addTab("选项卡A", imageIcon, tabLabelA, "点击查看选项卡A");
        final JLabel tabLabelB = new JLabel();
        tabLabelB.setText("选项卡B");
        tabbedPane.addTab("选项卡B", imageIcon, tabLabelB, "点击查看选项卡B");
        final JLabel tabLabelC = new JLabel();
        tabLabelC.setText("选项卡C");
        tabbedPane.addTab("选项卡C", imageIcon, tabLabelC, "点击查看选项卡C");
        tabbedPane.setSelectedIndex(2); // 设置默认选中的
        tabbedPane.setEnabledAt(0, false); // 设置索引0的面板不可用
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JTabbedPaneTest jTabbedPaneTest = new JTabbedPaneTest();
        jTabbedPaneTest.setVisible(true);
    }

}
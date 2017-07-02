package com.myblog.containers.tabbedpane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class TabbedPaneDemo extends JPanel {

    public TabbedPaneDemo() {
        // 设置布局管理器，默认的布局管理器是 BorderLayout,这里没那么复杂
        // 选择GridLayout(1,1)即可，就是整个为一块
        super(new GridLayout(1, 1));

        // 创建JTabbedPane
        JTabbedPane tp = new JTabbedPane();
        // 创建标签显示的图标
        ImageIcon ii = createImageIcon("images/middle.gif");

        // 创建第一个标签下的panel
        JPanel panel0 = createPanel("panel0");
        // 指定标签名，标签图标，panel，和提示信息
        tp.addTab("panel0", ii, panel0, "do noting");
        // 设置标签的快捷键
        tp.setMnemonicAt(0, KeyEvent.VK_0);

        // 第二个标签
        JPanel panel1 = createPanel("panel1");
        tp.addTab("panel1", ii, panel1, "do noting");
        tp.setMnemonicAt(1, KeyEvent.VK_1);

        // 第三个标签
        JPanel panel2 = createPanel("panel2");
        tp.addTab("panel2", ii, panel2, "do noting");
        tp.setMnemonicAt(2, KeyEvent.VK_2);

        // 第四个标签
        JPanel panel3 = createPanel("panel3");
        tp.addTab("panel3", ii, panel3, "do noting");
        tp.setMnemonicAt(3, KeyEvent.VK_3);
        // 设置合适的显示尺寸，这个是必须的，因为如果所有的标签都
        // 不指定适合的显示尺寸，系统无法判断初始显示尺寸大小
        // 默认是使用最小化，并且对一个标签设计即可
        tp.setPreferredSize(new Dimension(500, 500));

        // 将tabbedPanel添加到Jpanel中
        add(tp);

        // 设置窗口过小时，标签的显示策略
        tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        // 设置标签停放的位置，这里设置为左侧停放
        tp.setTabPlacement(JTabbedPane.LEFT);

    }

    private JPanel createPanel(String string) {
        // 创建一个JPanel，并为构造函数初始false
        // 表示不适用双缓冲
        JPanel panel = new JPanel(false);

        // 设置布局
        panel.setLayout(new GridLayout(1, 1));

        // 创建一个label放到panel中
        JLabel filler = new JLabel(string);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.add(filler);
        return panel;
    }

    private ImageIcon createImageIcon(String string) {
        URL url = TabbedPaneDemo.class.getResource(string);
        if (url == null) {
            System.out.println("the image " + string + " is not exist!");
            return null;
        }
        return new ImageIcon(url);
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("table panel test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new TabbedPaneDemo());

        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
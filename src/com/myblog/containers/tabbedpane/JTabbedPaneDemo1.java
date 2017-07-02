package com.myblog.containers.tabbedpane;

import java.awt.*;  
import javax.swing.*;  
  
/** 
 * <p>Title: 选项卡演示</p> 
 * <p>Description: 这里是一个选项卡演示，点击不同的卡片，显示的内容不同</p> 
 */  
  
public class JTabbedPaneDemo1 extends JPanel {  
      
    public JTabbedPaneDemo1() {  
        super(new GridLayout(1, 1));  
  
        ImageIcon icon = createImageIcon("images/MyIcon.gif");  
        JTabbedPane tabbedPane = new JTabbedPane();  
  
        Component panel1 = makeTextPanel("#第一个卡片#");  
        tabbedPane.addTab("One", icon, panel1, "第一个卡片提示信息！");  
        tabbedPane.setSelectedIndex(0);  
  
        Component panel2 = makeTextPanel("##第二个卡片##");  
        tabbedPane.addTab("Two", icon, panel2, "第二个卡片提示信息！");  
  
        Component panel3 = makeTextPanel("###第三个卡片###");  
        tabbedPane.addTab("Three", icon, panel3, "第三个卡片提示信息！");  
  
        Component panel4 = makeTextPanel("####第四个卡片####");  
        tabbedPane.addTab("Four", icon, panel4, "第四个卡片提示信息！");  
  
        // 将选项卡添加到panl中  
        add(tabbedPane);  
    }  
  
    /** 
     * <br> 
     * 方法说明：添加信息到选项卡中 <br> 
     * 输入参数：String text 显示的信息内容 <br> 
     * 返回类型：Component 成员对象 
     */  
    protected Component makeTextPanel(String text) {  
        JPanel panel = new JPanel(false);  
        JLabel filler = new JLabel(text);  
        filler.setHorizontalAlignment(JLabel.CENTER);  
        panel.setLayout(new GridLayout(2, 2));  
        panel.add(filler);  
        return panel;  
    }  
  
    /** 
     * <br> 
     * 方法说明：获得图片 <br> 
     * 输入参数：String path 图片的路径 <br> 
     * 返回类型：ImageIcon 图片对象 
     */  
    protected static ImageIcon createImageIcon(String path) {  
        // java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);  
        if (path != null) {  
            return new ImageIcon(path);  
        } else {  
            System.out.println("Couldn't find file: " + path);  
            return null;  
        }  
    }  
  
    public static void main(String[] args) {  
        // 使用Swing窗体描述  
        // JFrame.setDefaultLookAndFeelDecorated(true);  
  
      /*  try {  
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) {  
              
        }  */
        // 创建窗体  
        JFrame frame = new JFrame("TabbedPaneDemo");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.getContentPane().add(new JTabbedPaneDemo1(), BorderLayout.CENTER);  
  
        // 显示窗体  
        frame.setSize(400, 200);  
        frame.setVisible(true);  
    }  
}  
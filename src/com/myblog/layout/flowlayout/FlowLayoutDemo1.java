package com.myblog.layout.flowlayout;

//FlowLayoutDemo.java
import javax.swing.*;
import java.awt.*;

//修改程序代码体会界面布局效果：
//setLayout(newFlowLayout());
//将上面源程序中的代码更改如下，然后做出如下更改：
//setLayout(newFlowLayout(0));  //组件左对齐
//setLayout(newFlowLayout(FlowLayout.RIGHT,10,15));  //组件右对齐,组件间水平间距为10像素，垂直间距为15像素

public class FlowLayoutDemo1 extends JFrame {
    public FlowLayoutDemo1() {
        // 设置这个容器的布局方式是空布局
        // setLayout(null);
        // 设置窗体为流式布局，无参数默认为居中对齐
        // setLayout(new FlowLayout());
        // setLayout(new FlowLayout(0)); //组件左对齐
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 15)); // 组件右对齐,组件间水平间距为10像素，垂直间距为15像素
        // 设置窗体中显示的字体样式
        setFont(new Font("Helvetica", Font.PLAIN, 14));
        // 将按钮添加到窗体中
        getContentPane().add(new JButton("Button 1"));
        getContentPane().add(new JButton("Button 2"));
        getContentPane().add(new JButton("Button3"));
        getContentPane().add(new JButton("Button 4"));
    }

    public static void main(String args[]) {
        FlowLayoutDemo1 window = new FlowLayoutDemo1();
        window.setTitle("流式布局");
        // 该代码依据放置的组件设定窗口的大小使之正好能容纳你放置的所有组件
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null); // 让窗体居中显示
    }
}
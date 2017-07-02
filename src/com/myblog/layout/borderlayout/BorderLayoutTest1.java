package com.myblog.layout.borderlayout;

//JFrame中使用jpanel来布局

//通俗的讲jframe相当与桌子，而jpanel相当桌布，jbutton，jlabel等相当于杯子，碗筷等，杯子，碗筷虽然可以直接放到桌子上但不是很规范，应该放到桌布上才对。
//以前我没有用jpanel来布局。直接就是jframe.add（jlabel1）;然后就是jlabel1.setbound();这种做法不妥，虽然窗口可以正常显示，但是还是不规范，有事后会有点小问题，当组件更改的时候会有点问题，会有阴影（以前遇到过这种问题）。
//
//正确的做法是
//1.使用Container content=this.getContentPane();得到容器
//2.实例化一个jpanel 例如JPanel  panel1=new   javax.swing.JPanel();
//3.实例化组件比如JButton等
//4.使用panel1.add（）方法添加组件
//5.定位比如说JButton1.setbound（0，0，23，34）；注意这里的坐标是相对于其所在的容器panel1
//6.使用例如content.add(panel,BorderLayout.CENTER);方法把“桌布”（panel1）放到“桌子”content上。（JFrame的默认布局是BorderLayout）

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BorderLayoutTest1 extends JFrame {
    /** Creates a new instance of NewClass */
    public BorderLayoutTest1() {
        init();
    }

    public void init() {
        JButton jb1 = new JButton("one in pan1");
        JButton jb2 = new JButton("two in pan1");
        JButton jb3 = new JButton("one out panel");
        JButton jb4 = new JButton("two out panel");
        // setBound()坐标是相对于pan1而言的
        jb1.setBounds(0, 0, 100, 30);
        jb2.setBounds(0, 200, 100, 30);
        // 实例化JPanel等一些组件
        JPanel pan1 = new JPanel();
        // 将组件添加到pan1中，并对pan1做一些初始化
        pan1.setLocation(70, 70);
        pan1.setLayout(null);// 注意此处的null
        pan1.add(jb1);
        pan1.add(jb2);
        pan1.setBackground(Color.ORANGE);
        
        // 得到容器为什么不需要实例化？可能是因为每个窗口都有这个对象
        Container cont = getContentPane();
        // 将pan1添加到容器中
        cont.add(pan1, BorderLayout.CENTER);
        cont.add(jb4, BorderLayout.WEST);
        cont.add(jb3, BorderLayout.EAST);
        this.setBounds(0, 0, 400, 400);
        this.setVisible(true);
    }

    public static void main(String arg[]) {
        BorderLayoutTest1 test = new BorderLayoutTest1();
    }
}

//问题1：做窗口程序时候Container cont=getContentPane(); cont.add(pan1,BorderLayout.CENTER);//如何定位pan1位置？如何把pan1放到指定的坐标？
//答：可以使用pan1.setbound（）方法，相应的要设置cont.setLayout(null);    注意JPanel的默认布局是FlowLayout
//如果没有设置cont.setLayout(null);系统会使用默认的布局管理器，那么pan1.setbound（）就不会起作用，比如说上例cont没有设置布局管理器为空。
//容器包含关系JFrame=》  Container=》JPanel=》（各种组件）
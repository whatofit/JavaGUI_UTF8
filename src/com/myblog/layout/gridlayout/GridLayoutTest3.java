package com.myblog.layout.gridlayout;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * 1:常用面板
 * 面板也是一个Swing容器，他可以看作为容器容纳其他组件，但它也必须被添加到其他容器中
 * Swing中常用的面板包括JPanel面板和JScrollPane面板
 * 
 * 2:JPanel面板可以聚集一些组件来布局
 * 读者首先应该明白面板也是一种容器，因为它继承了java.awt.Container类
 * @author biexiansheng
 *
 */
public class GridLayoutTest3 extends JFrame{

    public GridLayoutTest3(){
        Container container=getContentPane();//设置一个容器
        
        //将整个容器设置为2行1列的网格布局     网格布局管理器x,y代表行和列
        container.setLayout(new GridLayout(2,1,10,10));
        
        //初始化一个面板，设置1行3列的网格布局
        JPanel p1=new JPanel(new GridLayout(1,3,10,10));
        JPanel p2=new JPanel(new GridLayout(1,2,10,10));
        JPanel p3=new JPanel(new GridLayout(1,2,10,10));
        JPanel p4=new JPanel(new GridLayout(2,1,10,10));
        
        //在面板中添加按钮
        p1.add(new JButton("1"));
        p1.add(new JButton("2"));
        p1.add(new JButton("3"));
        p2.add(new JButton("4"));
        p2.add(new JButton("5"));
        p3.add(new JButton("6"));
        p3.add(new JButton("7"));
        p4.add(new JButton("8"));
        p4.add(new JButton("9"));
        
        //最重要的一步，将面板实例添加到容器中
        container.add(p1);
        container.add(p2);
        container.add(p3);
        container.add(p4);
        
        //将容器外部特性实例化
        setTitle("JPanel面板的案例");
        setSize(400,250);//设窗体的大小     宽和高
        setVisible(true);//设定窗体的可视化
        //设置窗体的关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GridLayoutTest3 jt=new GridLayoutTest3();
    }

}
package com.myblog.layout.borderlayout;

import java.awt.*;
import javax.swing.*;

public class BorderLayoutTest2 extends JFrame{
    
    private JButton[] jb = new JButton[9];
    private JPanel jp1, jp2;
    
    public static void main(String[] args){
        
        BorderLayoutTest2 demo1 = new BorderLayoutTest2();
        
    }
    
    //构造函数
    public BorderLayoutTest2(){
        
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp1.setLayout(new FlowLayout());
        jp2.setLayout(new BorderLayout());
        
        jb[0] = new JButton("囚牛");
        jb[1] = new JButton("睚眦");
        jb[2] = new JButton("嘲风");
        jb[3] = new JButton("蒲牢");
        jb[4] = new JButton("狻猊");
        jb[5] = new JButton("赑屃");
        jb[6] = new JButton("狴犴");
        jb[7] = new JButton("椒图");
        jb[8] = new JButton("貔貅");
        
        jp1.add(jb[0]);
        jp1.add(jb[1]);
        jp1.add(jb[2]);
        jp1.add(jb[3]);
        jp1.add(jb[4]);
        
        jp2.add(jb[5], BorderLayout.CENTER);
        jp2.add(jb[6], BorderLayout.NORTH);
        jp2.add(jb[7], BorderLayout.WEST);
        jp2.add(jb[8], BorderLayout.EAST);
        
        this.setLayout(new BorderLayout());
        this.add(jp1, BorderLayout.NORTH);
        this.add(jp2, BorderLayout.SOUTH);
        
        this.setTitle("你好啊！");
        this.setSize(300, 200);
        this.setLocation(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setVisible(true);
        
    }
    
}
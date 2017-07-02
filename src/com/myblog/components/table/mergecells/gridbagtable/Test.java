package com.myblog.components.table.mergecells.gridbagtable;

import java.awt.BorderLayout;   
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
  
import javax.swing.JButton;   
import javax.swing.JFrame;   
import javax.swing.JScrollPane;   
import javax.swing.table.DefaultTableModel;   
  
//开发一个类似EXCEL支持单元格合并的JTable

//GridBagModel：抽象模型接口。该接口用于描述表格中单元格的合并状态。
//DefaultGridBagTableModel：GridBagModel的默认实现。
//GridBagTable：继承自JTable的控制器。通过该类中的方法控制表格单元的合并和拆分。
//GridBagTableUI：GridBagTable对应的UI。
//
//TODO：(已合并)行、列的插入，删除操作对应的GridBagModel的修改，不过已留接口。

//可参考:多级汇总分组统计报表/无限极分组统计报表/无限极交叉分组报表
//汇总单元格收缩，展开,
//分组汇总对象树
//Swing JTable复杂报表 			//http://blog.csdn.net/sjdl9396/article/details/6887800
//Swing JTable超复杂报表(二)	//http://blog.csdn.net/sjdl9396/article/details/7022528
//Swing JTable超复杂报表(三)	//http://blog.csdn.net/sjdl9396/article/details/7030431

public class Test implements ActionListener{   
       
    GridBagTable table;   
    public Test()   
    {   
        JFrame d = new JFrame();   
        DefaultTableModel model = new DefaultTableModel(5,5);   
           
        table = new GridBagTable(model);   
        table.setRowHeight(20);   
           
        JScrollPane pane = new JScrollPane(table);   
        d.getContentPane().add(pane, BorderLayout.CENTER);   
        JButton btn = new JButton("合并/拆分");   
        d.getContentPane().add(btn, BorderLayout.NORTH);   
        btn.addActionListener(this);   
        d.setBounds(0, 0, 400, 400);   
        d.setVisible(true);   
    }   
       
    public static void main(String[] fsd){   
        new Test();   
    }   
       
    public void actionPerformed(ActionEvent e) {   
        table.mergeCells(table.getSelectedRows(), table.getSelectedColumns());   
    }   
} 
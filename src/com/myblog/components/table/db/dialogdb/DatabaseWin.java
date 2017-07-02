package com.myblog.components.table.db.dialogdb;

import javax.swing.*;    
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 

class DatabaseWin extends JFrame implements ActionListener{//主窗口
       JMenuBar menubar;
       JMenu menu;
       JMenuItem itemShow,itemUpdate,itemInsert;
       ShowRecord showRecord;
       ModifyRecord modifyRecord;
       InsertRecord insertRecord;
       DatabaseWin(){
           menubar=new JMenuBar(); 
           menu=new JMenu("操作数据库");
           itemShow=new JMenuItem("显示记录");
           itemUpdate=new JMenuItem("更新记录");
           itemInsert=new JMenuItem("插入记录");
           itemShow.addActionListener(this);
           itemUpdate.addActionListener(this);
           itemInsert.addActionListener(this);
           menu.add(itemShow);
           menu.add(itemUpdate);
           menu.add(itemInsert);
           menubar.add(menu);
           showRecord=new ShowRecord("显示记录对话框");
           modifyRecord=new ModifyRecord("修改记录对话框");
           insertRecord=new InsertRecord("插入记录对话框");
           setJMenuBar(menubar);
           setBounds(100,100,370,250);
           setVisible(true);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       } 
       public void actionPerformed(ActionEvent e){
           if(e.getSource()==itemShow)
              showRecord.setVisible(true);
           else if(e.getSource()==itemUpdate)
              modifyRecord.setVisible(true);
           else if(e.getSource()==itemInsert)
              insertRecord.setVisible(true);
      }
}
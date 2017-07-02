package com.myblog.components.table.db.dialogdb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 
import javax.swing.border.*;
public class InsertRecord extends JDialog implements ActionListener{ //负责插入记录的类
     JLabel hintLabel;
     Object name[]={"学号","姓名","出生日期","身高"};
     Object a[][]=new Object[1][4];
     JTable table;
     JButton enterInsert; 
     Connection con;
     Statement sql; 
     ResultSet rs;
     String num;
     InsertRecord(String s){
        setTitle(s);
        hintLabel=new JLabel("输入新记录:");
        table=new JTable(a,name);
        enterInsert=new JButton("插入新记录");
        setLayout(null); 
        Box baseBox=Box.createHorizontalBox();
        baseBox.add(hintLabel);
        baseBox.add(new JScrollPane(table));
        baseBox.add(enterInsert);
        add(baseBox);
        baseBox.setBounds(10,40,600,38);
        enterInsert.addActionListener(this);
        setBounds(120,160,700,200);
    }
    public void actionPerformed(ActionEvent e){
       try{
    	   //con=DriverManager.getConnection("jdbc:odbc:hello","","");
    	   con=DriverManager.getConnection("jdbc:sqlite:Students.db3");
             sql=con.createStatement();
              String sqlCreate = "CREATE TABLE IF NOT EXISTS message (number,name,birthday,height);";
              int count = sql.executeUpdate(sqlCreate);
             int k=sql.executeUpdate
             ("INSERT INTO message VALUES('"+
              a[0][0]+"','"+a[0][1]+"','"+a[0][2]+"','"+a[0][3]+"')");
             if(k==1)
                JOptionPane.showMessageDialog
                (this,"插入记录成功","成功",JOptionPane.PLAIN_MESSAGE);     
             con.close();
       }  
       catch(SQLException ee){ 
             JOptionPane.showMessageDialog
             (this,"插入记录失败"+ee,"失败",JOptionPane.ERROR_MESSAGE);
       }    
    }      
}
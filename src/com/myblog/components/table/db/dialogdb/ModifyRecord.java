package com.myblog.components.table.db.dialogdb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 

import javax.swing.border.*;
public class ModifyRecord extends JDialog implements ActionListener{ //负责更新记录的类
     JLabel hintLabel;
     JTextField inputNumber;
     Object name[]={"姓名","出生日期","身高"};
     Object a[][]=new Object[1][3];
     JTable table;
     JButton enterModify; 
     Connection con;
     Statement sql; 
     ResultSet rs;
     String num;
     ModifyRecord(String s){
        setTitle(s);
        hintLabel=new JLabel("输入学号(回车确认):");
        inputNumber=new JTextField(20);
        table=new JTable(a,name);
        enterModify=new JButton("更新记录");
        setLayout(null); 
        Box baseBox=Box.createHorizontalBox();
        baseBox.add(hintLabel);
        baseBox.add(inputNumber);
        baseBox.add(new JScrollPane(table));
        baseBox.add(enterModify);
        add(baseBox);
        baseBox.setBounds(10,40,600,38);
        inputNumber.addActionListener(this);
        enterModify.addActionListener(this);
        setBounds(20,60,700,200);
    }
    public void actionPerformed(ActionEvent e){
       if(e.getSource()==inputNumber)
       try{ num=inputNumber.getText().trim();
            //con=DriverManager.getConnection("jdbc:odbc:hello","","");
            con=DriverManager.getConnection("jdbc:sqlite:Students.db3");
            sql=con.createStatement();
            rs=sql.executeQuery("SELECT * FROM message WHERE number='"+num+"'");
            boolean boo=rs.next();
            if(boo==false){
                   JOptionPane.showMessageDialog
                   (this,"学号不存在","提示",JOptionPane.WARNING_MESSAGE);
            }
            else{
                   a[0][0]=rs.getString(2);
                   a[0][1]=rs.getDate(3).toString();
                   a[0][2]=rs.getString(4);
                   table.repaint();
            }
           con.close();
       }  
       catch(SQLException ee){ 
           System.out.println(ee);
       }
      if(e.getSource()==enterModify){
           try{ 
        	   	//con=DriverManager.getConnection("jdbc:odbc:hello","","");
           		con=DriverManager.getConnection("jdbc:sqlite:Students.db3");
                sql=con.createStatement();
                sql.executeUpdate
                ("UPDATE message SET name='"+a[0][0]+
                  "',birthday='"+a[0][1]+
                  "',height='"+a[0][2]+"'WHERE number='"+num+"'");
                 JOptionPane.showMessageDialog
                   (this,"更新成功","成功",JOptionPane.PLAIN_MESSAGE);     
                 con.close();
           }  
           catch(SQLException ee){ 
                 JOptionPane.showMessageDialog
                (this,"更新失败"+ee,"失败",JOptionPane.ERROR_MESSAGE);
           }    
      } 
    }      
}
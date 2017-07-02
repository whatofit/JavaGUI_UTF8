package com.myblog.components.table.db.windowdb;

//刚学习的写的一些笔记和大家分享一下有什么不改进的地方希望大家能指正  

import java.awt.BorderLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.Vector;  

import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JMenu;  
import javax.swing.JMenuBar;  
import javax.swing.JMenuItem;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.JTextField;  
import javax.swing.table.DefaultTableModel;  

class Window extends JFrame implements ActionListener {  
  JMenuBar bar;  
  JMenu menuQuery, menuDelete, menuView;  
  JMenuItem delete, qurey, itemClose;  
  JTextField text;  
  JButton button;  
  JLabel label;  
  JPanel panel;  
  JScrollPane jscrollpane;  
  JTable table;  
  DefaultTableModel dtm;  
  Statement stmt;  
  ResultSet rs;  
  ResultSetMetaData rsmd;  
  DataBaseConnection dbc = new DataBaseConnection();  

  Window() {  
      init();  
      setBounds(300, 100, 850, 550);  
      setVisible(true);  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  }  

  public void init() {  
      setLayout(new BorderLayout());  
      // 菜单条和菜单  
      bar = new JMenuBar();  
      menuQuery = new JMenu("查询");  
      menuDelete = new JMenu("删除");  
      menuView = new JMenu("更新");  

      bar.add(menuQuery);  
      bar.add(menuDelete);  
      bar.add(menuView);  
      setJMenuBar(bar);  

      // 文件菜单  
      qurey = new JMenuItem("查询");  
      qurey.addActionListener(this);  
      menuQuery.add(qurey);  
      delete = new JMenuItem("删除");  
      delete.addActionListener(this);  
      menuDelete.add(delete);  

      // 插入数据  
      text = new JTextField(16);  
      button = new JButton("删除");  
      label = new JLabel("删除数据");  
      panel = new JPanel();  
      panel.add(label);  
      panel.add(text);  
      panel.add(button);  
      button.addActionListener(this);  
      add(panel, BorderLayout.SOUTH);  

  }  

  @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })  
  @Override  
  public void actionPerformed(ActionEvent e) {  
      Vector colum = new Vector();  
      Vector rows = new Vector();  

      // 查询  
      if (e.getSource() == qurey) {  
          rs = dbc.executeQuery("SELECT * FROM employee");  
          try {  
              rsmd = rs.getMetaData();  
              for (int i = 1; i <= rsmd.getColumnCount(); ++i)  
                  colum.addElement(rsmd.getColumnName(i));  
              while (rs.next()) {  
                  Vector currow = new Vector();  
                  for (int i = 1; i <= rsmd.getColumnCount(); ++i) {  
                      currow.addElement(rs.getString(i));  
                  }  
                  rows.addElement(currow);  
              }  
              table = new JTable(rows, colum);  
              add(table, BorderLayout.CENTER);  
              table.setVisible(true);  
              table.setRowHeight(50);  
              add(new JScrollPane(table), BorderLayout.CENTER);  
              // table.setFillsViewportHeight(true);  

          } catch (SQLException e1) {  
              // TODO Auto-generated catch block  
              e1.printStackTrace();  
          }  
          show();  

      }  

  }
  
  public static void main(String[] args) {  
      Window win = new Window();  
      win.setTitle("小学期作业");  
  }  

}  
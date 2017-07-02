package com.myblog.components.list;

import java.awt.Dimension;  
import java.util.Date;  
import java.util.Vector;  
  
import javax.swing.DefaultListModel;  
import javax.swing.JFrame;  
import javax.swing.JList;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.ListSelectionModel;  
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;  
import javax.swing.table.DefaultTableModel;  

/*
JList: 
public JList(ListModel lm); 
public void setModel(ListModel lm); 
getSelectionModel().addListSelectionListener(ListSelectionListener lsl); //JList上的选择事件和JTabel相同 
DefaultListModel: 
public void addElement(Object o); //加入节点 
public void removeElement(Object o); //移除节点 
public int getSelectedIndex(); //获取被选中项的索引 
public int[] getSelectedIndices(); //获取被选中项的索引 
public Object getElementAt(int i); 
public void setSelectedValue(Object o); 
 * */
public class JFrameJList extends JFrame {  
    public JFrameJList() {  
        init();  
  
        this.setTitle("JList例子");  
        this.setSize(new Dimension(200, 450));  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
  
    private void init() {  
        final JList list = new JList();  
        DefaultListModel defaultListModel = new DefaultListModel();  
          
        defaultListModel.addElement("黄飞鸿");  
        defaultListModel.addElement("霍元甲");  
        defaultListModel.addElement("姚家兴");  
        defaultListModel.addElement("陈真");  
        defaultListModel.addElement("叶问");  
        defaultListModel.addElement("李小龙");  
        defaultListModel.addElement("方世玉");  
          
        list.setModel(defaultListModel);  
          
        //单选  
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        //加入选择事件  
        list.getSelectionModel().addListSelectionListener(new ListSelectionListener(){  
  
            public void valueChanged(ListSelectionEvent e) {  
                if(e.getValueIsAdjusting()){  
                    int index = list.getSelectedIndex();  
                    System.out.println(index);  
                    String selectedItem = list.getSelectedValue().toString();  
                    System.out.println("选中值:"+selectedItem);  
                      
                }  
                  
            }});          
  
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setViewportView(list);  
        this.add(scrollPane);  
  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        new JFrameJList();  
  
    }  
  
}  
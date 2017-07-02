package com.myblog.components.table.base;


import java.awt.Dimension;  
import java.util.Date;  
import java.util.Vector;  
  
import javax.swing.JFrame;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.table.DefaultTableModel;  

/*
1、JTable 
JTable构造方法: 
JTable(TableModel dm) 
JTable(object[][]rowData,object[]columnNames) 
JTable(Vector  rowData,Vector  columnNames) 

JTable类常用的方法有： 
getModel() //获得表格的数据来源对象 
getSelectedRow() //获得选中的行数 
 * */
public class JFrameTable extends JFrame {  
    public JFrameTable() {  
        init();  
  
        this.setTitle("表格的例子");  
        this.setSize(new Dimension(400, 450));  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
  
    private void init() {  
//      // 1.二维数组初始化  
//      String[] columnHeader = { "编号", "姓名", "年龄", "地址" };  
//      String[][] data = new String[][] { { "user1", "小明", "26", "福州鼓楼" },  
//              { "user2", "小明2", "28", "福州鼓楼2" },  
//              { "user3", "小明3", "33", "福州鼓楼" },  
//              { "user4", "小明4", "26", "福州鼓楼" } };  
  
//        
//      //2、实现了tableModel接口  
//      DefaultTableModel defaultTableModel = new DefaultTableModel(data,columnHeader);  
        //设置模型方式  
//      table.setModel(defaultTableModel);  
      
          
          
        //3、vector  
        Vector<String> colHeader = new Vector<String>();  
        colHeader.add("编号");  
        colHeader.add("名字");  
        colHeader.add("性别");  
        colHeader.add("日期");  
          
        Vector<Vector<String>> dataVec = new Vector<Vector<String>>();  
        Vector<String> row1 = new Vector<String>();  
        row1.add("0001");  
        row1.add("旺财");  
        row1.add("男");  
        row1.add(new Date().toString());  
        Vector<String> row2 = new Vector<String>();  
        row2.add("0002");  
        row2.add("小强");  
        row2.add("男");  
        row2.add(new Date().toString());  
        Vector<String> row3 = new Vector<String>();  
        row3.add("0003");  
        row3.add("韦小宝");  
        row3.add("女");  
        row3.add(new Date().toString());  
        Vector<String> row4 = new Vector<String>();  
        row4.add("0004");  
        row4.add("零零七");  
        row4.add("男");  
        row4.add(new Date().toString());  
          
        dataVec.add(row1);  
        dataVec.add(row2);  
        dataVec.add(row3);  
        dataVec.add(row4);  
          
        JTable table = new JTable(dataVec,colHeader);  
        //要显示表头必须要加入到滚动面板 滚动面板加入到窗体  
//      JScrollPane scrollPane = new JScrollPane(table);  
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setViewportView(table);  
        this.add(scrollPane);  
  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        new JFrameTable();  
  
    }  
  
}  

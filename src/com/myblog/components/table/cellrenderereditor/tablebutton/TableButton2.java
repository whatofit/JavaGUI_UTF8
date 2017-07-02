package com.myblog.components.table.cellrenderereditor.tablebutton;

import java.awt.EventQueue;  

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableButton2  
{  
  
    private JFrame frame;  
    private JTable table;  
  
    /** 
     * Launch the application. 
     */  
    public static void main(String[] args)  
    {  
        EventQueue.invokeLater(new Runnable()  
        {  
            public void run()  
            {  
                try  
                {  
                	TableButton2 window = new TableButton2();  
                    window.frame.setVisible(true);  
                }  
                catch (Exception e)  
                {  
                    e.printStackTrace();  
                }  
            }  
        });  
    }  
  
    /** 
     * Create the application. 
     */  
    public TableButton2()  
    {  
        this.initialize();  
    }  
  
    /** 
     * Initialize the contents of the frame. 
     */  
    private void initialize()  
    {  
        this.frame = new JFrame();  
        this.frame.setBounds(100, 100, 450, 300);  
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.frame.getContentPane().setLayout(null);  
  
        JPanel panel = new JPanel();  
        panel.setBounds(10, 10, 414, 242);  
        this.frame.getContentPane().add(panel);  
        panel.setLayout(null);  
  
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setBounds(10, 10, 394, 222);  
        panel.add(scrollPane);  
  
        this.table = new JTable();  
        scrollPane.setViewportView(this.table);  
  
        this.table.setModel(new DefaultTableModel()  
        {  
            @Override  
            public Object getValueAt(int row, int column)  
            {  
            	//System.out.println("getValueAt,row="+row+",column="+column);  
                return (row + 3) * (column + 3);  
            }  
  
            @Override  
            public int getRowCount()  
            {  
                return 3;  
            }  
  
            @Override  
            public int getColumnCount()  
            {  
                return 3;  
            }  
  
            @Override  
            public void setValueAt(Object aValue, int row, int column)  
            {  
                System.out.println(aValue + "  setValueAt");  
            }  
  
            @Override  
            public boolean isCellEditable(int row, int column)  
            {  
                // 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。
                // 对列2来说，还需要启用可编辑功能才行，不然仍然触发不了点击事件的。
                if (column == 2)  
                {  
                    return true;  
                }  
                else  
                {  
                    return false;  
                }  
            }  
        });  
  
        //修改表格的默认编辑器：
        this.table.getColumnModel().getColumn(2).setCellEditor(new MyButtonEditor());  
  
        //修改表格的默认渲染器：//只有获取到表格的列后才能添加渲染器。
        this.table.getColumnModel().getColumn(2).setCellRenderer(new MyButtonRender());  
  
        this.table.setRowSelectionAllowed(false);// 禁止表格的选择功能。不然在点击按钮时表格的整行都会被选中。也可以通过其它方式来实现。   
    }  
} 
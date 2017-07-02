package com.myblog.components.table.base;
//package table;
//
//import javax.swing.*;  
//import javax.swing.table.*;  
//import java.awt.event.*;  
//import java.util.*;  
//import java.awt.*;  
//public class JTableSortTest  
//{  
//    public static void main(String[] arg){  
//        new JTableSortFrame();  
//    }  
//}  
//class JTableSortFrame extends JFrame  
//{  
//    private JTable table;  
//    private DefaultTableModel model;///实际存储数据的TableModel  
//    private SortTableModel sortModel;///逻辑层TableModel  
//    private Object[][] cells = { { "Mercury", 2440.0, 0, false, Color.YELLOW },  
//         { "Venus", 6052.0, 0, false, Color.YELLOW }, { "Earth", 6378.0, 1, false, Color.BLUE },  
//         { "Mars", 3397.0, 2, false, Color.RED }, { "Jupiter", 71492.0, 16, true, Color.ORANGE },  
//         { "Saturn", 60268.0, 18, true, Color.ORANGE },  
//         { "Uranus", 25559.0, 17, true, Color.BLUE }, { "Neptune", 24766.0, 8, true, Color.BLUE },  
//         { "Pluto", 1137.0, 1, false, Color.BLACK } };  
//   private String[] columnNames = { "Planet", "Radius", "Moons", "Gaseous", "Color" };  
//   public JTableSortFrame(){  
//        model=new DefaultTableModel(cells,columnNames);  
//        sortModel=new SortTableModel(model);  
//        table=new JTable(sortModel);  
//        //为列标题添加时间监听，响应双击事件  
//        table.getTableHeader().addMouseListener(  
//            new MouseAdapter(){  
//                public void mouseClicked(MouseEvent e){  
//                    if(e.getClickCount()<2)  
//                        return;  
//                    int colIndex=table.columnAtPoint(e.getPoint());  
//                    sortModel.setColumn(colIndex);  
//                    sortModel.sort();  
//                    sortModel.fireTableDataChanged();//通知关心此事件的监听器  
//                }  
//            }     
//        );  
//        add(new JScrollPane(table),BorderLayout.CENTER);  
//        setSize(400,200);  
//        setVisible(true);  
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//   }  
//}  
//  
//class SortTableModel extends AbstractTableModel  
//{  
//    private DefaultTableModel model;  
//    private Row[] Rows;//逻辑下标到物理下标的映射  
//    private int column;//当前双击的列,依据此列进行排序  
//         public SortTableModel(DefaultTableModel _model){  
//        this.model=_model;  
//        //初始化Rows及其index  
//        Rows=new Row[model.getRowCount()];  
//        for(int i=0;i<Rows.length;i++)  
//            Rows[i]=new Row(i);  
//    }  
//    public int getRowCount(){  
//        return model.getRowCount();  
//    }  
//    public int getColumnCount(){  
//        return model.getColumnCount();  
//    }  
//    public void setColumn(int col){  
//        column=col;  
//    }  
//    public String getColumnName(int col){  
//        return model.getColumnName(col);  
//    }  
//    public Object getValueAt(int row,int column){  
//        return model.getValueAt(Rows[row].index,column);  
//    }  
//    //对相应列进行排序  
//    public void sort(){  
//        Arrays.sort(Rows);  
//    }  
//    private class Row implements Comparable<Row>  
//    {  
//        private int index;//物理索引  
//        public Row(int _index){  
//            index=_index;  
//        }  
//        public int getIndex(){  
//            return index;  
//        }  
//        public void setIndex(int _index){  
//            index=_index;  
//        }  
//        public int compareTo(Row other){  
//            if(model.getValueAt(index,column) instanceof Comparable)  
//                return ((Comparable)(model.getValueAt(index,column))).compareTo(model.getValueAt(other.getIndex(),column));  
//            return model.getValueAt(index,column).toString().compareTo(model.getValueAt(other.getIndex(),column).toString());  
//        }   
//    }  
//}  
package com.myblog.components.table.mergecells.combinecells;

//http://www.cnblogs.com/alanzyy/archive/2011/06/03/2072069.html
//JTable合并单元格 合并指定的一列或几列中行连续相同的单元格的值。
//为了合并单元格，我们需要重载（overwrite）Jtable的三个方法：getCellRect()，columnAtPoint()，and rowAtPoint()。第一个方法返回一个单元格的边界（Rectangle类），第二、三个方法分别返回屏幕指定位置的列和行。
//swing components 是使用ComponentUI对象来完成渲染的。所以我们需要找出渲染Jtable的ComponentUI对象，并且修改它以达到我们的目的。
//由于要实现多行多列单元格合并需要多个类相互协作，直接写出来的话可能比较复杂，所以我先讲一下跨列的单元格合并的方法，然后再提供一个完整的例子。
//由于swing里没有可记录单元格合并情况的数据模型，所以我们需要一个新的类，它要包涵一个方法来取得单元格的所跨越的列数。另外，为了使用Jtable画(paint)起来更容易些，我们需要一个方法来确定指定单元格是否被其它单元格所覆盖，被哪个单元格覆盖。

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
 
public class Test {
 
    public static void main(String args[]) {
        JFrame jf = new JFrame("Cell Combine Table");
        JTable cTable = getTable1();
 
        jf.getContentPane().add(new JScrollPane(cTable));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 500);
        jf.setVisible(true);
    }
 
    private static CombineTable getTable1() {
        String[][] datas = new String[10][6];
        for (int i = 0; i < datas.length; i++) {
            String[] data = datas[i];
            for (int j = 0; j < data.length; j++) {
                data[j] = "";
            }
            data[0] = String.valueOf((int) (i / 3));
        }
 
        ArrayList<Integer> combineColumns = new ArrayList<Integer>();
        combineColumns.add(0);
        CombineData m = new CombineData(datas, combineColumns);
        DefaultTableModel tm = new DefaultTableModel(datas, new String[]{"1", "2", "3", "4", "5"});
        CombineTable cTable = new CombineTable(m, tm);
 
        TableColumn column = cTable.getColumnModel().getColumn(0);
        column.setCellRenderer(new CombineColumnRender());
        column.setWidth(50);
        column.setMaxWidth(50);
        column.setMinWidth(50);
        cTable.setCellSelectionEnabled(true);
        return cTable;
    }
 
    private static CombineTable getTable2() {
        String[][] datas = new String[10][6];
        for (int i = 0; i < datas.length; i++) {
            String[] data = datas[i];
            for (int j = 0; j < data.length; j++) {
                data[j] = "";
            }
            data[0] = String.valueOf((int) (i / 4));
            data[1] = String.valueOf((int) (i / 2));
        }
 
        CombineData m = new CombineData(datas, 0, 1);
        DefaultTableModel tm = new DefaultTableModel(datas, new String[]{"1", "2", "3", "4", "5"});
        CombineTable cTable = new CombineTable(m, tm);
 
        TableColumnModel columnModel = cTable.getColumnModel();
        for (Integer integer : m.combineColumns) {
            TableColumn column = columnModel.getColumn(integer);
            column.setCellRenderer(new CombineColumnRender());
            column.setWidth(50);
            column.setMaxWidth(50);
            column.setMinWidth(50);
        }
 
        cTable.setCellSelectionEnabled(true);
        return cTable;
    }
}
package com.myblog.components.table.cellrenderereditor.tablecellcomponent;

import java.awt.Color;
import java.awt.Component;
import java.util.Hashtable;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * This interface defines the method required by any object * that would like to be a renderer for cells in a JTable
 * in there, I put progress bar in it.
*/
public class MyProgressCellRenderer extends JProgressBar implements TableCellRenderer {
	//它提供一个属性放置各个颜色区间需要设置的颜色:
    /** the progress bar's color. */
    private Hashtable<Integer, Color> limitColors = null;

    //在构造函数里我们设置显示的最大和最小值:
    /**
    * Creates a progress bar using the specified orientation, * minimum, and maximum.
    */
    public MyProgressCellRenderer(int min, int max) {
       super(JProgressBar.HORIZONTAL, min, max);
       setBorderPainted(false);
    }

	//然后实现TableCellRenderer接口的getTableCellRendererComponent方法，设置显示组件和颜色:
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	System.out.println(row + " and  " + column);
    	//先根据单元格的值取得颜色:
        //Color color = getColor(n);
        //if (color != null) {
        //   setForeground(color);
        //}

       //同时设置JProcessBar的值并返回它.
       //setValue(n);
       return this;
    }
    
	//最后还提供一个设置颜色的方法:
    public void setLimits(Hashtable<Integer, Color> limitColors) {
    	//它把传入的颜色表按照大小先排序,然后设置好.
    }
}
package com.myblog.components.table.mergecells.combinecells;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
 
public class CombineTable extends JTable {
 
    public CombineData combineData;
 
    public CombineTable(TableModel tableModel) {
        super(tableModel);
        super.setUI(new CombineTableUI());
    }
 
    public CombineTable(CombineData combineData, TableModel tableModel) {
        super(tableModel);
        this.combineData = combineData;
 
        for (Integer column : combineData.combineColumns) {
            TableColumn tableColumn = super.columnModel.getColumn(column);
            tableColumn.setCellRenderer(new CombineColumnRender());
        }
        super.setUI(new CombineTableUI());
    }
 
    public void setCombineData(CombineData combineData) {
        this.combineData = combineData;
 
        for (Integer column : combineData.combineColumns) {
            TableColumn tableColumn = super.columnModel.getColumn(column);
            tableColumn.setCellRenderer(new CombineColumnRender());
        }
    }
 
    @Override
    public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
        // required because getCellRect is used in JTable constructor
        if (combineData == null) {
            return super.getCellRect(row, column, includeSpacing);
        }
        // add widths of all spanned logical cells
        int sk = combineData.visibleCell(row, column);
        //Rectangle r1 = super.getCellRect(row, sk, includeSpacing);
        Rectangle rect1 = super.getCellRect(sk, column, includeSpacing);
        if (combineData.span(sk, column) != 1) {
            for (int i = 1; i < combineData.span(sk, column); i++) {
                //r1.width += getColumnModel().getColumn(sk + i).getWidth();
                rect1.height += this.getRowHeight(sk + i);
            }
        }
        return rect1;
    }
 
    @Override
    public int rowAtPoint(Point p) {
        int column = super.columnAtPoint(p);
        // -1 is returned by columnAtPoint if the point is not in the table
        if (column < 0) {
            return column;
        }
        int row = super.rowAtPoint(p);
        return combineData.visibleCell(row, column);
    }
 
    @Override
    public boolean isCellEditable(int row, int column) {
        if (combineData.combineColumns.contains(column)) {
            return false;
        }
        return super.isCellEditable(row, column);
    }
 
    @Override
    public boolean isCellSelected(int row, int column) {
        if (combineData.combineColumns.contains(column)) {
            return false;
        }
        return super.isCellSelected(row, column);
    }
}
package com.myblog.components.table.mergecells.ctable;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class CTable extends JTable {
	public CMap map;

	public CTable(CMap cmp, TableModel tbl) {
		super(tbl);
		map = cmp;
		setUI(new CTUI());// 设置Jtable的渲染UI
	}

	public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
		// required because getCellRect is used in JTable constructor
		//该方法是Jtable构建时所必须的
		if (map == null)
			return super.getCellRect(row, column, includeSpacing);
		// add widths of all spanned logical cells
		//指定单元格的可视单元格列值
		int sk = map.visibleCell(row, column);
		
// 		Rectangle r1 = super.getCellRect(row, sk, includeSpacing);
//		// 如果指定单元格列宽不为1，累计出跨列单元格的宽度
//		if (map.span(row, sk) != 1)
//			for (int i = 1; i < map.span(row, sk); i++) {
//				r1.width += getColumnModel().getColumn(sk + i).getWidth();
//			}
		
		Rectangle r1 = super.getCellRect(sk, column, includeSpacing);
		if (map.span(sk, column) != 1)
			for (int i = 1; i < map.span(sk, column); i++) {
				// r1.width += getColumnModel().getColumn(sk + i).getWidth();
				r1.height += this.getRowHeight(sk + i);
			}
		return r1;
	}

	public int rowAtPoint(Point p) {
		int x = super.columnAtPoint(p);
		// -1 is returned by columnAtPoint if the point is not in the table
		if (x < 0)
			return x;
		int y = super.rowAtPoint(p);
		return map.visibleCell(y, x);
	}
	
//	public int columnAtPoint(Point p) {
//		int x = super.columnAtPoint(p);
//		// 当指定位置不在Table内时，返回－1
//		if (x < 0)
//			return x;
//		int y = super.rowAtPoint(p);
//		// 获取指定位置可视单元格的列值
//		return map.visibleCell(y, x);
//	}

}
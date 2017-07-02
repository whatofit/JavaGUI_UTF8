package com.myblog.components.table.mergecells.ctable;

/******************************************************************************************
 CMap1对CMap地实现
 span( ) 表示合并的单元格的列，返回的是合并的格数。
 visibleCell() 表示要渲染的格。返回的渲染的开始格的行。
 本程序的table是16行10列，合并的单元格是第一列和最后一列（最后一列是第10列）每两个行。
 *******************************************************************************************/
import javax.swing.*;
import javax.swing.table.*;

class CMap1 implements CMap {
	public int span(int row, int column) {
		if (column == 0 || column == 9)
			return 2;
		return 1;
	}

	public int visibleCell(int row, int column) {
		if (((row >= 0) && (row < 2)) && (column == 0 || column == 9))
			return 0;
		if (((row >= 2) && (row < 4)) && (column == 0 || column == 9))
			return 2;
		if (((row >= 4) && (row < 6)) && (column == 0 || column == 9))
			return 4;
		if (((row >= 6) && (row < 8)) && (column == 0 || column == 9))
			return 6;
		if (((row >= 8) && (row < 10)) && (column == 0 || column == 9))
			return 8;
		if (((row >= 10) && (row < 12)) && (column == 0 || column == 9))
			return 10;
		if (((row >= 12) && (row < 14)) && (column == 0 || column == 9))
			return 12;
		if (((row >= 14) && (row < 16)) && (column == 0 || column == 9))
			return 14;
		System.out.println(">>>row = " + row + "column = " + column);
		return row;
	}
}
package com.myblog.components.table.mergecells.ctable;

//http://blog.csdn.net/tjh666/article/details/1671113
//最近，我为了做一个管理系统，需要用到合并JTable的单元格。查找了很多资料，终于简单的实现了。现在把代码共享出来，希望对大家有用。
//本程序主要实现行的合并，列的合并大家可以根据下面的代码修改。

import javax.swing.*;
import javax.swing.table.*;

public class CTest {
	public static void main(String args[]) {
		JFrame jf = new JFrame("Table with cell spanning");
		CMap m = new CMap1();
		DefaultTableModel tm = new DefaultTableModel(16, 10) {
			public boolean isCellEditable(int indexRow, int indexColumn) {
				return false;
			}
		};
		// tm.isCellEditable( 16, 10 );
		tm.setValueAt("port1", 0, 0);// 对一个合并的单元格填一个数据。
		tm.setValueAt("port2", 1, 1);// 对一个合并的单元格填一个数据。
		jf.getContentPane().add(new JScrollPane(new CTable(m, tm)));
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setSize(500, 500);
		jf.show();
	}
}
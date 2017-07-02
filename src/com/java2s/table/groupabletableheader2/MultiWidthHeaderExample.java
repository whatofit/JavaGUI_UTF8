package com.java2s.table.groupabletableheader2;

//Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
/*
 *  (swing1.1beta3)
 * 
 * |-----------------------------------------------------|
 * |   1st  |      2nd        |          3rd             |
 * |-----------------------------------------------------|
 * |        |        |        |        |        |        |
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @version 1.0 11/09/98
 */
public class MultiWidthHeaderExample extends JFrame {

	MultiWidthHeaderExample() {
		super("Multi-Width Header Example");

		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(new Object[][] { { "a", "b", "c", "d", "e", "f" },
				{ "A", "B", "C", "D", "E", "F" } }, new Object[] { "1 st", "",
				"", "", "", "" });

		JTable table = new JTable(dm) {
			protected JTableHeader createDefaultTableHeader() {
				return new GroupableTableHeader(columnModel);
			}
		};
		TableColumnModel cm = table.getColumnModel();
		ColumnGroup g_2nd = new ColumnGroup("2 nd");
		g_2nd.add(cm.getColumn(1));
		g_2nd.add(cm.getColumn(2));
		ColumnGroup g_3rd = new ColumnGroup("3 rd");
		g_3rd.add(cm.getColumn(3));
		g_3rd.add(cm.getColumn(4));
		g_3rd.add(cm.getColumn(5));
		GroupableTableHeader header = (GroupableTableHeader) table
				.getTableHeader();
		header.addColumnGroup(g_2nd);
		header.addColumnGroup(g_3rd);
		JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll);
		setSize(400, 100);
		header.revalidate();
	}

	public static void main(String[] args) {
		MultiWidthHeaderExample frame = new MultiWidthHeaderExample();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}
package com.java2s.table;

//Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html

/* (swing1.1beta3) */

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @version 1.0 11/09/98
 */
public class MultiLineHeaderExample extends JFrame {
	MultiLineHeaderExample() {
		super("Multi-Line Header Example");

		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(
				new Object[][] { { "a", "b", "c" }, { "A", "B", "C" } },
				new Object[] { "1st\nalpha", "2nd\nbeta", "3rd\ngamma" });

		JTable table = new JTable(dm);
		MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
		Enumeration e = table.getColumnModel().getColumns();
		while (e.hasMoreElements()) {
			((TableColumn) e.nextElement()).setHeaderRenderer(renderer);
		}
		JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll);
		setSize(400, 110);
		setVisible(true);
	}

	public static void main(String[] args) {
		MultiLineHeaderExample frame = new MultiLineHeaderExample();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

/**
 * @version 1.0 11/09/98
 */

class MultiLineHeaderRenderer extends JList implements TableCellRenderer {
	public MultiLineHeaderRenderer() {
		setOpaque(true);
		setForeground(UIManager.getColor("TableHeader.foreground"));
		setBackground(UIManager.getColor("TableHeader.background"));
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		ListCellRenderer renderer = getCellRenderer();
		((JLabel) renderer).setHorizontalAlignment(JLabel.CENTER);
		setCellRenderer(renderer);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setFont(table.getFont());
		String str = (value == null) ? "" : value.toString();
		BufferedReader br = new BufferedReader(new StringReader(str));
		String line;
		Vector v = new Vector();
		try {
			while ((line = br.readLine()) != null) {
				v.addElement(line);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		setListData(v);
		return this;
	}
}

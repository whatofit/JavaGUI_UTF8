package com.java2s.table;

//Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html

/* (swing1.1) */

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 * @version 1.0 02/25/99
 */
public class ToolTipHeaderTableExample extends JPanel {

	public ToolTipHeaderTableExample() {
		setLayout(new BorderLayout());
		String[] headerStr = { "default", "jw", "ja", "la", "unknown" };
		String[] toolTipStr = { "", "Javanese", "Japanese", "Latin" };

		DefaultTableModel dm = new DefaultTableModel(headerStr, 4);
		JTable table = new JTable(dm);

		ToolTipHeader header = new ToolTipHeader(table.getColumnModel());
		header.setToolTipStrings(toolTipStr);
		header.setToolTipText("Default ToolTip TEXT");
		table.setTableHeader(header);

		JScrollPane pane = new JScrollPane(table);
		add(pane, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("ToolTipHeaderTable Example");
		f.getContentPane().add(new ToolTipHeaderTableExample(),
				BorderLayout.CENTER);
		f.setSize(400, 100);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	class ToolTipHeader extends JTableHeader {
		String[] toolTips;

		public ToolTipHeader(TableColumnModel model) {
			super(model);
		}

		public String getToolTipText(MouseEvent e) {
			int col = columnAtPoint(e.getPoint());
			int modelCol = getTable().convertColumnIndexToModel(col);
			String retStr;
			try {
				retStr = toolTips[modelCol];
			} catch (NullPointerException ex) {
				retStr = "";
			} catch (ArrayIndexOutOfBoundsException ex) {
				retStr = "";
			}
			if (retStr.length() < 1) {
				retStr = super.getToolTipText(e);
			}
			return retStr;
		}

		public void setToolTipStrings(String[] toolTips) {
			this.toolTips = toolTips;
		}
	}
}

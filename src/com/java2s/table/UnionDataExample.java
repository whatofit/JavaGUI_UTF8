package com.java2s.table;

//Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html

/* (swing1.1beta3) */

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * @version 1.0 11/09/98
 */
public class UnionDataExample extends JFrame {

	UnionDataExample() {
		super("UnionData Example");
		setSize(300, 200);

		final String[] headers = { "a", "b", "c", "d" };
		final String[][] data = new String[6][headers.length];

		final AbstractTableModel dmAB = new AbstractTableModel() {
			public int getRowCount() {
				return data.length;
			}

			public int getColumnCount() {
				return headers.length;
			}

			public String getColumnName(int col) {
				return headers[col];
			}

			public Object getValueAt(int row, int col) {
				return data[row][col];
			}
		};

		final int rowCountA = 3;
		DefaultTableModel dmA = new DefaultTableModel(data, headers) {
			public int getRowCount() {
				return rowCountA;
			}

			public Object getValueAt(int row, int col) {
				Vector rowVector = (Vector) dataVector.elementAt(row);
				return rowVector.elementAt(col);
			}

			public void setValueAt(Object value, int row, int col) {
				data[row][col] = (String) value;
				Vector rowVector = (Vector) dataVector.elementAt(row);
				rowVector.setElementAt(value, col);
				TableModelEvent event = new TableModelEvent(this, row, row, col);
				fireTableChanged(event);
				dmAB.fireTableChanged(event);
			}
		};

		DefaultTableModel dmB = new DefaultTableModel(data, headers) {
			public int getRowCount() {
				return rowCountA;
			}

			// public int getRowCount() { return data.length - rowCountA; }
			public Object getValueAt(int row, int col) {
				// Vector rowVector = (Vector) dataVector.elementAt(row
				// + rowCountA);
				Vector rowVector = (Vector) dataVector.elementAt(2);

				return rowVector.elementAt(col);
			}

			public void setValueAt(Object value, int row, int col) {
				int sRow = row + rowCountA;
				data[sRow][col] = (String) value;
				Vector rowVector = (Vector) dataVector.elementAt(sRow);
				rowVector.setElementAt(value, col);
				TableModelEvent event = new TableModelEvent(this, sRow, sRow,
						col);
				fireTableChanged(event);
				dmAB.fireTableChanged(event);
			}
		};

		JScrollPane scrollA = new JScrollPane(new JTable(dmA));
		JScrollPane scrollB = new JScrollPane(new JTable(dmB));
		JScrollPane scrollAB = new JScrollPane(new JTable(dmAB));

		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(new JLabel("Table A"));
		box.add(scrollA);
		box.add(new JSeparator(SwingConstants.VERTICAL));
		box.add(new JLabel("Table B"));
		box.add(scrollB);
		Box boxAB = new Box(BoxLayout.Y_AXIS);
		boxAB.add(new JLabel("Table A + B"));
		boxAB.add(scrollAB);
		Container content = getContentPane();
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.add(box);
		content.add(new JSeparator(SwingConstants.HORIZONTAL));
		content.add(boxAB);
	}

	public static void main(String[] args) {
		UnionDataExample frame = new UnionDataExample();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}
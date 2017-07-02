package com.myblog.components.table.base;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

public class RowAdder extends JFrame {

	final SimpleModel tableData = new SimpleModel();
	JTable table = new JTable(tableData);

	public static void main(String[] args) {
		RowAdder ra = new RowAdder();
		ra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ra.setSize(400, 300);
		ra.setVisible(true);
	}

	public RowAdder() {
		final JTextField textField = new JTextField();
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				tableData.addText(textField.getText());
				textField.setText("");
			}
		});
		add(textField, BorderLayout.SOUTH);
	}

}

class SimpleModel extends AbstractTableModel {

	Vector textData = new Vector();

	public void addText(String text) {
		textData.addElement(text);
		fireTableDataChanged();
	}

	public int getRowCount() {
		return textData.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public Object getValueAt(int row, int column) {
		return textData.elementAt(row);
	}

}
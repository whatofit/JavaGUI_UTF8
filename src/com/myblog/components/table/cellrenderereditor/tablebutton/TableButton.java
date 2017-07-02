package com.myblog.components.table.cellrenderereditor.tablebutton;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TableButton extends JPanel {
	JTable table;

	public TableButton() {
		setLayout(new BorderLayout());
		table = new JTable(new TestModel());
		table.setCellSelectionEnabled(true);

		table.getColumn("Button").setCellRenderer(new MyTableCellRenderer());

		JScrollPane pane = new JScrollPane(table);
		add(pane, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Sample   Table");
		TableButton sample = new TableButton();
		f.getContentPane().add(sample, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 200);
		f.setVisible(true);
	}
}

class MyTableCellRenderer extends JButton implements TableCellRenderer {

	public MyTableCellRenderer() {
		super();
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		this.setText((value == null) ? "" : ((JButton) value).getText());

		if (isSelected) {

			int choose = JOptionPane.showConfirmDialog(null, "第" + row + "行"
					+ "第" + column + "列" + "   JButton   Clicked", "Test",
					JOptionPane.OK_CANCEL_OPTION);
			if (choose == JOptionPane.OK_OPTION) {
				super.setForeground(Color.red);
				super.setBackground(table.getSelectionBackground());
			} else {
				super.setForeground(Color.blue);
				super.setBackground(table.getSelectionBackground());
			}
			super.setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			super.setForeground(table.getForeground());
			super.setBackground(table.getBackground());
		}

		if (hasFocus) {
			super.setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			super.setForeground(table.getForeground());
			super.setBackground(table.getBackground());
		}

		return this;
	}
}

class TestModel extends AbstractTableModel {

	Object data[][] = { { "Jon", "Apple", new JButton("Apple") },
			{ "Marry", "Pine", new JButton("Pine") },
			{ "Ben", "Peach", new JButton("Peach") },
			{ "Mike", "Orange", new JButton("Orange") },
			{ "Patty", "Apple", new JButton("Apple") },
			{ "Jimmy", "Lemon", new JButton("Lemon") },
			{ "Jon", "Apple", new JButton("Apple") },
			{ "Marry", "Pine", new JButton("Pine") },
			{ "Ben", "Peach", new JButton("Peach") },
			{ "Mike", "Orange", new JButton("Orange") },
			{ "Patty", "Apple", new JButton("Apple") },
			{ "Jimmy", "Lemon", new JButton("Lemon") }, };

	Object names[] = { "NAME", "FURUTS", "Button" };

	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return names.length;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public String getColumnName(int colIndex) {
		return names[colIndex].toString();
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return getColumnClass(col) == String.class;
	}

	public void setValueAt(Object aValue, int row, int col) {
		data[row][col] = aValue;
	}
}

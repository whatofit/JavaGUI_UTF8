package com.myblog.components.table.cellrenderereditor.tablebutton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public final class TableButton6 extends JFrame {
	public TableButton6() {
		super("JTableButton6 Demo");
		JTable table = new JTable(new JTableButtonModel());
		TableCellRenderer defaultRenderer = table.getDefaultRenderer(JButton.class);
		table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(
				defaultRenderer));
		table.setPreferredScrollableViewportSize(new Dimension(400, 200));
		table.addMouseListener(new JTableButtonMouseListener(table));

		JScrollPane scrollPane = new JScrollPane(table);
		setContentPane(scrollPane);
	}

	public static void main(String[] args) {
		WindowListener exitListener = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Window window = e.getWindow();
				window.setVisible(false);
				window.dispose();
				System.exit(0);
			}
		};

		Frame frame = new TableButton6();
		frame.addWindowListener(exitListener);
		frame.pack();
		frame.setVisible(true);
	}
}

class JTableButtonRenderer implements TableCellRenderer {
	private TableCellRenderer __defaultRenderer;

	public JTableButtonRenderer(TableCellRenderer renderer) {
		__defaultRenderer = renderer;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof Component)
			return (Component) value;
		return __defaultRenderer.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
	}
}

class JTableButtonModel extends AbstractTableModel {
	private Object[][] __rows = { 
			{ "One", new JButton("Button One") },
			{ "Two", new JButton("Button Two") },
			{ "Three", new JButton("Button Three") },
			{ "Four", new JButton("Button Four") } };

	private String[] __columns = { "Numbers", "Buttons" };

	public String getColumnName(int column) {
		return __columns[column];
	}

	public int getRowCount() {
		return __rows.length;
	}

	public int getColumnCount() {
		return __columns.length;
	}

	public Object getValueAt(int row, int column) {
		return __rows[row][column];
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}
}

class JTableButtonMouseListener implements MouseListener {
	private JTable __table;

	private void __forwardEventToButton(MouseEvent e) {
		TableColumnModel columnModel = __table.getColumnModel();
		int column = columnModel.getColumnIndexAtX(e.getX());
		int row = e.getY() / __table.getRowHeight();
		Object value;
		JButton button;
		MouseEvent buttonEvent;

		if (row >= __table.getRowCount() || row < 0
				|| column >= __table.getColumnCount() || column < 0)
			return;

		value = __table.getValueAt(row, column);

		if (!(value instanceof JButton))
			return;

		button = (JButton) value;

		buttonEvent = (MouseEvent) SwingUtilities.convertMouseEvent(__table, e,
				button);
		button.dispatchEvent(buttonEvent);
		// This is necessary so that when a button is pressed and released
		// it gets rendered properly. Otherwise, the button may still appear
		// pressed down when it has been released.
		__table.repaint();
	}

	public JTableButtonMouseListener(JTable table) {
		__table = table;
	}

	public void mouseClicked(MouseEvent e) {
		__forwardEventToButton(e);
	}

	public void mouseEntered(MouseEvent e) {
		__forwardEventToButton(e);
	}

	public void mouseExited(MouseEvent e) {
		__forwardEventToButton(e);
	}

	public void mousePressed(MouseEvent e) {
		__forwardEventToButton(e);
	}

	public void mouseReleased(MouseEvent e) {
		__forwardEventToButton(e);
	}
}
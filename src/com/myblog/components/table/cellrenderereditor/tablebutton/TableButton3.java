package com.myblog.components.table.cellrenderereditor.tablebutton;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TableButton3 extends JFrame {

	JTable t;

	public TableButton3() {
		super("TableButton3 Demo");
		setBounds(100, 100, 400, 300);
		JPanel pane = new JPanel();

		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(new Object[][] { { "Button 1", "foo" },
				{ "Button 2", "bar" }, { "Button 2", "bar" },
				{ "Button 2", "bar" }, { "Button 2", "bar" },
				{ "Button 2", "bar" } }, new Object[] { "Button", "String" });

		t = new JTable(dm);

		t.getColumn("Button").setCellRenderer(new ButtonRenderer());
		t.getColumn("Button").setCellEditor(new ButtonEditor(new JTextField()));

		pane.setLayout(new FlowLayout());
		pane.add(t);
		this.add(pane);
		setVisible(true);
	}

	public static void main(String[] args) {
		TableButton3 test1 = new TableButton3();
	}
}

class ButtonEditor extends DefaultCellEditor {

	protected JButton button;
	private String label;
	private boolean isPushed;

	public ButtonEditor(JTextField checkBox) {
		super(checkBox);
		this.setClickCountToStart(1);
		button = new JButton();
		//button.setBounds(0, 0, 350, 50);
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});

	}

	public Component getTableCellEditorComponent(final JTable table,
			Object value, boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selected,Row,Column = "+ table.getSelectedRow() +","+table.getSelectedColumn() );
			}
		});
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			// JOptionPane.showMessageDialog(button, label + ": Ouch!");
			// System.out.println(label + ": Ouch!");
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		System.out.println("shouldSelectCell=");
		return super.shouldSelectCell(anEvent);
	}

}

class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("UIManager"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}
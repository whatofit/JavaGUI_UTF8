package com.myblog.components.table.editcell.ec1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

public class MyTable extends JTable {
	private String[] header;
	private Object[][] value;

	public MyTable(String[] header, Object[][] value) {
		this.header = header;
		this.value = value;
		init();
	}

	private void init() {
		DefaultTableModel dtm = new DefaultTableModel(value, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				JTextField tf = new JTextField();
				tf.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent e) {
						event(e);
					};
				});
				tf.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				tf.setSelectionStart(0);
				tf.setSelectionEnd(tf.getText().length());
				getColumnModel().getColumn(column).setCellEditor(
						new DefaultCellEditor(tf));
				return true;
			}
		};
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				event(e);
			}
		});

	}

	private void event(KeyEvent e) {
		int row = getSelectedRow();
		int column = getSelectedColumn();

		DefaultCellEditor obj = (DefaultCellEditor) (getColumnModel()
				.getColumn(column).getCellEditor());
		if (obj != null) {
			JComponent com = (JComponent) obj.getComponent();
			Object value = null;
			if (com instanceof JTextField) {
				value = ((JTextField) com).getText();
			} else if (com instanceof JToggleButton) {
				value = ((JToggleButton) com).isSelected();
			}

			System.out.println("row:" + row + " ,column:" + column + " ,value:"
					+ value);
		}

	}
}
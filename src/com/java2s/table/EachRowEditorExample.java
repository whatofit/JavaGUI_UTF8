package com.java2s.table;

//Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
/* (swing1.1.1) */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;
import java.util.Hashtable;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 * @version 1.1 09/09/99
 */
public class EachRowEditorExample extends JFrame {
	public EachRowEditorExample() {
		super("EachRow Editor Example");

		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(new Object[][] { { "Name", "MyName" },
				{ "Gender", "Male" } }, new Object[] { "Column1", "Column2" });

		JTable table = new JTable(dm);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Male");
		comboBox.addItem("Female");
		comboBox.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				final JComponent c = (JComponent) e.getSource();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						c.requestFocus();
						System.out.println(c);
						if (c instanceof JComboBox) {
							System.out.println("a");
						}
					}
				});
			}
		});

		EachRowEditor rowEditor = new EachRowEditor(table);
		rowEditor.setEditorAt(1, new DefaultCellEditor(comboBox));
		table.getColumn("Column2").setCellEditor(rowEditor);

		JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll, BorderLayout.CENTER);
		setSize(400, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		EachRowEditorExample frame = new EachRowEditorExample();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

/**
 * each row TableCellEditor
 * 
 * @version 1.1 09/09/99
 * @author Nobuo Tamemasa
 */

class EachRowEditor implements TableCellEditor {
	protected Hashtable editors;

	protected TableCellEditor editor, defaultEditor;

	JTable table;

	/**
	 * Constructs a EachRowEditor. create default editor
	 * 
	 * @see TableCellEditor
	 * @see DefaultCellEditor
	 */
	public EachRowEditor(JTable table) {
		this.table = table;
		editors = new Hashtable();
		defaultEditor = new DefaultCellEditor(new JTextField());
	}

	/**
	 * @param row
	 *            table row
	 * @param editor
	 *            table cell editor
	 */
	public void setEditorAt(int row, TableCellEditor editor) {
		editors.put(new Integer(row), editor);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// editor = (TableCellEditor)editors.get(new Integer(row));
		// if (editor == null) {
		// editor = defaultEditor;
		// }
		return editor.getTableCellEditorComponent(table, value, isSelected,
				row, column);
	}

	public Object getCellEditorValue() {
		return editor.getCellEditorValue();
	}

	public boolean stopCellEditing() {
		return editor.stopCellEditing();
	}

	public void cancelCellEditing() {
		editor.cancelCellEditing();
	}

	public boolean isCellEditable(EventObject anEvent) {
		selectEditor((MouseEvent) anEvent);
		return editor.isCellEditable(anEvent);
	}

	public void addCellEditorListener(CellEditorListener l) {
		editor.addCellEditorListener(l);
	}

	public void removeCellEditorListener(CellEditorListener l) {
		editor.removeCellEditorListener(l);
	}

	public boolean shouldSelectCell(EventObject anEvent) {
		selectEditor((MouseEvent) anEvent);
		return editor.shouldSelectCell(anEvent);
	}

	protected void selectEditor(MouseEvent e) {
		int row;
		if (e == null) {
			row = table.getSelectionModel().getAnchorSelectionIndex();
		} else {
			row = table.rowAtPoint(e.getPoint());
		}
		editor = (TableCellEditor) editors.get(new Integer(row));
		if (editor == null) {
			editor = defaultEditor;
		}
	}
}
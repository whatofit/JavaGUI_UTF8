//package com.java2s.table;
//
////Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
//
//import java.awt.Component;
//import java.awt.Point;
//import java.awt.Rectangle;
//import java.awt.event.MouseEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.util.EventObject;
//
//import javax.swing.DefaultCellEditor;
//import javax.swing.JComboBox;
//import javax.swing.JComponent;
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.event.CellEditorListener;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.MouseInputListener;
//import javax.swing.plaf.basic.BasicTableHeaderUI;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellEditor;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableColumn;
//import javax.swing.table.TableColumnModel;
//
///**
// * @version 1.0 08/22/99
// */
//public class EditableHeaderTableExample2 extends JFrame {
//
//	public EditableHeaderTableExample2() {
//		super("EditableHeader Example");
//
//		JTable table = new JTable(7, 5);
//		TableColumnModel columnModel = table.getColumnModel();
//		table.setTableHeader(new EditableHeader(columnModel));
//
//		String[] items = { "Dog", "Cat" };
//		JComboBox combo = new JComboBox();
//		for (int i = 0; i < items.length; i++) {
//			combo.addItem(items[i]);
//		}
//		ComboRenderer renderer = new ComboRenderer(items);
//
//		EditableHeaderTableColumn col;
//		// column 1
//		col = (EditableHeaderTableColumn) table.getColumnModel().getColumn(1);
//		col.setHeaderValue(combo.getItemAt(0));
//		col.setHeaderRenderer(renderer);
//		col.setHeaderEditor(new DefaultCellEditor(combo));
//
//		// column 3
//		col = (EditableHeaderTableColumn) table.getColumnModel().getColumn(3);
//		col.setHeaderValue(combo.getItemAt(0));
//		// col.setHeaderRenderer(renderer);
//		col.setHeaderEditor(new DefaultCellEditor(combo));
//
//		JScrollPane pane = new JScrollPane(table);
//		getContentPane().add(pane);
//	}
//
//	class ComboRenderer extends JComboBox implements TableCellRenderer {
//
//		ComboRenderer(String[] items) {
//			for (int i = 0; i < items.length; i++) {
//				addItem(items[i]);
//			}
//		}
//
//		public Component getTableCellRendererComponent(JTable table,
//				Object value, boolean isSelected, boolean hasFocus, int row,
//				int column) {
//			setSelectedItem(value);
//			return this;
//		}
//	}
//
//	public static void main(String[] args) {
//		EditableHeaderTableExample2 frame = new EditableHeaderTableExample2();
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//		frame.setSize(300, 100);
//		frame.setVisible(true);
//	}
//}
//
//class EditableHeader extends JTableHeader implements CellEditorListener {
//	public final int HEADER_ROW = -10;
//
//	transient protected int editingColumn;
//
//	transient protected TableCellEditor cellEditor;
//
//	transient protected Component editorComp;
//
//	public EditableHeader(TableColumnModel columnModel) {
//		super(columnModel);
//		setReorderingAllowed(false);
//		cellEditor = null;
//		recreateTableColumn(columnModel);
//	}
//
//	public void updateUI() {
//		setUI(new EditableHeaderUI());
//		resizeAndRepaint();
//		invalidate();
//	}
//
//	protected void recreateTableColumn(TableColumnModel columnModel) {
//		int n = columnModel.getColumnCount();
//		EditableHeaderTableColumn[] newCols = new EditableHeaderTableColumn[n];
//		TableColumn[] oldCols = new TableColumn[n];
//		for (int i = 0; i < n; i++) {
//			oldCols[i] = columnModel.getColumn(i);
//			newCols[i] = new EditableHeaderTableColumn();
//			newCols[i].copyValues(oldCols[i]);
//		}
//		for (int i = 0; i < n; i++) {
//			columnModel.removeColumn(oldCols[i]);
//		}
//		for (int i = 0; i < n; i++) {
//			columnModel.addColumn(newCols[i]);
//		}
//	}
//
//	public boolean editCellAt(int index) {
//		return editCellAt(index);
//	}
//
//	public boolean editCellAt(int index, EventObject e) {
//		if (cellEditor != null && !cellEditor.stopCellEditing()) {
//			return false;
//		}
//		if (!isCellEditable(index)) {
//			return false;
//		}
//		TableCellEditor editor = getCellEditor(index);
//
//		if (editor != null && editor.isCellEditable(e)) {
//			editorComp = prepareEditor(editor, index);
//			editorComp.setBounds(getHeaderRect(index));
//			add(editorComp);
//			editorComp.validate();
//			setCellEditor(editor);
//			setEditingColumn(index);
//			editor.addCellEditorListener(this);
//
//			return true;
//		}
//		return false;
//	}
//
//	public boolean isCellEditable(int index) {
//		if (getReorderingAllowed()) {
//			return false;
//		}
//		int columnIndex = columnModel.getColumn(index).getModelIndex();
//		EditableHeaderTableColumn col = (EditableHeaderTableColumn) columnModel
//				.getColumn(columnIndex);
//		return col.isHeaderEditable();
//	}
//
//	public TableCellEditor getCellEditor(int index) {
//		int columnIndex = columnModel.getColumn(index).getModelIndex();
//		EditableHeaderTableColumn col = (EditableHeaderTableColumn) columnModel
//				.getColumn(columnIndex);
//		return col.getHeaderEditor();
//	}
//
//	public void setCellEditor(TableCellEditor newEditor) {
//		TableCellEditor oldEditor = cellEditor;
//		cellEditor = newEditor;
//
//		// firePropertyChange
//
//		if (oldEditor != null && oldEditor instanceof TableCellEditor) {
//			((TableCellEditor) oldEditor)
//					.removeCellEditorListener((CellEditorListener) this);
//		}
//		if (newEditor != null && newEditor instanceof TableCellEditor) {
//			((TableCellEditor) newEditor)
//					.addCellEditorListener((CellEditorListener) this);
//		}
//	}
//
//	public Component prepareEditor(TableCellEditor editor, int index) {
//		Object value = columnModel.getColumn(index).getHeaderValue();
//		boolean isSelected = true;
//		int row = HEADER_ROW;
//		JTable table = getTable();
//		Component comp = editor.getTableCellEditorComponent(table, value,
//				isSelected, row, index);
//		if (comp instanceof JComponent) {
//			((JComponent) comp).setNextFocusableComponent(this);
//		}
//		return comp;
//	}
//
//	public TableCellEditor getCellEditor() {
//		return cellEditor;
//	}
//
//	public Component getEditorComponent() {
//		return editorComp;
//	}
//
//	public void setEditingColumn(int aColumn) {
//		editingColumn = aColumn;
//	}
//
//	public int getEditingColumn() {
//		return editingColumn;
//	}
//
//	public void removeEditor() {
//		TableCellEditor editor = getCellEditor();
//		if (editor != null) {
//			editor.removeCellEditorListener(this);
//
//			requestFocus();
//			remove(editorComp);
//
//			int index = getEditingColumn();
//			Rectangle cellRect = getHeaderRect(index);
//
//			setCellEditor(null);
//			setEditingColumn(-1);
//			editorComp = null;
//
//			repaint(cellRect);
//		}
//	}
//
//	public boolean isEditing() {
//		return (cellEditor == null) ? false : true;
//	}
//
//	//
//	// CellEditorListener
//	//
//	public void editingStopped(ChangeEvent e) {
//		TableCellEditor editor = getCellEditor();
//		if (editor != null) {
//			Object value = editor.getCellEditorValue();
//			int index = getEditingColumn();
//			columnModel.getColumn(index).setHeaderValue(value);
//			removeEditor();
//		}
//	}
//
//	public void editingCanceled(ChangeEvent e) {
//		removeEditor();
//	}
//
//	//
//	// public void setReorderingAllowed(boolean b) {
//	// reorderingAllowed = false;
//	// }
//
//}
//
//class EditableHeaderUI extends BasicTableHeaderUI {
//
//	protected MouseInputListener createMouseInputListener() {
//		return new MouseInputHandler((EditableHeader) header);
//	}
//
//	public class MouseInputHandler extends BasicTableHeaderUI.MouseInputHandler {
//		private Component dispatchComponent;
//
//		protected EditableHeader header;
//
//		public MouseInputHandler(EditableHeader header) {
//			this.header = header;
//		}
//
//		private void setDispatchComponent(MouseEvent e) {
//			Component editorComponent = header.getEditorComponent();
//			Point p = e.getPoint();
//			Point p2 = SwingUtilities.convertPoint(header, p, editorComponent);
//			dispatchComponent = SwingUtilities.getDeepestComponentAt(
//					editorComponent, p2.x, p2.y);
//		}
//
//		private boolean repostEvent(MouseEvent e) {
//			if (dispatchComponent == null) {
//				return false;
//			}
//			MouseEvent e2 = SwingUtilities.convertMouseEvent(header, e,
//					dispatchComponent);
//			dispatchComponent.dispatchEvent(e2);
//			return true;
//		}
//
//		public void mousePressed(MouseEvent e) {
//			if (!SwingUtilities.isLeftMouseButton(e)) {
//				return;
//			}
//			super.mousePressed(e);
//
//			if (header.getResizingColumn() == null) {
//				Point p = e.getPoint();
//				TableColumnModel columnModel = header.getColumnModel();
//				int index = columnModel.getColumnIndexAtX(p.x);
//				if (index != -1) {
//					if (header.editCellAt(index, e)) {
//						setDispatchComponent(e);
//						repostEvent(e);
//					}
//				}
//			}
//		}
//
//		public void mouseReleased(MouseEvent e) {
//			super.mouseReleased(e);
//			if (!SwingUtilities.isLeftMouseButton(e)) {
//				return;
//			}
//			repostEvent(e);
//			dispatchComponent = null;
//		}
//
//	}
//
//}
//
//class EditableHeaderTableColumn extends TableColumn {
//
//	protected TableCellEditor headerEditor;
//
//	protected boolean isHeaderEditable;
//
//	public EditableHeaderTableColumn() {
//		setHeaderEditor(createDefaultHeaderEditor());
//		isHeaderEditable = true;
//	}
//
//	public void setHeaderEditor(TableCellEditor headerEditor) {
//		this.headerEditor = headerEditor;
//	}
//
//	public TableCellEditor getHeaderEditor() {
//		return headerEditor;
//	}
//
//	public void setHeaderEditable(boolean isEditable) {
//		isHeaderEditable = isEditable;
//	}
//
//	public boolean isHeaderEditable() {
//		return isHeaderEditable;
//	}
//
//	public void copyValues(TableColumn base) {
//		modelIndex = base.getModelIndex();
//		identifier = base.getIdentifier();
//		width = base.getWidth();
//		minWidth = base.getMinWidth();
//		setPreferredWidth(base.getPreferredWidth());
//		maxWidth = base.getMaxWidth();
//		headerRenderer = base.getHeaderRenderer();
//		headerValue = base.getHeaderValue();
//		cellRenderer = base.getCellRenderer();
//		cellEditor = base.getCellEditor();
//		isResizable = base.getResizable();
//	}
//
//	protected TableCellEditor createDefaultHeaderEditor() {
//		return new DefaultCellEditor(new JTextField());
//	}
//
//}

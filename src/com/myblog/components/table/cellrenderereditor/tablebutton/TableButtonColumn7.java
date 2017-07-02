package com.myblog.components.table.cellrenderereditor.tablebutton;
//package tablebutton;
//
//import java.awt.Component;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.math.BigDecimal;
//
//import javax.swing.AbstractCellEditor;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JTable;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.TableCellEditor;
//import javax.swing.table.TableCellRenderer;
//
//class TableButtonColumn7 extends AbstractCellEditor implements
//		TableCellRenderer, TableCellEditor, ActionListener {
//	JButton renderButton;
//	JButton editButton;
//	String text;
//	String bcAcctType;
//	String bcInstrument;
//	BigDecimal bcOrderQty;
//	private int row;
//	private JTable table;
//
//	TableButtonColumn7() {
//		renderButton = new JButton();
//		editButton = new JButton();
//		editButton.setFocusPainted(false);
//		editButton.setFocusable(false);
//		editButton.addActionListener(this);
//	}
//
//	@Override
//	public Object getCellEditorValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		this.fireEditingStopped();// 立即终止此单元格的编辑状态，使表格模型可以自动更新
//		// dtm.removeRow(this.row);
//		bcAcctType = (String) table.getModel().getValueAt(row, 10);
//		bcInstrument = (String) table.getModel().getValueAt(row, 1);
//		bcOrderQty = (BigDecimal) table.getModel().getValueAt(row, 5);
//		//String acctType = (String) table.getModel().getValueAt(table.getSelectedRow(), ColumnNo.AccountType);
//	}
//
//	@Override
//	public Component getTableCellEditorComponent(JTable table, Object value,
//			boolean isSelected, int row, int column) {
//		text = (value == null) ? "SELL" : value.toString();
//		// editButton.setText(text);
//		this.row = row;
//		this.table = table;
//
//		return editButton;
//	}
//
//	@Override
//	public Component getTableCellRendererComponent(JTable table, Object value,
//			boolean isSelected, boolean hasFocus, int row, int column) {
//		if (hasFocus) {
//
//		} else if (isSelected) {
//
//		} else {
//
//		}
//
//		renderButton.setText((value == null) ? "SELL" : value.toString());
//		renderButton.setToolTipText("SELL");
//		return renderButton;
//	}
//
//}

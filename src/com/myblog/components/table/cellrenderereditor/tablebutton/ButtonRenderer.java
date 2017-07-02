package com.myblog.components.table.cellrenderereditor.tablebutton;
//package tablebutton;
//
////File: ButtonRenderer.java  
///* (swing1.1beta3) */
//import java.awt.Component;
//
//import javax.swing.JButton;
//import javax.swing.JTable;
//import javax.swing.UIManager;
//import javax.swing.table.TableCellRenderer;
//
///**
// * @version 1.0 11/09/98
// */
//public class ButtonRenderer extends JButton implements TableCellRenderer {
//	public ButtonRenderer() {
//		setOpaque(true);
//	}
//
//	public Component getTableCellRendererComponent(JTable table, Object value,
//			boolean isSelected, boolean hasFocus, int row, int column) {
//		if (isSelected) {
//			setForeground(table.getSelectionForeground());
//			setBackground(table.getSelectionBackground());
//		} else {
//			setForeground(table.getForeground());
//			setBackground(UIManager.getColor("Button.background"));
//		}
//		setText((value == null) ? "" : value.toString());
//		return this;
//	}
//}
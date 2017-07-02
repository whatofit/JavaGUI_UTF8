package com.myblog.components.table.cellrenderereditor.cellpanel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class JTableCellPanel extends JFrame {
	String[] columnNames = { "名称及规格", "零数", "件数", "数量", "单位", "件价", "单价", "金额",
			"备注" };
	JTable tableA;
	public Object[][] values = { { "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "", "" } };
	DefaultTableModel model = new DefaultTableModel(values, columnNames);

	public JTableCellPanel() {
		setBounds(100, 100, 800, 400);
		tableA = new JTable(model);
		tableA.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane(tableA);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) tableA
				.getColumnModel();
		dcm.getColumn(0).setPreferredWidth(200);

		// 调用我们刚才自己改写后的TableCellRenderer接口JTableTestRenderer
		TableColumnModel tcm = tableA.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
//		tc.setPreferredWidth(200);
		tc.setCellRenderer(new JTableCellPanelRenderer());

		// 调用我们刚才自己改写后的TableCellEditor接口JTableTestCellEditor
		tc.setCellEditor(new JTableCellPanelEditor());

		add(scrollPane);
		setVisible(true);
	}

	public static void main(String[] args) {
		new JTableCellPanel();
	}

//	public void getTable(DefaultTableModel model) {
//		tableA.setModel(model);
//		TableColumnModel tcm = tableA.getColumnModel();
//		TableColumn tc = tcm.getColumn(0);
//		tc.setCellRenderer(new JTableTestRenderer());
//		tc.setCellEditor(new JTableTestCellEdit());
//		tableA.setColumnSelectionAllowed(false);
//		tableA.setRowSelectionAllowed(false);
//		tcm.getColumn(0).setPreferredWidth(200);
//		tableA.repaint();
//	}
}
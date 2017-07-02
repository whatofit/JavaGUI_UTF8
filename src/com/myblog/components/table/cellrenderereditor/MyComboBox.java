package com.myblog.components.table.cellrenderereditor;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 * 在JTable中添加下拉
 * 
 * @author sky
 * 
 */
public class MyComboBox {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyTable mytable = new MyTable();
		DefaultTableModel model = (DefaultTableModel) mytable.getModel();
		model.addColumn("姓名", new Object[] { "向小文", "刘晓明" });
		model.addColumn("语文", new Object[] { "100", "99" });
		model.addColumn("数学", new Object[] { "98", "90" });
		model.addColumn("复选框", new Object[] { false, false });

		String[] values = new String[] { "90", "92", "93" };
		mytable.setComboCell(0, 1, new MyComboBoxEditor(values));// 第一行的第二例为下拉

		JScrollPane jp = new JScrollPane(mytable);
		jp.setViewportView(mytable);
		jp.setSize(400, 300);
		JFrame jf = new JFrame();
		jf.getContentPane().add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(400, 300);
		jf.setVisible(true);
	}
}

// ----------------表体table------------------
class MyTable extends JTable {
	int myRow = -1, myCol = -1;
	TableCellEditor myEditor;// 边框编辑器

	public void setComboCell(int row, int col, TableCellEditor editor) {
		this.myRow = row;
		this.myCol = col;
		this.myEditor = editor;
	}

	public TableCellEditor getCellEditor(int row, int col) {
		if (row == myRow && col == myCol && myEditor != null)
			return myEditor;
		return super.getCellEditor(row, col);
	}

	/**
	 * 返回数据类型
	 */
	public Class getColumnClass(int myCol) {
		return getValueAt(0, myCol).getClass();
	}
}




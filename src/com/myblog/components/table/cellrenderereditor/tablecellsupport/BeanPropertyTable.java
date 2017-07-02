package com.myblog.components.table.cellrenderereditor.tablecellsupport;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

//BeanPropertyTable继承了JTable类，它覆盖了JTable的两方法getCellRenderer和getCellEditor来提供自定义的渲染器和编辑器，并使用基于BeanProperty数组的数据模型BeanModel：
public class BeanPropertyTable extends JTable {
	private ArrayList properties;

	public BeanPropertyTable() {
		properties = new ArrayList();
	}

	public void setProperties(ArrayList properties) {
		if (properties != null) {
			this.properties = properties;
			setModel(new BeanModel());
		}
	}
	// 自定义的TableModel
	private class BeanModel extends AbstractTableModel {

		public int getRowCount() {
			// 属性表的行数
			return properties.size();
		}

		public int getColumnCount() {
			// 属性表的列数
			return 2;
		}

		public String getColumnName(int columnIndex) {
			// 属性表的列名：property, value
			return columnIndex == 0 ? "property" : "value";
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// 第二列属性值可编辑
			return columnIndex == 1;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			// 获取值，第一列用属性显示名，第二列用属性值
			BeanProperty property = (BeanProperty) properties.get(rowIndex);
			return property;
		}
	}
}


package com.myblog.components.table.cellrenderereditor.tablecellcomponent;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

//然后就是写Renderer了,我们继承MyRadioPanel并且实现TableCellRenderer接口就可以了.
public class MyRadioCellRenderer extends MyRadioPanel implements TableCellRenderer {

	// 构造函数直接使用MyRadioCellRenderer的
	public MyRadioCellRenderer(String[] strButtonTexts) {
		super(strButtonTexts);
	}

	// 然后是实现接口的getTableCellRendererComponent方法:
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof Integer) {
			setSelectedIndex(((Integer) value).intValue());
		}
		return this;
	}
}
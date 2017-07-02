package com.myblog.components.table.cellrenderereditor.tablecellsupport;

//BeanProperty是一个简单的封装类，目的是打包一个属性的描述，包括它的名称、值、渲染器和编辑器。它的代码如下：
public class BeanProperty { // 属性显示名称，属性表格第一列显示名
	private String displayName; // 属性的值，属性表格第二列的值
	private Object value; // 渲染和编辑属性值用的渲染编辑器（Support类）
	private TableCellSupport support;

	public BeanProperty(String displayName, TableCellSupport support) {
		this.displayName = displayName;
		this.support = support;
	}

	public String getDisplayName() {
		return "";
	}

	public Object getValue() {
		return "";
	}

	public void setValue(Object value) {
	}

	public TableCellSupport getSupport() {
		return null;
	}
}
package com.myblog.components.table.cellrenderereditor.tablecellsupport;

import java.awt.Color;
import java.awt.Component;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

//public abstract class TableCellSupport<T> implements TableCellEditor,
//		TableCellRenderer {
//	// 编辑器、渲染器缺省的前后背景
//	static Color BACKGROUND = UIManager.getColor("TextField.background");
//	static Color FOREGROUND = UIManager.getColor("TextField.foreground");
//	static Color SELECTION_BACKGROUND = UIManager
//			.getColor("TextField.selectionBackground");
//	static Color SELECTION_FOREGROUND = UIManager
//			.getColor("TextField.selectionForeground");
//	// 渲染器、编辑器的组件，使用同一个
//	protected T component;
//
//	// vector<T> v=new vector<T>();
//	// CellEditorListener的容器，使用WeakReference放置内存泄漏
//	private ArrayList listeners = new ArrayList();
//
//	// 构造函数
//	public TableCellSupport(T component) {
//		this.component = component;
//		// 如果是JComponent类组件，为了美观把边框去掉
//		if (component instanceof JComponent)
//			((JComponent) component).setBorder(null);
//	}
//
//	// 获取并配置编辑组件
//	public Component getTableCellEditorComponent(JTable table, Object value,
//			boolean isSelected, int row, int column) {
//		// 将value值设置给component，这儿调用了一个子类需要实现的方法setValueTo
//		setValueTo(component, value);
//		// 设置前后景、字体
//		component.setBackground(BACKGROUND);
//		component.setForeground(FOREGROUND);
//		component.setFont(table.getFont());
//		return component;
//	}
//
//	// 获取当前编辑器的值，以component中的值为准
//	public Object getCellEditorValue() {
//		// 调用了一个子类需要实现的方法getValueFrom从component获取当前正在编辑的值
//		return getValueFrom(component);
//	}
//
//	// 根据事件anEvent判断是否可编辑，直接返回true，如有特殊需求子类可以覆盖改变
//	public boolean isCellEditable(EventObject anEvent) {
//		return true;
//	}
//
//	// 根据事件anEvent判断是否可选，直接返回true，如有特殊需求子类可以覆盖改变
//	public boolean shouldSelectCell(EventObject anEvent) {
//		return true;
//	}
//
//	// 停止编辑
//	public boolean stopCellEditing() {
//		try {
//			// 调用通常子类需要覆盖的方法:checkComponentValue，该方法通过抛出异常来声明发生何中错误
//			checkComponentValue(component);
//			// 通过检查，说明有效，触发事件通知编辑停止事件
//			fireEditingStopped();
//			// 返回true标识成功
//			return true;
//		} catch (Exception e) {
//			// 说明有错，错误信息被包含在Exception的message中，显示该信息。
//			JOptionPane.showMessageDialog(component, e.getMessage(),
//					"Error Input", JOptionPane.ERROR_MESSAGE);
//			// 返回false标识失败
//			return false;
//		}
//	}
//
//	// 取消编辑
//	public void cancelCellEditing() {
//		// 通常直接发出通知即可
//		fireEditingCanceled();
//	}
//
//	// 添加CellEditorListener
//	public void addCellEditorListener(CellEditorListener l) {
//		listeners.add(new WeakReference(l));
//	}
//
//	// 删除CellEditorListener
//	public void removeCellEditorListener(CellEditorListener l) {
//		listeners.remove(new WeakReference(l));
//	}
//
//	// 获取并配置渲染组件
//	public Component getTableCellRendererComponent(JTable table, Object value,
//			boolean isSelected, boolean hasFocus, int row, int column) {
//		// 设置组件的值
//		setValueTo(component, value);
//		// 设置字体、前后背景
//		component.setFont(table.getFont());
//		if (isSelected) {
//			component.setBackground(SELECTION_BACKGROUND);
//			component.setForeground(SELECTION_FOREGROUND);
//		} else {
//			component.setBackground(BACKGROUND);
//			component.setForeground(FOREGROUND);
//		}
//		// 返回该组件
//		return component;
//	}
//
//	// 触发编辑停止操作事件，注意这儿是protected方法，允许子类调用
//	protected void fireEditingStopped() {
//		ChangeEvent e = new ChangeEvent(component);
//		for (WeakReference ref : listeners) {
//			CellEditorListener l = ref.get();
//			l.editingStopped(e);
//		}
//	}
//
//	// 触发编辑取消操作，允许子类调用
//	protected void fireEditingCanceled() {
//		ChangeEvent e = new ChangeEvent(component);
//		for (WeakReference ref : listeners) {
//			CellEditorListener l = ref.get();
//			l.editingCanceled(e);
//		}
//	}
//
//	// 检查编辑器组件component内的值是否有效，对于希望检查有效性的需要覆盖此方法
//	protected void checkComponentValue(T component) throws Exception {
//	}
//
//	// 将value设置到编辑组件component内，子类必须实现的抽像方法
//	protected abstract void setValueTo(T component, Object value);
//
//	// 从编辑组件component内获取正在编辑的值，子类必须实现的抽象方法
//	protected abstract Object getValueFrom(T component);
//}

public abstract class TableCellSupport implements TableCellEditor,
		TableCellRenderer {
	// 编辑器、渲染器缺省的前后背景
	static Color BACKGROUND = UIManager.getColor("TextField.background");
	static Color FOREGROUND = UIManager.getColor("TextField.foreground");
	static Color SELECTION_BACKGROUND = UIManager
			.getColor("TextField.selectionBackground");
	static Color SELECTION_FOREGROUND = UIManager
			.getColor("TextField.selectionForeground");
	// 渲染器、编辑器的组件，使用同一个
	protected JComponent component;

	// vector<T> v=new vector<T>();
	// CellEditorListener的容器，使用WeakReference放置内存泄漏
	private ArrayList listeners = new ArrayList();

	// 构造函数
	public TableCellSupport(JComponent component) {
		this.component = component;
		// 如果是JComponent类组件，为了美观把边框去掉
		if (component instanceof JComponent)
			((JComponent) component).setBorder(null);
	}

	// 获取并配置编辑组件
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// 将value值设置给component，这儿调用了一个子类需要实现的方法setValueTo
		setValueTo(component, value);
		// 设置前后景、字体
		component.setBackground(BACKGROUND);
		component.setForeground(FOREGROUND);
		component.setFont(table.getFont());
		return component;
	}

	// 获取当前编辑器的值，以component中的值为准
	public Object getCellEditorValue() {
		// 调用了一个子类需要实现的方法getValueFrom从component获取当前正在编辑的值
		return getValueFrom(component);
	}

	// 根据事件anEvent判断是否可编辑，直接返回true，如有特殊需求子类可以覆盖改变
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	// 根据事件anEvent判断是否可选，直接返回true，如有特殊需求子类可以覆盖改变
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	// 停止编辑
	public boolean stopCellEditing() {
		try {
			// 调用通常子类需要覆盖的方法:checkComponentValue，该方法通过抛出异常来声明发生何中错误
			checkComponentValue(component);
			// 通过检查，说明有效，触发事件通知编辑停止事件
			fireEditingStopped();
			// 返回true标识成功
			return true;
		} catch (Exception e) {
			// 说明有错，错误信息被包含在Exception的message中，显示该信息。
			JOptionPane.showMessageDialog(component, e.getMessage(),
					"Error Input", JOptionPane.ERROR_MESSAGE);
			// 返回false标识失败
			return false;
		}
	}

	// 取消编辑
	public void cancelCellEditing() {
		// 通常直接发出通知即可
		fireEditingCanceled();
	}

	// 添加CellEditorListener
	public void addCellEditorListener(CellEditorListener l) {
		listeners.add(new WeakReference(l));
	}

	// 删除CellEditorListener
	public void removeCellEditorListener(CellEditorListener l) {
		listeners.remove(new WeakReference(l));
	}

	// 获取并配置渲染组件
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// 设置组件的值
		setValueTo(component, value);
		// 设置字体、前后背景
		component.setFont(table.getFont());
		if (isSelected) {
			component.setBackground(SELECTION_BACKGROUND);
			component.setForeground(SELECTION_FOREGROUND);
		} else {
			component.setBackground(BACKGROUND);
			component.setForeground(FOREGROUND);
		}
		// 返回该组件
		return component;
	}

	// 触发编辑停止操作事件，注意这儿是protected方法，允许子类调用
	protected void fireEditingStopped() {
		ChangeEvent e = new ChangeEvent(component);
//		for (WeakReference ref : listeners) {
//			CellEditorListener l = ref.get();
//			l.editingStopped(e);
//		}
	}

	// 触发编辑取消操作，允许子类调用
	protected void fireEditingCanceled() {
		ChangeEvent e = new ChangeEvent(component);
//		for (WeakReference ref : listeners) {
//			CellEditorListener l = (CellEditorListener) ref.get();
//			l.editingCanceled(e);
//		}
	}

	// 检查编辑器组件component内的值是否有效，对于希望检查有效性的需要覆盖此方法
	protected void checkComponentValue(JComponent component) throws Exception {
	}

	// 将value设置到编辑组件component内，子类必须实现的抽像方法
	protected abstract void setValueTo(JComponent component, Object value);

	// 从编辑组件component内获取正在编辑的值，子类必须实现的抽象方法
	protected abstract Object getValueFrom(JComponent component);
}

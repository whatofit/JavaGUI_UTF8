package com.myblog.components.table.cellrenderereditor.tablecellsupport.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import com.myblog.components.table.cellrenderereditor.tablecellsupport.TableCellSupport;


public class CheckBoxCell extends TableCellSupport {
	public CheckBoxCell(JCheckBox cb) {
		super(cb);
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 被选中时需要触发编辑停止事件，一般直接调用父类的stopCellEditing即可，那儿已经负责了有效性检查，事件触发。
				stopCellEditing();
			}
		});
	}

	protected void setValueTo(JCheckBox component, Object value) {
		// 认为value值是Boolean类型的，注意空值的处理
		component.setSelected(value == null ? false : ((Boolean) value)
				.booleanValue());
	}

	protected Object getValueFrom(JCheckBox component) {
		// 返回当前选中状态的布尔值，用Boolean封装
		return new Boolean(component.isSelected());
	}

	@Override
	protected void setValueTo(JComponent component, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object getValueFrom(JComponent component) {
		// TODO Auto-generated method stub
		return null;
	}

}
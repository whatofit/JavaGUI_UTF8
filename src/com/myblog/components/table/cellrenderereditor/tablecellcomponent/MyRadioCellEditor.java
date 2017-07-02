package com.myblog.components.table.cellrenderereditor.tablecellcomponent;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

//最后就是Editor了,

/**
 * create cell editor that radio in it.
*/
public class MyRadioCellEditor extends DefaultCellEditor implements ItemListener {
	MyRadioPanel panel;
	//在它的构造函数里我们为JRadioButton添加监听:
	public MyRadioCellEditor() {
		super(new JTextField());
	}
	
	public MyRadioCellEditor(MyRadioPanel panel) {
		//todo
		super(new JTextField());
		this.panel = panel;
		JRadioButton[] buttons = panel.getButtons();
    	for (int i = 0; i < buttons.length;i++) {
    		buttons[i].addItemListener(this);
    	}
	}
	
	//在监听处理中我们停止编辑,
    @Override
    public void itemStateChanged(ItemEvent e) {
       super.fireEditingStopped();
       System.out.println("itemStateChanged");
    }

    //然后我们需要覆盖DefaultCellEditor的getTableCellEditorComponent,返回我们需要显示的MyRadioPanel.
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
           boolean isSelected, int row, int column) {
       if (value instanceof Integer) {
           panel.setSelectedIndex(((Integer) value).intValue());
       }
       return panel;
    }

	//最后我们重写getCellEditorValue,返回编辑完成后我们显示的值：

    @Override
    public Object getCellEditorValue() {
       return new Integer(panel.getSelectedIndex());
    }
}
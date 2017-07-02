package com.myblog.components.table.cellrenderereditor.tablecellcomponent;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class MyButtonRenderer extends JButton implements TableCellRenderer {

//    public MyButtonRenderer() {  
//        setOpaque(true);  
//    }
	//实现接口的方法:
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		//然后设置属性：
        if (isSelected) {  
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());  
        } else {  
            setForeground(table.getForeground());  
            setBackground(UIManager.getColor("Button.background"));  
        }
		
		setText((value == null) ? "" : value.toString());
		//使用也很简单,假如我们希望第一列是JButton,则
		return this;
	}
}


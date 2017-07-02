package com.myblog.components.table.cellrenderereditor.tablecellcomponent;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyButtonCellEditor extends DefaultCellEditor {

	//定义两个属性,分别代表编辑状态下显示的组件和显示的值.
    //editor show
    private JButton button = null;
    //text
    private String label = null;

	public MyButtonCellEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fireEditingStopped();
				ButtonClick(e);
			}
		});
		
	}
	
	public MyButtonCellEditor() {
		// DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
		super(new JTextField());
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fireEditingStopped();
				ButtonClick(e);  
			}
		});

	}
    //然后重写getTableCellEditorComponent方法,在编辑状态表示我们自己的组件.
    /**
     * Sets an initial <code>value</code> for the editor.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	//设置组件样式:
        if (isSelected) {  
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());  
        } else {  
            button.setForeground(table.getForeground());  
            button.setBackground(table.getBackground());  
        }  
    	//label = (value == null) ? "" : value.toString();
    	//button.setText(label);
    	button.setText(table.getValueAt(row, column).toString());
    	return button;
    }
    
    //然后还需要重写getCellEditorValue方法,返回编辑完成后的值,
    //若缺少此方法，点击按钮后将获取不到Button的Text值，将会显示false.
    @Override
    public Object getCellEditorValue() {
    	// return new String(label);
        return button.getText();
    }
      
    protected void ButtonClick(ActionEvent e) {
    	System.out.println("test");
    	if(e.getSource().equals(button)) {
		}
//        System.out.println(table.getSelectedColumn() + " and  "  
//                + table.getSelectedRow());  
    } 
}

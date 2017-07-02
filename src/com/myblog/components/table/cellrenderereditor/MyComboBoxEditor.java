package com.myblog.components.table.cellrenderereditor;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 * @author duchao
 * 
 */
//-----------------------ÏÂÀ­±à¼­Æ÷-----------------------
class MyComboBoxEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 1L;

	public MyComboBoxEditor(JCheckBox checkBox) {
		super(checkBox);
	}

	public MyComboBoxEditor(String[] items) {
		super(new JComboBox(items));
	}
}
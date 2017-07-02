package com.myblog.components.table.cellrenderereditor.tablecellsupport.impl;

import javax.swing.JComponent;
import javax.swing.JSpinner;

import com.myblog.components.table.cellrenderereditor.tablecellsupport.TableCellSupport;


public class SpinnerCell extends TableCellSupport {
	public SpinnerCell(JComponent component) {
		super(component);
	}

	protected void checkComponentValue(JSpinner component) throws Exception {
		Integer i = (Integer) component.getValue();
		if (i.intValue() < 0)
			throw new Exception("Cannot be negative!");
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
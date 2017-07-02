package com.myblog.components.table.cellrenderereditor.colorRenderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class ColorRenderer extends BasicComboBoxRenderer {
	private JComboBox combo;

	public ColorRenderer(JComboBox cb) {
		combo = cb;
		setFont(combo.getFont());
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		JLabel lbl = (JLabel) this;
		if (value != null) {
			Color c = (Color) value;
			lbl.setText(ColorIcon.getColorName(c));
			lbl.setIcon(new ColorIcon(c));
		}
		return this;
	}
}
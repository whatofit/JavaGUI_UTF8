package com.myblog.components.table.cellrenderereditor.cellpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class WordTableCellRenderer extends JPanel implements TableCellRenderer {
	JButton edit_btn;
	JButton del_btn;
	//JTextField edit_txf;

	public WordTableCellRenderer() {
		super();
		setLayout(new BorderLayout());
		edit_btn = new JButton("edit");
		del_btn = new JButton("del");
//		edit_txf = new JTextField();
//		add(edit_txf);
		add(edit_btn, BorderLayout.EAST);
		add(del_btn, BorderLayout.WEST);
		edit_btn.setBackground(Color.white);
		del_btn.setBackground(Color.white);
		edit_btn.setPreferredSize(new Dimension(60, getHeight()));
		del_btn.setPreferredSize(new Dimension(60, getHeight()));
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getForeground());
			super.setBackground(table.getBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		//if (value != null)
		//	edit_txf.setText(value.toString());
		return this;
	}
}
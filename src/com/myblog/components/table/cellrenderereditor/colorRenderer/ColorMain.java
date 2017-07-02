package com.myblog.components.table.cellrenderereditor.colorRenderer;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorMain {

	/**
	 * @param args
	 */
	public static void main(String[] fsd) {
		JFrame d = new JFrame();

		JComboBox colorCombo = new JComboBox();
		colorCombo.setRenderer(new ColorRenderer(colorCombo));
		colorCombo.addItem(Color.red);
		colorCombo.addItem(Color.orange);

		//JScrollPane pane = new JScrollPane(colorCombo);
		//d.getContentPane().add(pane, BorderLayout.CENTER);
		JPanel panel = new JPanel(); 
		panel.add(colorCombo);
		d.getContentPane().add(panel, BorderLayout.CENTER);
		d.setBounds(0, 0, 400, 400);
		d.setVisible(true);
	}

}

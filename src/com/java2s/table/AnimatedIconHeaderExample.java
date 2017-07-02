package com.java2s.table;

//Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html

/* (swing1.1.1beta2) */

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

/**
 * @version 1.0 06/19/99
 */
public class AnimatedIconHeaderExample extends JFrame {

	public AnimatedIconHeaderExample() {
		super("AnimatedIconHeader Example");

		final Object[][] data = new Object[][] { { "Leopard", "Lycaon" },
				{ "Jagur", "Jackal" }, { "Cheetah", "Coyote" },
				{ "Puma", "Dingo" }, { "Lynx", "Fox" }, { "Tom", "Hot" } };
		final String[] column = new String[] { "Cat", "Dog" };

		ImageIcon[] icons = { new ImageIcon("Java2sAnimation.gif"),
				new ImageIcon("Java2sAnimation.gif") };

		AbstractTableModel model = new AbstractTableModel() {
			public int getColumnCount() {
				return column.length;
			}

			public int getRowCount() {
				return data.length;
			}

			public String getColumnName(int col) {
				return column[col];
			}

			public Object getValueAt(int row, int col) {
				return data[row][col];
			}
		};

		JTable table = new JTable(model);
		JTableHeader header = table.getTableHeader();
		JLabel renderer;
		for (int i = 0; i < model.getColumnCount(); i++) {
			renderer = (JLabel) table.getColumn(column[i]).getHeaderRenderer();
			renderer.setIcon(icons[i]);

			// If you have only one column.
			// icons[i].setImageObserver(header);

			icons[i].setImageObserver(new HeaderImageObserver(header, i));
		}
		JScrollPane pane = new JScrollPane(table);
		getContentPane().add(pane);
	}

	class HeaderImageObserver implements ImageObserver {
		JTableHeader header;

		int col;

		HeaderImageObserver(JTableHeader header, int col) {
			this.header = header;
			this.col = col;
		}

		public boolean imageUpdate(Image img, int flags, int x, int y, int w,
				int h) {
			if ((flags & (FRAMEBITS | ALLBITS)) != 0) {
				Rectangle rect = header.getHeaderRect(col);
				header.repaint(rect);
			}
			return (flags & (ALLBITS | ABORT)) == 0;
		}
	}

	public static void main(String[] args) {
		AnimatedIconHeaderExample frame = new AnimatedIconHeaderExample();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setSize(300, 140);
		frame.setVisible(true);
	}
}

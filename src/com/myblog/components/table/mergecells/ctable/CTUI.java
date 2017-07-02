package com.myblog.components.table.mergecells.ctable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.TableCellRenderer;

public class CTUI extends BasicTableUI {
	public void paint(Graphics g, JComponent c) {
//		Rectangle r = g.getClipBounds();
//		int firstRow = table.rowAtPoint(new Point(0, r.y));
//		int lastRow = table.rowAtPoint(new Point(0, r.y + r.height));
//		// -1 is a flag that the ending point is outside the table
//		if (lastRow < 0)
//			lastRow = table.getRowCount() - 1;
//		for (int i = firstRow; i <= lastRow; i++)
//			paintRow(i, g);

		Rectangle r = g.getClipBounds();
		int firstCol = table.columnAtPoint(new Point(r.x, 0));
		int lastCol = table.columnAtPoint(new Point(r.x + r.width, 0));
		// -1 is a flag that the ending point is outside the table
		if (lastCol < 0)
			lastCol = table.getColumnCount() - 1;
		for (int i = firstCol; i <= lastCol; i++)
			paintCol(i, g);
	}

//	private void paintRow(int row, Graphics g) {
//		Rectangle r = g.getClipBounds();
//		for (int i = 0; i < table.getColumnCount(); i++) {
//			Rectangle r1 = table.getCellRect(row, i, true);
//			if (r1.intersects(r)) // at least a part is visible
//			{
//				 int sk = ((CTable) table).map.visibleCell(row, i);
//				 paintCell(row, sk, g, r1);
//				 // increment the column counter
//				 i += ((CTable) table).map.span(row, sk) - 1;
//			}
//		}
//	}

	private void paintCol(int col, Graphics g) {
		Rectangle r = g.getClipBounds();
		for (int i = 0; i < table.getRowCount(); i++) {
			Rectangle r1 = table.getCellRect(i, col, true);
			if (r1.intersects(r)) // at least a part is visible
			{
				int sk = ((CTable) table).map.visibleCell(i, col);
				paintCell(sk, col, g, r1);
				// increment the column counter
				i += ((CTable) table).map.span(sk, col) - 1;
			}
		}
	}

	private void paintCell(int row, int column, Graphics g, Rectangle area) {
		int verticalMargin = table.getRowMargin();
		int horizontalMargin = table.getColumnModel().getColumnMargin();
		Color c = g.getColor();
		g.setColor(table.getGridColor());
		g.drawRect(area.x, area.y, area.width - 1, area.height - 1);
		g.setColor(c);
		area.setBounds(area.x + horizontalMargin / 2, area.y + verticalMargin
				/ 2, area.width - horizontalMargin, area.height
				- verticalMargin);
		if (table.isEditing() && table.getEditingRow() == row
				&& table.getEditingColumn() == column) {
			Component component = table.getEditorComponent();
			component.setBounds(area);
			component.validate();
		} else {
			TableCellRenderer renderer = table.getCellRenderer(row, column);
			Component component = table.prepareRenderer(renderer, row, column);
			if (component.getParent() == null)
				rendererPane.add(component);
			rendererPane.paintComponent(g, component, table, area.x, area.y,
					area.width, area.height, true);
		}
	}
}
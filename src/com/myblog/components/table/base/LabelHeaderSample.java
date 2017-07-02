package com.myblog.components.table.base;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

class JComponentTableCellRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return (JComponent) value;
	}
}

public class LabelHeaderSample {

	public static void main(String args[]) {
		final Object rows[][] = { { "one", "1" }, { "two", "2" },
				{ "three", "3" } };
		JFrame frame = new JFrame("Label Header");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String headers[] = { "English", "test" };
		JTable table = new JTable(rows, headers);
		JScrollPane scrollPane = new JScrollPane(table);

		// 创建需要增加的图片对象
		Icon blueIcon = new ImageIcon("./resoure/jam__jelly.jpg");
		Icon redIcon = new ImageIcon("。/resoure/jam__jelly.jpg");

		Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");

		JLabel blueLabel = new JLabel(headers[0], blueIcon, JLabel.CENTER);
		blueLabel.setBorder(headerBorder);
		JLabel redLabel = new JLabel(headers[1], redIcon, JLabel.CENTER);
		redLabel.setBorder(headerBorder);

		// 获得表单元个渲染器
		TableCellRenderer renderer = new JComponentTableCellRenderer();

		// 获得表列模式
		TableColumnModel columnModel = table.getColumnModel();

		// 获得需要增加图片的列对象
		TableColumn column0 = columnModel.getColumn(0);
		TableColumn column1 = columnModel.getColumn(1);

		// 将用于绘制 TableColumn 的头的 TableCellRenderer 设置为 renderer。
		column0.setHeaderRenderer(renderer);

		// 设置 Object，将使用其字符串表示形式作为 headerRenderer 的值。
		column0.setHeaderValue(blueLabel);

		column1.setHeaderRenderer(renderer);
		column1.setHeaderValue(redLabel);

		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(300, 150);
		frame.setVisible(true);
	}
}
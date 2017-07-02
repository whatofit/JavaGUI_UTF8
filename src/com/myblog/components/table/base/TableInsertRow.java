package com.myblog.components.table.base;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableInsertRow extends JFrame {

	class TableTableModel extends DefaultTableModel {
		/**
   * 
   */
		private static final long serialVersionUID = 679265889547674796L;
		public final String[] COLUMN_NAMES = new String[] { "列0", "列1", "列2",
				"列3" };

		public TableTableModel() {
		}

		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		public String getColumnName(int columnIndex) {
			return COLUMN_NAMES[columnIndex];
		}

		// 将Table设成只读的
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	private JTable table;
	private int i = 0;
	private int j = 0;
	private int rowI = -1;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TableInsertRow frame = new TableInsertRow();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public TableInsertRow() {
		super();
		setTitle("JTable Test");
		getContentPane().setLayout(null);
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 460, 271);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setShowGrid(true);
		table.addMouseListener(new UserMouseAdapter() {
			/** */
			/**
			 * 鼠标单击事件
			 * 
			 * @param e
			 *            事件源参数
			 */
			public void mouseSingleClicked(MouseEvent e) {
				// System.out.println("Single Clicked!");
				rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
				if (rowI > -1)
					System.out.println("单击鼠标 "
							+ ((TableTableModel) table.getModel()).getValueAt(
									rowI, 0));
			}

			/** */
			/**
			 * 鼠标双击事件
			 * 
			 * @param e
			 *            事件源参数
			 */
			public void mouseDoubleClicked(MouseEvent e) {
				// System.out.println("Doublc Clicked!");
				rowI = table.rowAtPoint(e.getPoint());// 得到table的行号
				if (rowI > -1)
					System.out.println("双击鼠标 "
							+ ((TableTableModel) table.getModel()).getValueAt(
									rowI, 0));
			}

		});
		table.setModel(new TableTableModel());
		scrollPane.setViewportView(table);

		final JButton button = new JButton();
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				((TableTableModel) table.getModel()).addRow(new String[] {
						"行" + (i++), "含一", "行2", "行3" });
			}
		});
		button.setText("添加一行");
		button.setBounds(10, 308, 99, 23);
		getContentPane().add(button);

		final JButton button_1 = new JButton();
		button_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				((TableTableModel) table.getModel()).insertRow(
						(rowI >= 0 ? rowI : 0), new String[] { "插入行" + (j++),
								"插入行1", "插入行2", "插入行3" });
			}
		});
		button_1.setText("插入行");
		button_1.setBounds(121, 308, 99, 23);
		getContentPane().add(button_1);

	}

}

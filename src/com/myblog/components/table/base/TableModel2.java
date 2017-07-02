package com.myblog.components.table.base;
//package table;
//
//import java.awt.Dimension;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.TableModel;
//
//public class TableModel2 extends JPanel implements Runnable {
//
//	TableModel dataModel;
//	JScrollPane scrollpane;
//	JTable table;
//
//	public TableModel2() {
//		dataModel = getTableModel();
//		table = new JTable(dataModel);
//		scrollpane = new JScrollPane(table);
//		this.add(scrollpane);
//	}
//
//	// 读取外部文件，每一行当做一条字符串存入List中
//	public List<String> getData() {
//		FileReader fr;
//		File file = new File("E:/my.txt");
//		int b;
//		StringBuffer sb = new StringBuffer();
//		List<String> s = new ArrayList<String>();
//		try {
//			fr = new FileReader(file);
//			while ((b = fr.read()) != -1) {
//				if (b != '\r') {
//					sb.append((char) b);
//				}
//				if (b == '\n') {
//					s.add(sb.toString());
//					sb = new StringBuffer();
//				}
//			}
//			fr.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return s;
//	}
//
//	// 使用public List<String> getData() 方法得到的List构建数据模型
//	// 此处使用的外部文件中，每一行的字符串用空格分成四个部分
//	// 例如，其中一行为：2013-03-18 11:50:55 传感器1 报警，对应表格的一行
//	public AbstractTableModel getTableModel() {
//		return new AbstractTableModel() {
//			public int getColumnCount() {
//				return 4;
//			}
//
//			public int getRowCount() {
//				return getData().size();
//			}
//
//			public Object getValueAt(int row, int col) {
//				switch (col) {
//				case (0): {
//					return row + 1;
//				}
//				case (1): {
//					return getData().get(row).split(" ", 0)[0];
//				}
//				case (2): {
//					return getData().get(row).split(" ", 0)[1];
//				}
//				default:
//					return getData().get(row).split(" ", 0)[2];
//				}
//			}
//		};
//	}
//
//	public void run() {
//		while (true) {
//			// 每隔1秒钟更新JTable
//			table.validate();
//			table.updateUI();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static void main(String[] g) {
//		JFrame frame = new JFrame();
//		TableModel2 t = new TableModel2();
//		frame.add(t);
//		frame.setSize(new Dimension(400, 400));
//		frame.setVisible(true);
//		new Thread(t).start();
//	}
//}
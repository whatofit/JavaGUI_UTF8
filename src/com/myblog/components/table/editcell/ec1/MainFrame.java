package com.myblog.components.table.editcell.ec1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 Created with IntelliJ IDEA.
 * 
 * User: Edison
 * 
 * Date: 13-7-14
 * 
 * Time: 下午11:55
 * 
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame {
	private final int width = 800;
	private final int height = 600;
	private JFrame mFrame;
	private JTable movTable;
//	private MyTable movTable;

	public MainFrame() {
		mFrame = new JFrame();
		mFrame.setLayout(new BorderLayout(0, 0));
		mFrame.setSize(new Dimension(width, height));
		mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		initUI();

		mFrame.setVisible(true);
	}

	private void initUI() {
		TableModelMovie modelMovie = new TableModelMovie();
		movTable = new JTable(modelMovie);
//      // 1.二维数组初始化  
//      String[] columnHeader = { "编号", "姓名", "年龄", "地址" };  
//      String[][] data = new String[][] { { "user1", "小明", "26", "福州鼓楼" },  
//              { "user2", "小明2", "28", "福州鼓楼2" },  
//              { "user3", "小明3", "33", "福州鼓楼" },  
//              { "user4", "小明4", "26", "福州鼓楼" } };  
//		movTable = new MyTable(columnHeader,data);
		movTable.setRowHeight(20);
		movTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		JScrollPane scrollPane = new JScrollPane(movTable);
		mFrame.add(scrollPane, BorderLayout.CENTER);

		JPanel footPanel = new JPanel();
		JButton newBtn = new JButton("New");
		newBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				if (movTable.isEditing()) {
					//
					movTable.getCellEditor().stopCellEditing();
					//
				}
			}
		});

		footPanel.add(newBtn);
		mFrame.add(footPanel, BorderLayout.SOUTH);
	}

	public void show() {

	}

	public static void main(String[] args) {
		new MainFrame();

	}
}
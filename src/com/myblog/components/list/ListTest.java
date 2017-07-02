package com.myblog.components.list;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListTest extends JFrame implements ListSelectionListener {
	JList list1, list2;
	String news[] = { "人民日报", "新民晚报", "浙江日报", "文汇报" };
	String sports[] = { "足球", "排球", "乒乓球", "篮球" };
	JTextArea text;

	ListTest(String s) {
		super(s);
		Container con = getContentPane();
		con.setBackground(Color.BLUE);
		con.setLayout(new GridLayout(2, 2));
		con.setSize(200, 500);
		list1 = new JList(news);
		list1.setVisibleRowCount(3);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.addListSelectionListener(this);
		list2 = new JList(sports);
		list2.setVisibleRowCount(2);
		list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list2.addListSelectionListener(this);
		con.add(list1);
		con.add(list2);
		text = new JTextArea(10, 20);
		con.add(text);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListTest myWin = new ListTest("列表示例"); 
	}

	@Override
	public void valueChanged(ListSelectionEvent e) { // 每当选择值发生更改时调用
		if (e.getSource() == list1) {
			text.setText(null);
			Object listValue = ((JList) e.getSource()).getSelectedValue();
			String seleName = listValue.toString();
			for (int i = 0; i < news.length; i++)
				if (news[i].equals(seleName)) {
					text.append(seleName + "被选中\n");
				}
		} else if (e.getSource() == list2) {
			text.setText(null);
			int tempList[] = list2.getSelectedIndices();
			for (int i = 0; i < tempList.length; i++)
				text.append(sports[tempList[i]] + "被选中\n");
		}
	}

}

package com.myblog.components.table.base;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JTableForm extends JFrame {
	/** 数据体集合 */
	public static Vector rowVector = new Vector();
	/** 表头集合 */
	public static Vector headVector = new Vector();

	JTextField jtxtName;
	JTextField jtxtAge;
	JTextField jtxtSource;

	JTable jtable;

	public JTableForm() {
		Container con = this.getContentPane();
		con.setLayout(null);

		JLabel jl = new JLabel("姓名");
		jl.setBounds(50, 50, 60, 20);
		con.add(jl);

		jtxtName = new JTextField();
		jtxtName.setBounds(120, 50, 100, 20);
		con.add(jtxtName);

		JLabel jl1 = new JLabel("年龄");
		jl1.setBounds(50, 80, 60, 20);
		con.add(jl1);

		jtxtAge = new JTextField();
		jtxtAge.setBounds(120, 80, 100, 20);
		con.add(jtxtAge);

		JLabel jl2 = new JLabel("成绩");
		jl2.setBounds(50, 110, 60, 20);
		con.add(jl2);

		jtxtSource = new JTextField();
		jtxtSource.setBounds(120, 110, 100, 20);
		con.add(jtxtSource);

		JButton jbAdd = new JButton("添加");
		jbAdd.setBounds(50, 140, 60, 20);
		con.add(jbAdd);
		jbAdd.addActionListener(new MyEvent(this));

		JButton jbDel = new JButton("删除");
		jbDel.setBounds(120, 140, 60, 20);
		con.add(jbDel);
		jbDel.addActionListener(new MyEvent(this));

		JButton jbModify = new JButton("修改");
		jbModify.setBounds(190, 140, 60, 20);
		con.add(jbModify);
		jbModify.addActionListener(new MyEvent(this));

		JButton jbSelect = new JButton("查询");
		jbSelect.setBounds(260, 140, 60, 20);
		con.add(jbSelect);
		jbSelect.addActionListener(new MyEvent(this));

		/** 构建表头 */
		headVector.add("姓名");
		headVector.add("年龄");
		headVector.add("成绩");

		/** 构建数据体 */
		Vector temp = new Vector();
		temp.add("张三");
		temp.add("20");
		temp.add("90");
		rowVector.add(temp);

		temp = new Vector();
		temp.add("李四");
		temp.add("30");
		temp.add("50");
		rowVector.add(temp);

		temp = new Vector();
		temp.add("王五");
		temp.add("25");
		temp.add("87");
		rowVector.add(temp);

		jtable = new JTable(rowVector, headVector);

		JScrollPane jsp = new JScrollPane(jtable);
		jsp.setBounds(50, 180, 200, 250);
		con.add(jsp);

		this.setDefaultCloseOperation(3);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new JTableForm();
	}

}

class MyEvent implements ActionListener {
	JTableForm frame;

	public MyEvent(JTableForm frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();// 获得命令字

		if ("添加".equals(command)) {
			this.add();
		} else if ("删除".equals(command)) {
			this.del();
		} else if ("修改".equals(command)) {
			this.modify();
		} else if ("查询".equals(command)) {
			this.select();
		}

	}

	private void select() {
		// 符合数据的数据体
		Vector rowAll = new Vector();

		for (int i = 0; i < JTableForm.rowVector.size(); i++) {
			// 从集合中提取元素
			Object obj = JTableForm.rowVector.get(i);
			// 类型强转
			Vector temp = (Vector) obj;

			// 从数据体中获得数据
			int source = Integer.parseInt(temp.get(2).toString());

			if (source >= 60) {
				rowAll.add(temp);
			}
		}

		DefaultTableModel model = new DefaultTableModel(rowAll,
				JTableForm.headVector);
		// 刷新表格
		frame.jtable.setModel(model);
	}

	private void modify() {
		Vector vv = this.getVector();
		int index = frame.jtable.getSelectedRow();

		JTableForm.rowVector.set(index, vv);

		// 创建表格模型
		DefaultTableModel model = new DefaultTableModel(JTableForm.rowVector,
				JTableForm.headVector);
		// 刷新表格
		frame.jtable.setModel(model);

	}

	private void del() {
		// 获得当前选择行
		int index = frame.jtable.getSelectedRow();
		// 移除数据
		JTableForm.rowVector.remove(index);

		// 创建表格模型
		DefaultTableModel model = new DefaultTableModel(JTableForm.rowVector,
				JTableForm.headVector);
		// 刷新表格
		frame.jtable.setModel(model);
	}

	private void add() {
		Vector vv = this.getVector();

		JTableForm.rowVector.add(vv);// 将界面上的值加入数据体

		DefaultTableModel model = new DefaultTableModel(JTableForm.rowVector,
				JTableForm.headVector);

		frame.jtable.setModel(model);
	}

	/**
	 * 根据界面上的值，构建Vector对象
	 * 
	 * @return
	 */
	private Vector getVector() {
		Vector vec = new Vector();
		vec.add(frame.jtxtName.getText());
		vec.add(frame.jtxtAge.getText());
		vec.add(frame.jtxtSource.getText());

		return vec;
	}
}
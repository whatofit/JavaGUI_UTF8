package com.myblog.components.table.cellrenderereditor.tablecellsupport;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.UIManager;

import com.myblog.components.table.cellrenderereditor.tablecellsupport.impl.SpinnerCell;


//最后看看主类PropertyDemo.java的实现，非常简单，非常直接：
public class PropertyDemo extends JFrame {
	public PropertyDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BeanPropertyTable table = new BeanPropertyTable();
		ArrayList props = new ArrayList();
		for (int i = 0; i < 2; i++) {
			// 添加一个textfield属性，其渲染编辑组件是JTextField
			// props.add(new BeanProperty("textfield"+i,new TextFieldCell(new
			// JTextField())));
			// 添加一个combo属性，其渲染编辑组件是JComboBox
			JComboBox cb = new JComboBox();
			cb.addItem("true");
			cb.addItem("false");
			// props.add(new BeanProperty("combobox"+i,new ComboBoxCell(cb)));
			// 添加一个checkbox属性，其渲染编辑组件是 JCheckBox
			// props.add(new BeanProperty("checkbox"+i,new CheckBoxCell(new
			// JcheckBox())));
			// 添加一个spinner属性，其渲染编辑组件是 JSpinner
			props.add(new BeanProperty("spinner" + i, new SpinnerCell(new JSpinner())));
		}
		// 设置这些属性数组到属性表
		table.setProperties(props);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public static void main(String args[]) {
		try { // 设置外观，这儿设置成系统的，也可以设置成其他的外观
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() { // 显示属性表的窗口
				PropertyDemo demo = new PropertyDemo();
				demo.setSize(200, 300);
				demo.setVisible(true);
			}
		});
	}
}
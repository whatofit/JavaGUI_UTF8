package com.myblog.components.table.cellrenderereditor.tablecellcomponent;

//对于JTable单元格的渲染主要是通过两个接口interface来实现的,一个是TableCellRenderer另一个是TableCellEditor,
//JTable默认用的类class是DefaultCellRenderer和DefaultCellEditor,
//这两个都是在类似JTextField的一个JComponent的基础上来实现的,
//如果我们需要在JTable的单元格内放置特殊的控件或者绘制出特殊的效果,
//就要实现TableCellRenderer和TableCellEditor接口,在其上绘制出自己需要的样式,
//再通过JTable的setCellRenderer和setCellEditor方法设置新的外观呈现.

//首先我们先看看TableCellRenderer和TableCellEditor接口的区别, 
//TableCellRenderer接口就是用来绘制和展示当前单元格的内容的,可以用文字、图片、组件、甚至Java2D来绘制效果; 
//TableCellEditor主要是用来当用户点击具体的某个单元格进行编辑的时候来展现的,除了绘制之外,在点击时还会有更加复杂的效果出现.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TableCellComponent3 extends JFrame {
	// private DefaultTableModel tableModel;
	private WordTableModel tableModel;
	private JTable table;

	public TableCellComponent3() {
		setLayout(new BorderLayout());

		setBounds(10, 10, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		this.setBackground(Color.white);
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		panel.add(scrollPane);

		tableModel = new WordTableModel();
		table = new JTable(tableModel);
		table.setRowHeight(30);
		// table.setBackground(Color.red);
		scrollPane.setViewportView(table);

		// table.getColumn("English").setCellRenderer(new ButtonRenderer());
		// table.getColumn("English").setCellEditor(new ButtonEditor(new
		// JCheckBox()));

		// ButtonRenderer render = new ButtonRenderer();
		// ButtonEditor editor = new ButtonEditor(new JCheckBox());

		// MyButtonRenderer render = new MyButtonRenderer();
		// MyButtonCellEditor editor = new MyButtonCellEditor();

		// table.getColumnModel().getColumn(1).setCellRenderer(render);
		// table.getColumnModel().getColumn(1).setCellEditor(editor);
		// editor.setClickCountToStart(0);

		// String[] answer = {"A", "B", "C"};
		// table.getColumnModel().getColumn(1).setCellRenderer(new
		// MyRadioCellRenderer(answer));
		// table.getColumnModel().getColumn(1).setCellEditor(new
		// MyRadioCellEditor(new MyRadioCellRenderer(answer)));
		//
		// String choices[] = { "Color.red", "Color.orange", "Color.yellow",
		// "Color.green",
		// "Color.blue", "Color.magenta" };
		// JComboBox comboBox = new JComboBox(choices);
		// TableCellEditor editorComboBox = new DefaultCellEditor(comboBox);
		// TableColumn column = table.getColumnModel().getColumn(3);
		// //column.setCellRenderer(render);
		// column.setCellEditor(editorComboBox);

		// create renderer.

		MyProgressCellRenderer renderer = new MyProgressCellRenderer(
				MyProgressTableModel.MIN, MyProgressTableModel.MAX);
		renderer.setStringPainted(true);
		renderer.setBackground(table.getBackground());
		// set limit value and fill color
		Hashtable<Integer, Color> limitColors = new Hashtable<Integer, Color>();
		limitColors.put(new Integer(0), Color.green);
		limitColors.put(new Integer(20), Color.GRAY);
		limitColors.put(new Integer(40), Color.blue);
		limitColors.put(new Integer(60), Color.yellow);
		limitColors.put(new Integer(80), Color.red);

		renderer.setLimits(limitColors);
		// set renderer
		table.getColumnModel().getColumn(2).setCellRenderer(renderer);

		table.getColumnModel()
				.getColumn(1)
				.setCellEditor(
						new MyIntegerEditor(MyProgressTableModel.MIN,
								MyProgressTableModel.MAX));
	}

	public class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}

	}

	public class ButtonEditor extends DefaultCellEditor {
		protected JButton button;

		// protected String label = null;
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					ButtonClick();
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {

			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}

			button.setText(table.getValueAt(row, column).toString());
			return button;

		}

		// 若缺少此方法，点击按钮后将获取不到Button的Text值，将会显示false.
		@Override
		public Object getCellEditorValue() {
			return button.getText();
		}

		protected void ButtonClick() {
			System.out.println(table.getSelectedColumn() + " and  "
					+ table.getSelectedRow());
		}
	}

	public static void main(String args[]) {
		try {
			new TableCellComponent3();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// class TableTableModel extends DefaultTableModel {}
	class WordTableModel extends AbstractTableModel {
		Object rowData[][] = {
				{ "1", "Button one", Boolean.TRUE, "Color.red" },
				{ "2", "Button Two", Boolean.TRUE, "Color.blue" },
				{ "3", "Button Three", Boolean.FALSE, "Color.green" },
				{ "4", "Button Four", Boolean.TRUE, "Color.magenta" },
				{ "5", "Button five", Boolean.FALSE, "Color.pink" }, };
		// { "1", new JButton("Button one"), Boolean.TRUE, "Color.red"},
		// { "2", new JButton("Button Two"), Boolean.TRUE, "Color.blue"},
		// { "3", new JButton("Button Three"), Boolean.FALSE, "Color.green"},
		// { "4", new JButton("Button Four"), Boolean.TRUE, "Color.magenta" },
		// { "5", new JButton("Button Four"), Boolean.FALSE, "Color.pink"}, };
		String columnNames[] = { "No.", "English", "Boolean", "Color" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int column) {
			return columnNames[column];
		}

		public int getRowCount() {
			return rowData.length;
		}

		public Object getValueAt(int row, int column) {
			return rowData[row][column];
		}

		public Class getColumnClass(int column) {
			return getValueAt(0, column).getClass();
		}

		public void setValueAt(Object value, int row, int column) {
			rowData[row][column] = value;
		}

		public boolean isCellEditable(int row, int column) {
			return (column != 0);
			// return true;
		}
	}

	class MyProgressTableModel extends DefaultTableModel {
		public static final int MIN = 0;
		public static final int MAX = 100;

		public final String[] COLUMN_NAMES = new String[] { "name", "result",
				"indicator" };

		public MyProgressTableModel() {
			this.addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent e) {
					// 当引起TableModel改变的事件是UPDATE时并且是第二列时候:
					// when table action is update.
					if (e.getType() == TableModelEvent.UPDATE) {
						int col = e.getColumn();
						if (col == 1) {
							// 我们取得新设立的value,赋予第三列:
							// get the new set value.
							// Integer value = (Integer) model.getValueAt(row,
							// col);
							// model.setValueAt(checkMinMax(value), row, ++col);
							// 重写isCellEditable方法,设置可编辑的列:
						}
					}
				}
			});
		}

		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		public String getColumnName(int columnIndex) {
			return COLUMN_NAMES[columnIndex];
		}

		// 将Table设成只读的
		@Override
		public boolean isCellEditable(int row, int column) {
			switch (column) {
			case 1:
				return true;
			default:
				return false;
			}
		}

		// 重写setValueAt方法,设置可赋予的值:
		@Override
		public void setValueAt(Object obj, int row, int col) {
			// 这样一个我们需要的TableModel就完成了,修改第二列的值,第三列进度条也随之改变,使用也很简单:
			// set the table model.
			// table.setModel(dm);
		}
	}

	/**
	 * 第二列的Editor,使它编辑状态时可以验证我们的输入,并触发: Implements a cell editor that uses a
	 * formatted text field to edit Integer values.
	 */
	class MyIntegerEditor extends DefaultCellEditor {
		// 它有一个参数,用来处理编辑值的:
		// show component when cell edit
		private JFormattedTextField ftf;

		public MyIntegerEditor() {
			super(new JTextField());
		}

		public MyIntegerEditor(int min, int max) {
			super(new JTextField());
			ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "check");
			ftf.getActionMap().put("check", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
		           // The text is invalid.
		           if (!ftf.isEditValid()) {
		               //if (userSaysRevert()) {
		                   // reverted inform the editor
		               //    ftf.postActionEvent();
		               //}
		            } else{
		               try {
		                   // The text is valid, so use it.
		                   ftf.commitEdit();
		                   // stop editing
		                   ftf.postActionEvent();
		                } catch (java.text.ParseException exc) {
		                }
		            }
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			// 然后重写DefaultCellEditor的getTableCellEditorComponent方法,返回我们定义的JFormattedTextField.
			ftf = (JFormattedTextField) super.getTableCellEditorComponent(
					table, value, isSelected, row, column);
			ftf.setValue(value);
			return ftf;
		}

		// 重写getCellEditorValue方法,保证我们返回值正确：
		@Override
		public Object getCellEditorValue() {
			// 取得编辑完成的值:
			Object o = ftf.getValue();
			// 判断然后返回.
			return o.toString();
		}

		// 然后重写stopCellEditing方法,判断编辑的值是否正确,不正确的情况下提示用户,询问用户是返回还是重新设置.
		// Override to check whether the edit is valid,
		// setting the value if it is and complaining if it isn't.
		@Override
		public boolean stopCellEditing() {
			JFormattedTextField ftf = (JFormattedTextField) getComponent();
			if (ftf.isEditValid()) {
				try {
					ftf.commitEdit();
				} catch (java.text.ParseException exc) {
				}
			} else { // text is invalid
				// if (!userSaysRevert()) {
				// user wants to edit don't let the editor go away
				// return false;
				// }
			}
			return super.stopCellEditing();
		}
	}
}
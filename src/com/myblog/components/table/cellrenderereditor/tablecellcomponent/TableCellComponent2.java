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

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class TableCellComponent2 extends JFrame {
	//private DefaultTableModel tableModel;
  private WordTableModel tableModel;  
  private JTable table;

  public TableCellComponent2() {  
      setLayout(new BorderLayout());  

      setBounds(10,10,800,600);  
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
		//table.setBackground(Color.red);
		scrollPane.setViewportView(table);
		
//table.getColumn("English").setCellRenderer(new ButtonRenderer());  
//table.getColumn("English").setCellEditor(new ButtonEditor(new JCheckBox()));  
		
//		ButtonRenderer render = new ButtonRenderer();
//		ButtonEditor editor = new ButtonEditor(new JCheckBox());

//		MyButtonRenderer render = new MyButtonRenderer();
//		MyButtonCellEditor editor = new MyButtonCellEditor();

//      table.getColumnModel().getColumn(1).setCellRenderer(render);
//      table.getColumnModel().getColumn(1).setCellEditor(editor);
//      editor.setClickCountToStart(0);
		
		String[] answer = {"A", "B", "C"};
		MyRadioCellRenderer render = new MyRadioCellRenderer(answer);
		MyRadioCellEditor editor = new MyRadioCellEditor(render);		
		table.getColumnModel().getColumn(1).setCellRenderer(render);
		table.getColumnModel().getColumn(1).setCellEditor(editor);
      
		String choices[] = { "Color.red", "Color.orange", "Color.yellow", "Color.green",
				"Color.blue", "Color.magenta" };
		JComboBox comboBox = new JComboBox(choices);
		TableCellEditor editorComboBox = new DefaultCellEditor(comboBox);
		TableColumn column = table.getColumnModel().getColumn(3);
		//column.setCellRenderer(render);
		column.setCellEditor(editorComboBox);
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
      //protected String label = null;
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

      //若缺少此方法，点击按钮后将获取不到Button的Text值，将会显示false.  
      @Override  
      public Object getCellEditorValue(){  
          return button.getText();  
      }  
        
      protected void ButtonClick() {  
          System.out.println(table.getSelectedColumn() + " and  "  
                  + table.getSelectedRow());  
      }  
  }  
    
    
  public static void main(String args[]) {  
      try {  
          new TableCellComponent2();  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
  }
  
	//class TableTableModel extends DefaultTableModel {}
	class WordTableModel extends AbstractTableModel {
		Object rowData[][] = { 
				{ "1", "Button one",   Boolean.TRUE,  "Color.red"},
				{ "2", "Button Two",   Boolean.TRUE,  "Color.blue"},
				{ "3", "Button Three", Boolean.FALSE, "Color.green"},
				{ "4", "Button Four",  Boolean.TRUE,  "Color.magenta" },
				{ "5", "Button five",  Boolean.FALSE, "Color.pink"}, };
//				{ "1", new JButton("Button one"), Boolean.TRUE, "Color.red"},
//				{ "2", new JButton("Button Two"), Boolean.TRUE, "Color.blue"},
//				{ "3", new JButton("Button Three"), Boolean.FALSE, "Color.green"},
//				{ "4", new JButton("Button Four"), Boolean.TRUE, "Color.magenta" },
//				{ "5", new JButton("Button Four"), Boolean.FALSE, "Color.pink"}, };
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
			//return true;
		}
	}
}  
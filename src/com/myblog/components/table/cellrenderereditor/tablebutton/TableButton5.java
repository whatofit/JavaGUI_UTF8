package com.myblog.components.table.cellrenderereditor.tablebutton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class TableButton5 {
    public static JTable table;   
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Table5 Header");
        table = new JTable(6,6);       
        TableRender render = new TableRender();
        table.setRowHeight(20);
        TableEditor editor = new TableEditor(new JTextField());
        table.getColumnModel().getColumn(1).setCellRenderer(render);
        table.getColumnModel().getColumn(1).setCellEditor(editor);
        editor.setClickCountToStart(0);
        JScrollPane pane = new JScrollPane(table);           
        frame.setContentPane(pane);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);       
    }
}

class TableRender extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (row<0||column!=1)
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        else {
            JButton btn = new JButton("button"+row);
            btn.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return btn;
        }       
    }
}

class TableEditor extends DefaultCellEditor {
    public TableEditor(JCheckBox checkBox) {
        super(checkBox);
    }
    public TableEditor(JComboBox comboBox) {
        super(comboBox);
    }
    public TableEditor(JTextField textField) {
        super(textField);
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (row<0||column!=1)
            return super.getTableCellEditorComponent(table, value, isSelected,  row, column);
        else {
            JButton btn = new JButton("button");
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    JOptionPane.showMessageDialog(null, "test");
                }               
            });
            btn.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getPreferredWidth(),20));
            return btn;
        }
    }
}
package com.myblog.components.table.nestedtable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TableOfTable extends JTable {
    private Object[][] cells = { { "Mercury", 2440.0, 0, false, Color.YELLOW },
            { "Venus", 6052.0, 0, false, Color.YELLOW },
            { "Earth", 6378.0, 1, false, Color.BLUE },
            { "Mars", 3397.0, 2, false, Color.RED },
            { "Jupiter", 71492.0, 16, true, Color.ORANGE },
            { "Saturn", 60268.0, 18, true, Color.ORANGE },
            { "Uranus", 25559.0, 17, true, Color.BLUE },
            { "Neptune", 24766.0, 8, true, Color.BLUE },
            { "Pluto", 1137.0, 1, false, Color.BLACK } };
    private String[] columnNames = { "Planet", "Radius", "Moons", "Gaseous",
            "Color" };

    public TableOfTable() {
        // 添加一个缺省model，实际中可以根据自己需求定制。
        setModel(new DefaultTableModel(cells, columnNames));
        // 将第二行高度设置宽一些，使嵌入的表格显示起来好看些。
        setRowHeight(1, super.getRowHeight() * 4);
        getColumnModel().getColumn(2).setPreferredWidth(200);
    }

    // 重载getCellRenderer提供自己的TableCellRenderer
    public TableCellRenderer getCellRenderer(int row, int column) {
        if (row == 1 && column == 2) {// 在第二行、第三列提供一个子表的渲染器
            return new TableCellRenderer() {
                // 子表，可以自己定制子表的内容。
                JTable subTable = new JTable(new DefaultTableModel(cells,
                        columnNames));

                // 实现TableCellRenderer的方法，提供该子表作为渲染器
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    return subTable;
                }
            };
        } else
            // 如果是其他地方的表格，沿用父类中提供的渲染器
            return super.getCellRenderer(row, column);
    }

    public static void main(String[] fsd){
        JFrame d = new JFrame();
        TableOfTable table = new TableOfTable();
        JScrollPane pane = new JScrollPane(table);
        d.getContentPane().add(pane, BorderLayout.CENTER);
        d.setBounds(0, 0, 400, 400);   
        d.setVisible(true);   
    }   
}
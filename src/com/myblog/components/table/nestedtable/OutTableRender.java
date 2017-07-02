//package com.myblog.components.table.nestedtable;
//
//import java.awt.Component;
//import java.util.Vector;
//
//import javax.swing.ImageIcon;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//
//public class OutTableRender extends DefaultTableCellRenderer {
//
//  private static final long serialVersionUID = 1L;
//
//  private Vector<String> innerColNames = new Vector<String>();
//  public static int size, datasize;
//  public Object[] pic;
//
//  public OutTableRender(Object[] picture) {
//      for (int i = 0; i < 2; i++) {
//          innerColNames.add("innerColumn" + i);
//      }
//      size = 0;
//      datasize = picture.length;
//      pic = picture;
//  }
//
//  public Vector<String> getInnerColNames() {
//      return innerColNames;
//  }
//
//  public void setInnerColNames(Vector<String> innerColNames) {
//      this.innerColNames = innerColNames;
//  }
//
//  // @Override
//  public Component getTableCellRendererComponent(JTable table, Object value,
//          boolean isSelected, boolean hasFocus, int row, int column) {
//      final Vector<?> v = (Vector<?>) value;
//      // 内table的DefaultTableModel()
//      DefaultTableModel tm = new DefaultTableModel() {
//          private static final long serialVersionUID = 1L;
//
//          @Override
//          public int getColumnCount() {
//              return innerColNames.size();
//          }
//
//          @Override
//          public String getColumnName(int column) {
//              return innerColNames.get(column);
//          }
//
//          @Override
//          public int getRowCount() {
//              return v.size();
//          }
//
//          @Override
//          public boolean isCellEditable(int row, int column) {
//              return true;
//          }
//
//          @Override
//          public Object getValueAt(int row, int column) {
//              Vector<?> v1 = (Vector<?>) v.get(row);
//              return v1.get(column);
//          }
//
//          @Override
//          public void setValueAt(Object value, int row, int column) {
//              ((Vector<String>) v.get(row)).set(column, value.toString());
//              fireTableCellUpdated(row, column);
//          }
//      };
//      //CMap m = new CMap1();
//      //CTable t = new CTable(m, tm); // 合并单元格
//      JTable t1 = new JTable();
//      t1.getColumnModel().getColumn(0).setCellRenderer( // 设置第0列第0行为图片
//              new DefaultTableCellRenderer() {
//                  private static final long serialVersionUID = 1L;
//
//                  @Override
//                  public Component getTableCellRendererComponent(
//                          JTable table, Object value, boolean isSelected,
//                          boolean hasFocus, int row, int column) {
//                      String image = "";
//                      if (row == 0 && size < datasize)
//                          image = pic[size].toString();
//                      setIcon(new ImageIcon(image));
//                      size++;
//
//                      // TODO Auto-generated method stub
//                      Component c = super
//                              .getTableCellRendererComponent(table, value,
//                                      isSelected, hasFocus, row, column);
//
//                      return c;
//                  }
//              });
//
//      //TableButton tb = new TableButton();
//      //t1 = tb.ButtonColumn(t, 1);
//      JScrollPane jsp = new JScrollPane(t1);
//      return jsp;
//  }
//
//}

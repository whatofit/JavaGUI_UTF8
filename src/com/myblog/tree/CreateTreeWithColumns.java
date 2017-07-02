package com.myblog.tree;
//
////表格树
////通过TreeColumn实现“表格树”TableTree
//
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTree;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.TreeColumn;
//import org.eclipse.swt.widgets.TreeItem;
//public class CreateTreeWithColumns extends JFrame {
//    private JTree tree;
//    public static void main(String[] args) {
//        new TreeFrame();
//    }
//    public CreateTreeWithColumns() {
//        this.setSize(800, 600);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        
//        tree = new JTree();
//      //tree.setHeaderVisible(true);
//      TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
//      column1.setText("Column 1");
//      column1.setWidth(200);
//      TreeColumn column2 = new TreeColumn(tree, SWT.CENTER);
//      column2.setText("Column 2");
//      column2.setWidth(200);
//      TreeColumn column3 = new TreeColumn(tree, SWT.RIGHT);
//      column3.setText("Column 3");
//      column3.setWidth(200);
//      for (int i = 0; i < 4; i++) {
//          TreeItem item = new TreeItem(tree, SWT.NONE);
//          item.setText(new String[] { "item " + i, "abc", "defghi" });
//          for (int j = 0; j < 4; j++) {
//              TreeItem subItem = new TreeItem(item, SWT.NONE);
//              subItem.setText(new String[] { "subitem " + j, "jklmnop", "qrs" });
//              for (int k = 0; k < 4; k++) {
//                  TreeItem subsubItem = new TreeItem(subItem, SWT.NONE);
//                  subsubItem.setText(new String[] { "subsubitem " + k, "tuv", "wxyz" });
//              }
//          }
//      }
//      
//        
//        this.add(new JScrollPane(tree));
//        this.setVisible(true);
//    }
//  
//}
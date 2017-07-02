package com.myblog.components.table.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
 
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
 
 
public class TableMouseListener {
   
    public static void main(String[] args){
       JFrame frame = new JFrame();
       String[] tableTitleArray = {"ID","Name","Sex"};
       Object[][] body = new Object[6][tableTitleArray.length];
       for(int i = 0; i < 6;i++){
           body[i][0] = i;
           body[i][1] = "张三";
           body[i][2] = "男";
       }
       final JTable table = new JTable(new DefaultTableModel(body,tableTitleArray));
       final MouseInputListener mouseInputListener = getMouseInputListener(table);//添加鼠标右键选择行
       table.addMouseListener(mouseInputListener);
       table.addMouseMotionListener(mouseInputListener);
      
       frame.getContentPane().add(table,BorderLayout.CENTER);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setSize(new Dimension(640,480));
       frame.setVisible(true);
    }
   
    /*
     * 添加鼠标右键单击选择监听，并显示右键菜单
     */
    private static MouseInputListener getMouseInputListener(final JTable jTable){
       return new MouseInputListener() {
           public void mouseClicked(MouseEvent e) {
              processEvent(e);
           }
           public void mousePressed(MouseEvent e) {
              processEvent(e);
           }
           public void mouseReleased(MouseEvent e) {
              processEvent(e);
              if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0
                     && !e.isControlDown() && !e.isShiftDown()) {
//                popupMenu.show(tableLyz, e.getX(), e.getY());//右键菜单显示
              }
           }
           public void mouseEntered(MouseEvent e) {
              processEvent(e);
           }
 
           public void mouseExited(MouseEvent e) {
              processEvent(e);
           }
 
           public void mouseDragged(MouseEvent e) {
              processEvent(e);
           }
 
           public void mouseMoved(MouseEvent e) {
              processEvent(e);
           }
           private void processEvent(MouseEvent e) {
              if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
                  int modifiers = e.getModifiers();
                  modifiers -= MouseEvent.BUTTON3_MASK;
                  modifiers |= MouseEvent.BUTTON1_MASK;
                  MouseEvent ne = new MouseEvent(e.getComponent(), e.getID(),
                         e.getWhen(), modifiers, e.getX(), e.getY(), e
                                .getClickCount(), false);
                  jTable.dispatchEvent(ne);
              }
           }
       };
    }
   
}
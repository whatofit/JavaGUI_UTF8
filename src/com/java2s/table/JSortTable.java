package com.java2s.table;

/*


JSortTable.java

Created by Claude Duguay
Copyright (c) 2002
 This was taken from a Java Pro magazine article
 http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208

 I have NOT asked for permission to use this.


*/

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class JSortTable extends JTable implements MouseListener {
 protected int sortedColumnIndex = -1;
 protected boolean sortedColumnAscending = true;

 public JSortTable() {
    this(new DefaultSortTableModel());
 }

 public JSortTable(int rows, int cols) {
    this(new DefaultSortTableModel(rows, cols));
 }

 public JSortTable(Object[][] data, Object[] names) {
    this(new DefaultSortTableModel(data, names));
 }

 public JSortTable(Vector data, Vector names) {
    this(new DefaultSortTableModel(data, names));
 }

 public JSortTable(SortTableModel model) {
    super(model);
    initSortHeader();
 }

 public JSortTable(SortTableModel model, TableColumnModel colModel) {
    super(model, colModel);
    initSortHeader();
 }

 public JSortTable(SortTableModel model, TableColumnModel colModel,
                         ListSelectionModel selModel) {
    super(model, colModel, selModel);
    initSortHeader();
 }

 protected void initSortHeader() {
    JTableHeader header = getTableHeader();
    header.setDefaultRenderer(new SortHeaderRenderer());
    header.addMouseListener(this);
 }

 public int getSortedColumnIndex() {
    return sortedColumnIndex;
 }

 public boolean isSortedColumnAscending() {
    return sortedColumnAscending;
 }

 public void mouseReleased(MouseEvent event) {
    TableColumnModel colModel = getColumnModel();
    int index = colModel.getColumnIndexAtX(event.getX());
    int modelIndex = colModel.getColumn(index).getModelIndex();

    SortTableModel model = (SortTableModel)getModel();
    if (model.isSortable(modelIndex)) {
       // toggle ascension, if already sorted
       if (sortedColumnIndex == index) {
          sortedColumnAscending = !sortedColumnAscending;
       }
       sortedColumnIndex = index;

       model.sortColumn(modelIndex, sortedColumnAscending);
    }
 }

 public void mousePressed(MouseEvent event) {}
 public void mouseClicked(MouseEvent event) {}
 public void mouseEntered(MouseEvent event) {}
 public void mouseExited(MouseEvent event) {}
}

/*


DefaultSortTableModel.java

Created by Claude Duguay
Copyright (c) 2002
 This was taken from a Java Pro magazine article
 http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208

 I have NOT asked for permission to use this.


*/


class DefaultSortTableModel  extends DefaultTableModel
                                  implements SortTableModel {

 public DefaultSortTableModel() {}

 public DefaultSortTableModel(int rows, int cols) {
    super(rows, cols);
 }

 public DefaultSortTableModel(Object[][] data, Object[] names) {
    super(data, names);
 }

 public DefaultSortTableModel(Object[] names, int rows) {
    super(names, rows);
 }

 public DefaultSortTableModel(Vector names, int rows) {
    super(names, rows);
 }

 public DefaultSortTableModel(Vector data, Vector names) {
    super(data, names);
 }

 public boolean isSortable(int col) {
    return true;
 }

 public void sortColumn(int col, boolean ascending) {
    Collections.sort(getDataVector(),
       new ColumnComparator(col, ascending));
 }
}

/*


 SortTableModel.java

 Created by Claude Duguay
 Copyright (c) 2002
  This was taken from a Java Pro magazine article
  http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208

  I have NOT asked for permission to use this.

*/

interface SortTableModel extends TableModel {
  public boolean isSortable(int col);
  public void sortColumn(int col, boolean ascending);
}

/*


  ColumnComparator.java

  Created by Claude Duguay
  Copyright (c) 2002
   This was taken from a Java Pro magazine article
   http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208

   I have NOT asked for permission to use this.


*/

 class ColumnComparator implements Comparator {
   protected int index;
   protected boolean ascending;

   public ColumnComparator(int index, boolean ascending) {
      this.index = index;
      this.ascending = ascending;
   }

   public int compare(Object one, Object two) {
      if (one instanceof Vector && two instanceof Vector) {
         Vector vOne = (Vector)one;
         Vector vTwo = (Vector)two;
         Object oOne = vOne.elementAt(index);
         Object oTwo = vTwo.elementAt(index);
         if (oOne instanceof Comparable && oTwo instanceof Comparable) {
            Comparable cOne = (Comparable)oOne;
            Comparable cTwo = (Comparable)oTwo;
            if (ascending) {
               return cOne.compareTo(cTwo);
            }
            else {
               return cTwo.compareTo(cOne);
            }
         }
      }

      return 1;
   }
}






 /*
 

   SortHeaderRenderer.java

   Created by Claude Duguay
   Copyright (c) 2002
    This was taken from a Java Pro magazine article
    http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208

    I have NOT asked for permission to use this.

 
 */

  class SortHeaderRenderer extends DefaultTableCellRenderer {

    public static Icon NONSORTED =  new SortArrowIcon(SortArrowIcon.NONE);
    public static Icon ASCENDING =  new SortArrowIcon(SortArrowIcon.ASCENDING);
    public static Icon DECENDING =  new SortArrowIcon(SortArrowIcon.DECENDING);

    public SortHeaderRenderer() {
       setHorizontalTextPosition(LEFT);
       setHorizontalAlignment(CENTER);
    }

    public Component getTableCellRendererComponent( JTable table,
                               Object value,
                               boolean isSelected,
                               boolean hasFocus, int row, int col) {

       int index = -1;
       boolean ascending = true;
       if (table instanceof JSortTable) {
          JSortTable sortTable = (JSortTable)table;
          index = sortTable.getSortedColumnIndex();
          ascending = sortTable.isSortedColumnAscending();
       }
       if (table != null) {
          JTableHeader header = table.getTableHeader();
          if (header != null) {
             setForeground(header.getForeground());
             setBackground(header.getBackground());
             setFont(header.getFont());
          }
       }

       Icon icon = ascending ? ASCENDING : DECENDING;
       setIcon(col == index ? icon : NONSORTED);
       setText((value == null) ? "" : value.toString());
       setBorder(UIManager.getBorder("TableHeader.cellBorder"));
       return this;
    }
 }


  /*
  

    SortArrowIcon.java

    Created by Claude Duguay
    Copyright (c) 2002
     This was taken from a Java Pro magazine article
     http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208

     I have NOT asked for permission to use this.

  
  */

  class SortArrowIcon implements Icon {

     public static final int NONE = 0;
     public static final int DECENDING = 1;
     public static final int ASCENDING = 2;

     protected int direction;
     protected int width = 8;
     protected int height = 8;

     public SortArrowIcon(int direction) {
        this.direction = direction;
     }

     public int getIconWidth() {
        return width;
     }

     public int getIconHeight() {
        return height;
     }

     public void paintIcon(Component c, Graphics g, int x, int y) {
        Color bg = c.getBackground();
        Color light = bg.brighter();
        Color shade = bg.darker();

        int w = width;
        int h = height;
        int m = w / 2;
        if (direction == ASCENDING) {
           g.setColor(shade);
           g.drawLine(x, y, x + w, y);
           g.drawLine(x, y, x + m, y + h);
           g.setColor(light);
           g.drawLine(x + w, y, x + m, y + h);
        }
        if (direction == DECENDING) {
           g.setColor(shade);
           g.drawLine(x + m, y, x, y + h);
           g.setColor(light);
           g.drawLine(x, y + h, x + w, y + h);
           g.drawLine(x + m, y, x + w, y + h);
        }
     }
  }

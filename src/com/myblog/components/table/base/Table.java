package com.myblog.components.table.base;
//package jtable.base;
//
//import java.util.*;  
//import java.awt.event.*;  
//import javax.swing.*;  
//import javax.swing.event.*;  
//import javax.swing.table.*;  
//import java.awt.Dimension;  
////import ums.caf.cm.common.*;  
////import ums.uep.api.util.DebugPrn;  
//   
//import java.awt.*;  
//import javax.swing.JViewport;  
//import java.awt.datatransfer.Clipboard;  
//import java.awt.datatransfer.StringSelection;  
//   
///** 
// * 表组件，继承JTable，因此所有的JTable功能都可以使用。 
// *  
// * @author  
// * @version 1.0 
// */  
//public class Table extends JTable {  
//   
// // 该对象的操作类  
// private TableAction action = new TableAction();  
// // 该对象的状态类，保存排序信息，如果为null表示没有可排序的列  
// private SortInfo sortInfo;  
// private Table me;  
// // 保存表的列对象，因为排序列可能因为位置移动而改变，所以要记住原始位置。  
// protected TableColumn[] tableColumn;  
// // 保存选择的行信息  
// private ArrayList selectedRowNumList = null;  
//   
// static final int recordSelection = 19750301;  
// static final int reSetSelection = 19750302;  
// static final int deleteSelectionWhenRowDelete = 19750303;  
// static final int clearSelection = 19750304;  
// static final int deleteOneSelectItem = 19750305;  
//   
// private myTableModelListener reslModelListener = null;  
// private myListSelectionListener reslSelectionListener = null;  
// // 隐藏的列数,用于表头排序图标的现实计算,由于隐藏掉的列也要占用一个排序图标,  
// // 所以显示的排序列会从隐藏排序列的图标后计算,  
// // 增加隐藏列处理后,显示排序列从1开始计算  
// private int hideColNum = 0;  
//   
// private boolean multiLineSupport = false;  
// private int horizontalAlignment = SwingConstants.LEFT;  
// private int verticalAlignment = SwingConstants.CENTER;  
//   
// private boolean caseSensitive = true;  
//   
// private DebugPrn dMsg = new DebugPrn(this.getClass().getName());  
// /** 
//  * 构造函数 
//  *  
//  * @param model 
//  *            数据模型 
//  * @param sInfo 
//  *            排序信息，如果没有排序信息，写null。 
//  */  
// public Table(SortModel model, SortInfo sInfo) {  
//  super(model);  
//  model.setSortInfo(sInfo);  
//  // super.setModel(model);  
//  action.setTable(this);  
//  this.me = this;  
//   
//  // 保存最原始的列状态  
//  Enumeration enu = this.getColumnModel().getColumns();  
//  int columnCount = this.getColumnModel().getColumnCount();  
//  this.tableColumn = new TableColumn[columnCount];  
//  int k = 0;  
//  while (enu.hasMoreElements()) {  
//   this.tableColumn[k] = (TableColumn) enu.nextElement();  
//   k++;  
//  }  
//   
//  this.sortInfo = sInfo;  
//  if (this.sortInfo != null) {  
//   this.resetTableHeadShow();  
//   this.getSortModel().multiSort();  
//  }  
//  // 增加表头鼠标事件  
//  final JTableHeader hdr = this.getTableHeader();  
//  hdr.addMouseListener(new MouseAdapter() {  
//   public void mouseClicked(MouseEvent e) {  
//    if (e.getClickCount() == 1) {  
//     if (action != null) {  
//      action.headerMouseClick(e);  
//     }  
//    }  
//   }  
//  });  
//   
//  // 为表增加鼠标事件  
//  this.addMouseListener(new MouseAdapter() {  
//   public void mouseClicked(MouseEvent e) {  
//    if (e.getClickCount() == 2  
//      && e.getModifiers() == MouseEvent.BUTTON1_MASK) {  
//     if (action != null) {  
//      action.mouseDoubleClick(e);  
//     }  
//    }  
//    if (e.getClickCount() == 1  
//      && e.getModifiers() == MouseEvent.BUTTON1_MASK) {  
//     if (action != null) {  
//      action.mouseLeftClick(e);  
//     }  
//    }  
//    if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {  
//     ListSelectionModel lm = me.getSelectionModel();  
//     boolean isMutexSelected = (lm.getMinSelectionIndex() < lm  
//       .getMaxSelectionIndex());  
//     int row = rowAtPoint(e.getPoint());  
//     if (!isMutexSelected  
//       || (isMutexSelected && (!lm.isSelectedIndex(row)))) {  
//      if (row != -1) {  
//       me.getSelectionModel().setSelectionInterval(row,  
//         row);  
//      }  
//     }  
//   
//     if (action != null) {  
//      // 如果右健菜单为空，则调用action的方法产生右健菜单  
//      JPopupMenu rightMenu = null;  
//      rightMenu = action.createRightMenu(e);  
//      // 如果右健菜单仍为空，则表示没有右健菜单，执行鼠标右击事件，  
//      // 否则弹出右健菜单  
//      if (rightMenu != null) {  
//       int xLocation = e.getX();  
//       int yLocation = e.getY();  
//       rightMenu.show(me, xLocation, yLocation);  
//      } else {  
//       action.mouseRightClick(e);  
//      }  
//     }  
//    }  
//   }  
//   
//   // 鼠标释放事件  
//   public void mouseReleased(MouseEvent e) {  
//    if (action != null) {  
//     action.mouseReleased(e);  
//    }  
//   }  
//  });  
//   
//  DefaultListSelectionModel dlsm = new DefaultListSelectionModel() {  
//   public boolean isSelectionEmpty() {  
//    if (me.getSortModel().getRowCount() == 0) {  
//     return true;  
//    } else {  
//     return super.isSelectionEmpty();  
//    }  
//   }  
//  };  
//  this.setSelectionModel(dlsm);  
//  // 增加表选择事件。  
//  this.reslSelectionListener = new myListSelectionListener();  
//  this.getSelectionModel().addListSelectionListener(  
//    this.reslSelectionListener);  
//   
//  // 增加表模型监听器，当模型中增，删，重排序后，重设选择行。  
//  this.reslModelListener = new myTableModelListener();  
//  this.getSortModel().addTableModelListener(this.reslModelListener);  
// }  
//   
// /** 
//  * 隐藏的列数,用于表头排序图标的现实计算,由于隐藏掉的列也要占用一个排序图标, 所以显示的排序列会从隐藏排序列的图标后计算, 
//  * 增加隐藏列处理后,显示排序列从1开始计算 
//  *  
//  * @param model 
//  *            SortModel 
//  * @param sInfo 
//  *            SortInfo 
//  * @param hidecolnum 
//  *            int 
//  */  
// public Table(SortModel model, SortInfo sInfo, int hidecolnum) {  
//  this(model, sInfo);  
//  this.hideColNum = hidecolnum;  
//  this.resetTableHeadShow();  
// }  
//   
// public Table(SortModel model, SortInfo sInfo, boolean multiSupport,  
//   int horizontalAlignment, int verticalAlignment) {  
//  this(model, sInfo);  
//  this.multiLineSupport = multiSupport;  
//  this.horizontalAlignment = horizontalAlignment;  
//  this.verticalAlignment = verticalAlignment;  
//  this.resetTableHeadShow();  
// }  
//   
// private class myListSelectionListener implements ListSelectionListener {  
//  public void valueChanged(ListSelectionEvent e) {  
//   if (action != null) {  
//    action.valueChanged(e);  
//   }  
//  }  
// }  
//   
// private class myTableModelListener implements TableModelListener {  
//  public void tableChanged(TableModelEvent e) {  
//   ListSelectionModel lm = me.getSelectionModel();  
//   if (e.getType() == Table.recordSelection) {  
//    int anchorSelectionIndex = lm.getAnchorSelectionIndex();  
//    me.selectedRowNumList = new ArrayList();  
//    if (!(lm.isSelectionEmpty())) {  
//     for (int i = lm.getMinSelectionIndex(); i <= lm  
//       .getMaxSelectionIndex(); i++) {  
//      if (lm.isSelectedIndex(i)) {  
//       if (i != anchorSelectionIndex) {  
//        me.selectedRowNumList.add(SortModel.INT_CONST  
//          .get(me.getSortModel().getTrueRow(i)));  
//       }  
//      }  
//     }  
//     me.selectedRowNumList.add(SortModel.INT_CONST.get(me  
//       .getSortModel().getTrueRow(anchorSelectionIndex)));  
//    }  
//   
//   } else if (e.getType() == Table.deleteOneSelectItem) {  
//    if (me.selectedRowNumList != null) {  
//     int wantToDel = -1;  
//     for (int i = 0; i < me.selectedRowNumList.size(); i++) {  
//      Integer seleInteger = (Integer) me.selectedRowNumList  
//        .get(i);  
//      int selectRow = seleInteger.intValue();  
//      int deleteRow = e.getFirstRow();  
//      if (selectRow == deleteRow) {  
//       wantToDel = i;  
//      } else if (selectRow > deleteRow) {  
//       me.selectedRowNumList.set(i, SortModel.INT_CONST  
//         .get(selectRow - 1));  
//      }  
//     }  
//     if (wantToDel != -1) {  
//      me.selectedRowNumList.remove(wantToDel);  
//     }  
//    }  
//   
//   }  
//   
//   else if (e.getType() == Table.reSetSelection) {  
//    if (me.selectedRowNumList != null) {  
//     lm.clearSelection();  
//     for (int i = 0; i < me.selectedRowNumList.size(); i++) {  
//      int ii = ((Integer) (me.selectedRowNumList.get(i)))  
//        .intValue();  
//      ii = me.getSortModel().getShowRow(ii);  
//      lm.addSelectionInterval(ii, ii);  
//     }  
//     me.selectedRowNumList = null;  
//    }  
//   
//   } else if (e.getType() == Table.deleteSelectionWhenRowDelete) {  
//    if (me.selectedRowNumList != null) {  
//     lm.clearSelection();  
//     for (int i = 0; i < me.selectedRowNumList.size(); i++) {  
//      int ii = ((Integer) (me.selectedRowNumList.get(i)))  
//        .intValue();  
//      if (ii < e.getFirstRow()) {  
//       ii = me.getSortModel().getShowRow(ii);  
//       lm.addSelectionInterval(ii, ii);  
//      } else if (ii > e.getFirstRow()) {  
//       ii--;  
//       ii = me.getSortModel().getShowRow(ii);  
//       lm.addSelectionInterval(ii, ii);  
//      }  
//     }  
//    }  
//   } else if (e.getType() == Table.clearSelection) {  
//    lm.clearSelection();  
//   }  
//  }  
//   
// }  
//   
// /** 
//  * 去掉tableModel和selectionModel模型中与本表有关的所有监听器。 
//  */  
// public void removeAllListener() {  
//  if (this.reslSelectionListener != null) {  
//   this.getSelectionModel().removeListSelectionListener(  
//     this.reslSelectionListener);  
//  }  
//  if (this.reslModelListener != null) {  
//   this.getSortModel()  
//     .removeTableModelListener(this.reslModelListener);  
//  }  
//  this.getSelectionModel().removeListSelectionListener(this);  
//  this.getSortModel().removeTableModelListener(this);  
// }  
//   
// /** 
//  * 获取某一数据单元是否可以编辑，组件根据表的Action中的isCellEditable方法的返回值处理。 如果没有Action，返回true。 
//  *  
//  * @param rowIndex 
//  *            行数 
//  * @param columnIndex 
//  *            列数 
//  * @return 是否可以编辑 
//  */  
// public boolean isCellEditable(int showRow, int showColumn) {  
//  int trueRow = this.getSortModel().getTrueRow(showRow);  
//  int trueColumn = this.convertColumnIndexToModel(showColumn);  
//  if (this.getAction() != null) {  
//   return this.getAction().isCellEditable(trueRow, trueColumn);  
//  } else {  
//   return true;  
//  }  
// }  
//   
// /** 
//  * 获取表的事件监听器 
//  *  
//  * @return 
//  */  
// public TableAction getAction() {  
//  return action;  
// }  
//   
// /** 
//  * 设置表的事件监听器 
//  *  
//  * @param action 
//  */  
// public void setAction(TableAction action) {  
//  this.action = action;  
//  this.action.setTable(this);  
// }  
//   
// /** 
//  * 获取表组件的排序模型。 
//  *  
//  * @return 
//  */  
// public SortModel getSortModel() {  
//  return (SortModel) (super.getModel());  
// }  
//   
// /** 
//  * 返回所选择记录行的第一行行号，索引值从0开始。如果没有选择任何行，则返回-1。 
//  *  
//  * @return -- 真实的行。 
//  */  
// public int getSelectedTrueRow() {  
//  int tem = this.getSelectedRow();  
//  if (tem == -1) {  
//   return -1;  
//  }  
//  return this.getSortModel().getTrueRow(tem);  
// }  
//   
// /** 
//  * 返回所选择记录行的行号数组，如果没有选择行，则返回一个空数组 
//  *  
//  * @return -- 真实的行，而不是排序后的行号 
//  */  
// public int[] getSelectedTrueRows() {  
//  int[] newArr = this.getSelectedRows();  
//  int[] oldArr = new int[newArr.length];  
//  for (int i = 0; i < newArr.length; i++) {  
//   oldArr[i] = this.getSortModel().getTrueRow(newArr[i]);  
//  }  
//  return oldArr;  
// }  
//   
// /** 
//  * 返回所选择记录行的列号数组，如果没有选择行，则返回一个空数组。利用getSelectedColumns()方法可以获取显示的列。 
//  * 该方法应该在列选择的模式下使用，因为在行选择模式下，用户无法看清选择了那些列。 
//  *  
//  * @return 真实的列。 
//  */  
// public int[] getSelectedTrueColumns() {  
//  int[] newArr = this.getSelectedColumns();  
//  int[] oldArr = new int[newArr.length];  
//  for (int i = 0; i < newArr.length; i++) {  
//   oldArr[i] = this.convertColumnIndexToModel(newArr[i]);  
//  }  
//  return oldArr;  
// }  
//   
// /** 
//  * 获取所选择记录的第一列，索引值从0开始。如果没有选择任何列，则返回-1。 
//  * JTable的getSelectedColumn()方法提供了获取显示列号的方法。 
//  * 利用convertColumnIndexToModel()方法去转换。 
//  *  
//  * @return 真实的列号。 
//  */  
// public int getSelectedTrueColumn() {  
//  int tem = this.getSelectedColumn();  
//  if (tem == -1) {  
//   return -1;  
//  }  
//  return this.convertColumnIndexToModel(tem);  
// }  
//   
// /** 
//  * 获取列的字段名称，组成 字段名数组，如果列利用setHidedColumn隐藏，这些隐藏列不会获取。 
//  *  
//  * @return 字段名称的数组 
//  */  
// public String[] getColumnName() {  
//  String[] ret = new String[this.getColumnCount()];  
//  for (int i = 0; i < this.getColumnCount(); i++) {  
//   ret[i] = this.getColumnName(i);  
//  }  
//  return ret;  
// }  
//   
// /** 
//  * 设置某列是否显示 
//  *  
//  * @param ColumnName 
//  * @param isVisible 
//  *            为真表示显示 
//  * @exception RuntimeException 
//  *                列名错误 
//  */  
// public void setColumnVisible(String ColumnName, boolean isVisible) {  
//  int j = -1;  
//  for (int i = 0; i < this.tableColumn.length; i++) {  
//   if (this.tableColumn[i].getIdentifier().equals(ColumnName)) {  
//    j = i;  
//    break;  
//   }  
//  }  
//  if (j == -1) {  
//   throw new RuntimeException("ColumnName is invalid: " + ColumnName);  
//  }  
//  if (isVisible) { // 需要显示  
//   try {  
//    this.getColumn(ColumnName);  
//   } catch (IllegalArgumentException e) {  
//    this.addColumn(this.tableColumn[j]);  
//    int numCols = this.getColumnCount();  
//    if (j < numCols - 1) {  
//     this.moveColumn(numCols - 1, j);  
//    }  
//   }  
//  } else { // 需要不显示  
//   TableColumn tc = null;  
//   try {  
//    tc = this.getColumn(ColumnName);  
//   } catch (IllegalArgumentException e) {  
//    return;  
//    // do nothing  
//   }  
//   this.removeColumn(tc);  
//  }  
//    
//  // 设置非过滤列  
//  resetFilterColumn(ColumnName, isVisible);  
// }  
//   
// /** 
//  * 设置某列是否显示 
//  *  
//  * @param ColumnName 
//  * @param isVisible 
//  *            为真表示显示 
//  * @exception RuntimeException 
//  *                列名错误 
//  */  
// public void setColumnVisible(String columnName, boolean isVisible,  
//   boolean header) {  
//  if (header) {  
//   int j = -1;  
//   /*for (int i = 0; i < this.tableColumn.length; i++) { 
//    if (this.tableColumn[i].getIdentifier().equals(columnName)) { 
//     j = i; 
//     break; 
//    } 
//   }*/  
//   int realJ = 0;  
//   for (int i = 0; i < this.fixedColumn.length; i++) {  
//    try {  
//     this.headerTable.getColumn(this.fixedColumn[i].getIdentifier());  
//     realJ ++;  
//    } catch (IllegalArgumentException e) {  
//     //  
//    }  
//    if (this.fixedColumn[i].getHeaderValue().equals(columnName)) {  
//     j = i;  
//     break;  
//    }  
//   }  
//   if (j == -1) {  
//    throw new RuntimeException("ColumnName is invalid: "  
//      + columnName);  
//   }  
//   if (isVisible) { // 需要显示  
//    try {  
//     this.headerTable.getColumn(columnName);  
//    } catch (IllegalArgumentException e) {  
//     this.headerTable.addColumn(this.fixedColumn[j]); // fixedColumn  
//     int numCols = this.headerTable.getColumnCount();  
//     if (realJ < numCols - 1) {  
//      this.headerTable.moveColumn(numCols - 1, realJ);  
//     }  
//    }  
//   } else { // 需要不显示  
//    TableColumn tc = null;  
//    try {  
//     tc = this.headerTable.getColumn(columnName);  
//    } catch (IllegalArgumentException e) {  
//     return;  
//     // do nothing  
//    }  
//    this.headerTable.removeColumn(tc);  
//   }  
//   int width = 0;  
//   int count = headerTable.getColumnCount();  
//   for (int i = 0; i < count; i++) {  
//    width = width  
//      + headerTable.getColumnModel().getColumn(i).getWidth();  
//   }  
//   headerTable.setSize(width, headerTable.getHeight());  
//   headerTable.setPreferredScrollableViewportSize(new Dimension(width, 0));  
//  } else {  
//   setColumnVisible(columnName, isVisible);  
//  }  
//    
//  // 设置非过滤列  
//  resetFilterColumn(columnName, isVisible);  
//    
//  // 当活动列都不显示的时候，为了显示Corner，手动添加一个自定义的ColumnHeaderView  
//  if (this.headerTable != null && this.headerTable.getColumnCount() != 0) {  
//   JScrollPane scrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, this);  
//   if (this.getColumnCount() == 0) {  
//    JPanel panel = new JPanel();  
//    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  
//    panel.add(Box.createVerticalStrut(headerTable.getTableHeader().getPreferredSize().height));  
//    scrollPane.setColumnHeaderView(panel);  
//   } else {  
//    scrollPane.setColumnHeaderView(this.getTableHeader());  
//   }  
//  }  
// }  
//   
// /** 
//  * 获取某一列是否是显示的 
//  *  
//  * @param ColumnName 
//  * @return 
//  */  
// public boolean getColumnVisible(String ColumnName) {  
//  int j = -1;  
//  for (int i = 0; i < this.tableColumn.length; i++) {  
//   if (this.tableColumn[i].getIdentifier().equals(ColumnName)) {  
//    j = i;  
//    break;  
//   }  
//  }  
//  if (j == -1) {  
//   throw new RuntimeException("ColumnName is invalid: " + ColumnName);  
//  }  
//   
//  try {  
//   this.getColumn(ColumnName);  
//  } catch (IllegalArgumentException e) {  
//   return false;  
//   // do nothing  
//  }  
//  return true;  
//   
// }  
//   
// /** 
//  * 获取某一列是否是显示的 
//  *  
//  * @param ColumnName 
//  * @return 
//  */  
// public boolean getColumnVisible(String columnName, boolean header) {  
//  if (header) {  
//   int j = -1;  
//   for (int i = 0; i < this.tableColumn.length; i++) {  
//    if (this.tableColumn[i].getIdentifier().equals(columnName)) {  
//     j = i;  
//     break;  
//    }  
//   }  
//   if (j == -1) {  
//    throw new RuntimeException("ColumnName is invalid: "  
//      + columnName);  
//   }  
//   
//   try {  
//    this.headerTable.getColumn(columnName);  
//   } catch (IllegalArgumentException e) {  
//    return false;  
//    // do nothing  
//   }  
//   return true;  
//  } else {  
//   return getColumnVisible(columnName);  
//  }  
// }  
//   
// /** 
//  * 隐藏某些列，如果不需要隐藏任何列，则参数输入null，隐藏列并没有改变表的数据 
//  * 模型中的内容，也就是说某一列的列号在隐藏前后是一样的，对隐藏列后的表的操作 应该和没隐藏之前一样 
//  *  
//  * @param columnName 
//  *            -- 需要被隐藏的列字段名。 
//  */  
// public void setHidedColumn(String[] hideColumnName) {  
//  // 恢复最原始的列状态，首先删掉当前表中所有的列（有可能隐藏了一部分列），  
//  // 再把所有的列加入，tableColumn中保存的是所有的列  
//  TableColumnModel cm = this.getColumnModel();  
//  for (int i = 0; i < this.tableColumn.length; i++) {  
//   cm.removeColumn(this.tableColumn[i]);  
//  }  
//  for (int i = 0; i < this.tableColumn.length; i++) {  
//   cm.addColumn(this.tableColumn[i]);  
//  }  
//   
//  if (hideColumnName == null) {  
//   return;  
//  }  
//  // 设置需要隐藏的列  
//  for (int i = 0; i < hideColumnName.length; i++) {  
//   cm.removeColumn(this.getColumn(hideColumnName[i]));  
//  }  
//    
//  // 设置隐藏列不过滤  
//  this.getSortModel().setUnFilterColumns(Arrays.asList(hideColumnName));  
// }  
//   
// /** 
//  * 获取该表组件的SortInfo对象。 
//  *  
//  * @return 
//  */  
// public SortInfo getSortInfo() {  
//  return sortInfo;  
// }  
//   
// // /**  
//   
// protected TableColumn[] fixedColumn = null;  
// private Table headerTable = null;  
// private Table mainTable = null;  
//   
// /**  
//  * 设置需要固定列的个数，从表左边开始。如果nbColsToFix是0，表示没有列被固定。  
//  *   
//  * @param nbColsToFix  
//  */  
// public void setFixedColumnCount(int nbColsToFix) {  
//  if (nbColsToFix >= this.getColumnCount()) {  
//   nbColsToFix = 0;  
//  }  
//  JScrollPane scrollPane = (JScrollPane) SwingUtilities  
//    .getAncestorOfClass(JScrollPane.class, this);  
//  if (scrollPane == null) {  
//   return;  
//  }  
//  TableColumnModel tcm = this.getColumnModel();  
//  if (nbColsToFix <= 0) { // no fixed column  
//   scrollPane.setRowHeader(null);  
//   scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, null);  
//   if (this.fixedColumn != null) {  
//    for (int i = 0; i < this.fixedColumn.length; i++) {  
//     TableColumn currCol = this.fixedColumn[this.fixedColumn.length  
//       - 1 - i];  
//     TableColumn temCol = this.headerTable.getColumn(currCol  
//       .getIdentifier());  
//     if (temCol != null) {  
//      currCol.setPreferredWidth(temCol.getWidth());  
//     }  
//     tcm.addColumn(currCol);  
//     int numCols = tcm.getColumnCount();  
//     tcm.moveColumn(numCols - 1, 0);  
//    }  
//   }  
//   if (this.headerTable != null) {  
//    this.headerTable.removeAllListener();  
//   }  
//   this.fixedColumn = null;  
//   this.headerTable = null;  
//   // this.repaint();  
//  } else {  
//   this.setFixedColumnCount(0);// 回复初态。  
//   
//   this.headerTable = new Table(this.getSortModel(), this  
//     .getSortInfo(), true, horizontalAlignment, verticalAlignment);  
//   this.headerTable.mainTable = this;  
//   this.headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
//   this.headerTable.getTableHeader().setReorderingAllowed(false);  
//   int realFixColCount = this.getColumnCount();  
//   if (realFixColCount > nbColsToFix) {  
//    realFixColCount = nbColsToFix;  
//   }  
//   int fixedColWidth = 0;  
//   this.fixedColumn = new TableColumn[realFixColCount];  
//   
//   TableColumnModel headColModel = headerTable.getColumnModel();  
//   // 将所有需要固定的列移至最前面  
//   for (int i = 0; i < realFixColCount; i++) { // 固定列个数  
//    this.fixedColumn[i] = this.getColumnModel().getColumn(i);// record fixed col  
//      
//    int width = this.fixedColumn[i].getPreferredWidth(); // col width  
//    fixedColWidth += width; // fixed cols width  
//    Object obj = this.fixedColumn[i].getIdentifier();  
//    int index = headColModel.getColumnIndex(obj);  
//    TableColumn ht = headColModel.getColumn(index);  
//    ht.setPreferredWidth(width); // set headtable width  
//    headColModel.moveColumn(index, i); // move column as main table  
//   }  
//   
//   for (int i = 0; i < realFixColCount; i++) { // remove main column  
//    this.getColumnModel().removeColumn(this.fixedColumn[i]);  
//   }  
//     
//   for (int i = this.tableColumn.length - 1; i >= realFixColCount; i--) { // remove head column  
//   
//    headColModel.removeColumn(headColModel.getColumn(i));  
//   }  
//   headerTable.setPreferredScrollableViewportSize(new Dimension(  
//     fixedColWidth, 0));  
//   
//   scrollPane.setRowHeaderView(headerTable);  
//   scrollPane.setColumnHeaderView(this.tableHeader);  
//   final JViewport headView = scrollPane.getRowHeader();  
//   final JViewport js = scrollPane.getViewport();  
//   Point headPp = headView.getViewPosition();  
//   Point mainPp = js.getViewPosition();  
//   headPp.y = mainPp.y;  
//   headView.setViewPosition(headPp);  
//   headView.addChangeListener(new ChangeListener() {  
//    public void stateChanged(ChangeEvent e) {  
//     Point pp = headView.getViewPosition();  
//     Point pp3 = js.getViewPosition();  
//     pp3.y = pp.y;  
//     js.setViewPosition(pp3);  
//    }  
//   });  
//   headerTable.getTableHeader().setSize(headerTable.getTableHeader().getPreferredSize());  
//   scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, headerTable  
//     .getTableHeader());  
//     
//   headerTable.setSelectionModel(me.getSelectionModel());  
//   TableAction headerAction = (TableAction) (me.getAction().clone());  
//   headerTable.setAction(headerAction);  
//   headerTable.setDefaultRenderer(Object.class, me  
//     .getDefaultRenderer(Object.class));  
//   this.addCopyActionListener();  
//  }  
// }  
//   
// /** 
//  * 获取所选择的数据，按照真实列、显示行的次序。该方法主要用于单元选择的方式，选择了哪些单元，就返回那些单元。 
//  * 因此，如果用于行选择方式用户会看不清楚选择了哪些列。 
//  *  
//  * @return 
//  */  
// public CmCacheRowSet getSelectedData() {  
//  CmCacheRowSet ret = new CmCacheRowSet();  
//  int[] col = this.getSelectedTrueColumns();  
//  if (col != null && col.length != 0) {  
//   for (int i = 0; i < col.length; i++) {  
//    CmCacheRowSet cs = this.getSelectedColumnData(col[i]);  
//    ret.addColumn(cs.getHead()[0], cs.getColumnData(0));  
//   }  
//  }  
//  return ret;  
// }  
//   
// /** 
//  * 获取所选择的数据行中某一列数据，该方法即使在行选择的情况下，也会获取一整列的数据，按照显示行的次序。 
//  * 如果列号错误，返回空的CmCacheRowSet对象。 
//  *  
//  * @param trueColumn 
//  * @return 
//  */  
// public CmCacheRowSet getSelectedColumnData(int trueColumn) {  
//  CmCacheRowSet ret = new CmCacheRowSet();  
//  int colCount = this.getColumnCount();  
//  if ((trueColumn < 0) || (trueColumn >= colCount)) {  
//   return ret;  
//  }  
//  String colName = this.getModel().getColumnName(trueColumn);  
//   
//  int rowCount = this.getRowCount();  
//  int rowSelected = this.getSelectedRowCount();  
//  Object[] disColData = new Object[rowSelected];  
//  int j = 0;  
//  for (int i = 0; i < rowCount; i++) {  
//   if (this.isRowSelected(i)) {  
//    disColData[j] = this.getModel().getValueAt(i, trueColumn);  
//    j++;  
//   }  
//  }  
//  CmField cf = new CmField(colName, null, null);  
//  ret.addColumn(cf, disColData);  
//  return ret;  
// }  
//   
// /** 
//  * 获取没有被选中的数据行的某一列数据，按照显示行的次序。 如果列号错误，返回空的CmCacheRowSet对象。 
//  *  
//  * @return 一列中没有被选中的数据 
//  */  
// public CmCacheRowSet getUnSelectedColumnData(int trueColumn) {  
//  CmCacheRowSet ret = new CmCacheRowSet();  
//  int colCount = this.getColumnCount();  
//  if ((trueColumn < 0) || (trueColumn >= colCount)) {  
//   return ret;  
//  }  
//  String colName = this.getModel().getColumnName(trueColumn);  
//   
//  int rowCount = this.getRowCount();  
//  int rowSelected = this.getSelectedRowCount();  
//  Object[] disColData = new Object[rowCount - rowSelected];  
//  int j = 0;  
//  for (int i = 0; i < rowCount; i++) {  
//   if (!(this.isRowSelected(i))) {  
//    disColData[j] = this.getModel().getValueAt(i, trueColumn);  
//    j++;  
//   }  
//  }  
//  CmField cf = new CmField(colName, null, null);  
//  ret.addColumn(cf, disColData);  
//  return ret;  
// }  
//   
// /** 
//  * 获取没有被选中的数据行的某一列数据，按照显示行的次序。 
//  *  
//  * @param columnName 
//  *            该列的字段名称，对应<a href="http://lib.csdn.net/base/mysql" class='replace_word' title="MySQL知识库" target='_blank' style='color:#df3434; font-weight:bold;'>数据库</a>中的字段名，字段名无效返回null 
//  * @return 一列中没有被选中的数据 
//  * @exception IllegalArgumentException 
//  *                列名错误。 
//  */  
// public CmCacheRowSet getUnSelectedColumnData(String columnName) {  
//  int trueColumn = this.getSortModel().getTrueColumnFromName(columnName);  
//  return this.getUnSelectedColumnData(trueColumn);  
// }  
//   
// /** 
//  * 获取所选择的数据中某一列数据，按照显示行的次序。 
//  *  
//  * @param columnName 
//  *            对应数据库中的字段，字段名无效返回null 
//  * @return 一列中被选中数据的数组 
//  * @exception IllegalArgumentException 
//  *                列名错误。 
//  */  
// public CmCacheRowSet getSelectedColumnData(String columnName) {  
//  int trueColumn = this.getSortModel().getTrueColumnFromName(columnName);  
//  return this.getSelectedColumnData(trueColumn);  
// }  
//   
// /** 
//  * 获取被选择行的整行数据，即使没有选择完整的行，按照真实行真实列的次序。  
//  * 本方法不受隐藏列的影响，即使隐藏了，也可以获取到。 
//  * @return 被选择行的数据 
//  */  
// public CmCacheRowSet getSelectedRowsData() {  
//  int[] selectRows = this.getSelectedTrueRows();  
//  return this.getSortModel().getRowsData(selectRows);  
// }  
//   
// /** 
//  * 得到所有的记录，按照显示行、真实列的次序。 
//  *  
//  * @return CmCacheRowSet 
//  */  
// public CmCacheRowSet getAllRowsData() {  
//  int[] trueRows = new int[this.getRowCount()];  
//  for (int i = 0; i < trueRows.length; i++) {  
//   // 按照显示行的次序创建真实行数组。  
//   trueRows[i] = this.getSortModel().getTrueRow(i);  
//  }  
//  return this.getSortModel().getRowsData(trueRows);  
// }  
//   
// /** 
//  * 根据选择的列得到所有的记录，按照显示行排序，给定列的次序，注意该方法和sortmodel中的方法区别是该数据是经过过滤的。 
//  *  
//  * @return CmCacheRowSet 
//  */  
// public CmCacheRowSet getAllRowsData(String[] colName) {  
//  CmCacheRowSet ret = new CmCacheRowSet();  
//  for (int i = 0; i < colName.length; i++) {  
//   CmCacheRowSet colData = this.getAllRowsByColumn(colName[i]);  
//   ret.addColumn(new CmField(colName[i], null, null), colData  
//     .getColumnData(0));  
//  }  
//  return ret;  
// }  
//   
// /** 
//  * 获取某一列的数据，按照显示行次序，注意该方法和sortmodel中的方法区别是该数据是经过过滤的。 
//  *  
//  * @param colName 
//  *            String 
//  * @return CmCacheRowSet 
//  */  
// public CmCacheRowSet getAllRowsByColumn(String colName) {  
//  int trueCol = this.getSortModel().getTrueColumnFromName(colName);  
//  int rowCount = this.getRowCount();  
//  Object[] colData = new Object[rowCount];  
//  for (int i = 0; i < rowCount; i++) {  
//   colData[i] = this.getSortModel().getValueAt(i, trueCol);  
//  }  
//  CmField cf = new CmField(colName, null, null);  
//  CmCacheRowSet ret = new CmCacheRowSet();  
//  ret.addColumn(cf, colData);  
//  return ret;  
//   
// }  
//   
// /** 
//  * 获取没有被选择行的整行数据，即使没有选择完整的行，按照显示行，真实列次序。 
//  *  
//  * @return 所有没被选中的数据 
//  */  
// public CmCacheRowSet getUnSelectedRowsData() {  
//  int rowSize = this.getRowCount();  
//  int[] unSelectTrueRows = new int[rowSize - this.getSelectedRowCount()];  
//  int j = 0;  
//  for (int i = 0; i < rowSize; i++) {  
//   if (!(this.isRowSelected(i))) {  
//    unSelectTrueRows[j] = this.getSortModel().getTrueRow(i);  
//    j++;  
//   }  
//  }  
//  return this.getSortModel().getRowsData(unSelectTrueRows);  
// }  
//   
// /** 
//  * 按照排序信息设置表头的显示，只用于初始化表格时。 
//  */  
// public void resetTableHeadShow() {  
//        SortInfo sortInfo = this.getSortInfo();  
//        if(sortInfo == null){  
//            return;  
//        }  
//  SortColumn[] sort = sortInfo.getSort();  
//  if (sort == null || sort.length == 0) {  
//   return;  
//  }  
//  for (int i = 0; i < sort.length; i++) {  
//   TableColumn column = this.tableColumn[sort[i].columnNo];  
//   // 修改为使用有隐藏列的表头构造函数  
//   if (multiLineSupport) {  
//    column.setHeaderRenderer(new MultiLineHeaderRenderer(sort[i],  
//        this.hideColNum, horizontalAlignment,  
//        verticalAlignment));  
//   } else {  
//    column.setHeaderRenderer(new HeaderRenderer(sort[i],  
//      this.hideColNum));  
//   }  
//  }  
//   
// }  
//   
// /** 
//  * 继承JTable中的监听事件，只是为了提高显示效率，用户不用使用该方法。 
//  *  
//  * @param e 
//  */  
// public void tableChanged(TableModelEvent e) {  
//  if (e != null) {  
//   int type = e.getType();  
//   if (type == Table.recordSelection || type == Table.reSetSelection  
//     || type == Table.deleteSelectionWhenRowDelete  
//     || type == Table.clearSelection  
//     || type == Table.deleteOneSelectItem) {  
//   } else {  
//    // System.out.println("type" + type);  
//    super.tableChanged(e);  
//   }  
//   
//  } else {  
//   super.tableChanged(e);  
//  }  
// }  
//   
// public Vector getHeaderItems() {  
//  Vector headerItemsVector = new Vector();  
//  int count = tableColumn.length;  
//  for (int i = 0; i < count; i++) {  
//   final String columnName = (String) (tableColumn[i].getIdentifier());  
//   JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(columnName);  
//   final boolean fixCol;  
//   if (fixedColumn != null && fixedColumn.length != 0) {  
//    fixCol = Arrays.asList(fixedColumn).contains(tableColumn[i]);  
//   } else {  
//    fixCol = false;  
//   }  
//   
//   menuItem.setSelected(fixCol ? headerTable  
//     .getColumnVisible(columnName)  
//     : getColumnVisible(columnName));  
//   menuItem.addActionListener(new ActionListener() {  
//   
//    public void actionPerformed(ActionEvent e) {  
//     boolean visible = fixCol ? getColumnVisible(columnName,  
//       true) : getColumnVisible(columnName);  
//     if (visible) {  
//      setColumnVisible(columnName, false, fixCol);  
//     } else {  
//      setColumnVisible(columnName, true, fixCol);  
//     }  
//    }  
//   
//   });  
//   headerItemsVector.add(menuItem);  
//  }  
//  return headerItemsVector;  
// }  
//   
// /** 
//  * 内部使用的方法，固定列的功能中使用。 
//  *  
//  * @param e 
//  */  
// public void columnMarginChanged(ChangeEvent e) {  
//  super.columnMarginChanged(e);  
//  if (this.mainTable != null) {  
//   int width = this.getColumnModel().getTotalColumnWidth();  
//   
//   this.setPreferredScrollableViewportSize(new Dimension(width, 0));  
//   // JScrollPane scrollPane =  
//   // (JScrollPane)SwingUtilities.getAncestorOfClass(JScrollPane.class,  
//   // this);  
//   // scrollPane.updateUI();  
//  }  
//   
// }  
//   
// /** 
//  * 如果选中的第一行不在屏幕中，则滚动屏幕使它出现在屏幕中。 
//  */  
// public void setViewToSelectedRow() {  
//  JScrollPane scrollPane = (JScrollPane) SwingUtilities  
//    .getAncestorOfClass(JScrollPane.class, this);  
//  if (scrollPane != null) {  
//   int row = this.getSelectedRow();  
//   if (row == -1) {  
//    return;  
//   }  
//   int rowHeight = this.getRowHeight(); // 行高  
//   int count = this.getRowCount(); // 总行数  
//   int pos = row * rowHeight; // 选择行的位置  
//   int showHeight = scrollPane.getViewport().getHeight(); // 可显示的高度  
//   
//   int viewRow = 0;  
//   if (showHeight % rowHeight != 0) {  
//    viewRow = showHeight / rowHeight + 1; // 可显示的行数  
//   } else {  
//    viewRow = showHeight / rowHeight; // 可显示的行数  
//   }  
//   int currPos = scrollPane.getViewport().getViewPosition().y;  
//   // 如果选择行位置大于最大行，  
//   if (!(pos >= currPos && pos < currPos + showHeight)) {  
//    if (viewRow <= count - row) {  
//     scrollPane.getViewport().setViewPosition(new Point(0, pos));  
//    } else {  
//     scrollPane.getViewport().setViewPosition(  
//       new Point(0, pos - showHeight + rowHeight));  
//    }  
//   }  
//  }  
// }  
//   
// /** 
//  * 添加复制键盘动作监听器,主要针对在进行setFixedColumnCount 
//  * 后,使用ctrl+c的键盘进行复制时只能复制头表内容的情况进行ctrl+c动作的截获重载 
//  */  
// public void addCopyActionListener() {  
//  // 以实现ctrl+c的按键组合  
//  KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C,  
//    ActionEvent.CTRL_MASK, false);  
//  // 如果固定头表不为空则将ctrl+c的键盘监听器注册上去  
//  if (this.headerTable != null)  
//   headerTable.registerKeyboardAction(copyAction, "Copy", copy,  
//     JComponent.WHEN_FOCUSED);  
// }  
//   
// private ActionListener copyAction = new ActionListener() {  
//  private Clipboard system;  
//  private StringSelection stsel;  
//   
//  /** 
//   * 实现拷贝动作,复制内容的策略是将头表选择行的所有列的内容加上可滚动表的所有列的内容,主要针对在进行setFixedColumnCount 
//   * 后,使用ctrl+c的键盘进行复制时只能复制头表内容的情况进行ctrl+c动作的截获重载 
//   */  
//   
//  public void actionPerformed(ActionEvent e) {  
//   if (e.getActionCommand().compareTo("Copy") == 0) {  
//    StringBuffer sbf = new StringBuffer();  
//   
//    // 头表的列的个数  
//    int headcols = headerTable.getColumnModel().getColumnCount();  
//    // 滚动表的列的个数  
//    int numcols = getColumnModel().getColumnCount();  
//    // 选择的行的个数,因为头表的选择行等于滚动表的选择行,所以只需要取一次就可以  
//    int numrows = getSelectedRowCount();  
//    int[] rowsselected = getSelectedRows();  
//   
//    for (int i = 0; i < numrows; i++) {  
//     for (int j = 0; j < headcols; j++) {  
//      // 获取头标每行的数据  
//      sbf.append(headerTable.getValueAt(rowsselected[i], j));  
//      if (j < headcols - 1)  
//       sbf.append("/t");  
//     }  
//     for (int j = 0; j < numcols; j++) {  
//      // 获取滚动表的每行的数据  
//      sbf.append(getValueAt(rowsselected[i], j));  
//      if (j < numcols - 1)  
//       sbf.append("/t");  
//     }  
//     sbf.append("/n");  
//    }  
//   
//    stsel = new StringSelection(sbf.toString());  
//    // 获取系统的粘贴板  
//    system = Toolkit.getDefaultToolkit().getSystemClipboard();  
//    // 将表数据放到粘贴板上  
//    system.setContents(stsel, stsel);  
//   }  
//  }  
// };  
//   
// public void setHideColNum(int colnum) {  
//  this.hideColNum = colnum;  
// }  
//   
// public Table getHeaderTable() {  
//  return headerTable;  
// }  
//   
// public Table getMainTable() {  
//  return mainTable;  
// }  
//   
// /** 
//  * 或者表格过滤框组件 
//  * @return 
//  */  
// public JPanel getFilterComponent() {  
//  SortModel sm = this.getSortModel();  
//  DefaultTextFilter filter = new DefaultTextFilter(sm);  
//  filter.setSensitive(caseSensitive);  
//  FilterTextField tf = new FilterTextField(filter);  
//  sm.setRowFilter(filter);  
//  return tf;  
// }  
//   
// /** 
//  * 设置过滤是否大小写敏感(false--不敏感, true--敏感) 
//  * @param sensitive 
//  */  
// public void setFilterSensitive(boolean sensitive) {  
//  caseSensitive = sensitive;  
// }  
//   
// /** 
//  * 重新设置隐藏列过滤状态 
//  * @param columnName 
//  * @param isVisible 
//  */  
// protected void resetFilterColumn(String columnName, boolean isVisible) {  
//  // 设置非过滤列  
//  if (isVisible) {  
//   this.getSortModel().removeUnFilterCol(columnName);  
//  } else {  
//   this.getSortModel().addUnFilterCol(columnName);  
//  }  
// }  
//   
// public void addColumn(TableColumn aColumn) {  
//        super.addColumn(aColumn);  
//        this.getSortModel().removeUnFilterCol((String)aColumn.getIdentifier()); // edge @091012 for unfilter hide columns  
//        this.getSortModel().multiSort();  
//    }  
//   
// public void removeColumn(TableColumn aColumn) {  
//        super.removeColumn(aColumn);  
//        this.getSortModel().addUnFilterCol((String)aColumn.getIdentifier()); // edge @091012 for unfilter hide columns  
//        this.getSortModel().multiSort();  
//    }  
//}  
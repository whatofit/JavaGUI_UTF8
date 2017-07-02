//package db.table;
//
//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.table.AbstractTableModel;
//
///**
// * 
// * @Date: 2013-11-15 上午09:29:49
// * @Author: gyh
// * @TODO:
// */
//public class TestTable implements ActionListener {
//  private DBConnection db;
//  private String[] columns = { "序号", "姓名", "学号", "成绩" };
//  private List<Object> rows = null;
//  private JFrame frame;// 主界面
//  private AbstractTableModel model;
//  private JTable table;
//  private Container contentPane;// 容器，存放表格等东西。
//  private Toolkit tools = Toolkit.getDefaultToolkit();
//  private Dimension size = tools.getScreenSize();
//  private int width = size.width / 2;
//  private int height = size.height / 2;
//  private Connection conn;
//  private Statement stat;
//  private JTextField nameField;// 用来搜索学生姓名的输入框
//
//  public TestTable() {
//      frame = new JFrame("java 表格");
//      rows = new ArrayList<Object>();
//      init();
//  }
//
//  private void init() {
//      frame.setSize(width, height);// 设置大小
//      frame.setLocation(width / 2, height / 2);// 设置位置
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭时动作
//      frame.setLayout(new BorderLayout());// 设置布局方式
//      contentPane = frame.getContentPane();
//      model = new TableModel();// TableModel是自定义的类。使用自定义的类是为了更好的做自己想要做的事，比如更新数据库＝＝。
//      table = new JTable(model);
//      JScrollPane scroll = new JScrollPane(table,
//              JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//              JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);// 给表格加入滚动条，横向不加，纵向加
//      contentPane.add(scroll, BorderLayout.CENTER);
//      // 新建一个面板，放在表格顶端，用来放置搜索条件
//      JPanel searPanel = new JPanel();
//      JButton loadBtn = new JButton("搜索");
//      loadBtn.addActionListener(this);
//      loadBtn.setActionCommand("load");
//      nameField = new JTextField(15);
//      searPanel.add(nameField);
//      searPanel.add(loadBtn);
//      contentPane.add(searPanel, BorderLayout.NORTH);
//      // 新建一个面板，放在表格底部，用来放置按钮
//      JPanel btnPanel = new JPanel();
//      JButton clearBtn = new JButton("清除");
//      JButton exitBtn = new JButton("退出");
//      clearBtn.addActionListener(this);
//      clearBtn.setActionCommand("clear");
//      exitBtn.addActionListener(this);
//      exitBtn.setActionCommand("exit");
//      btnPanel.add(clearBtn);
//      btnPanel.add(exitBtn);
//      contentPane.add(btnPanel, BorderLayout.SOUTH);
//      frame.setVisible(true);
//  }
//
//  class TableModel extends AbstractTableModel {
//      /**
//       * 获取数据列数
//       */
//      @Override
//      public int getColumnCount() {
//          return columns.length;
//      }
//
//      /**
//       * 获取数据行数
//       */
//      @Override
//      public int getRowCount() {
//          return rows.size();
//      }
//
//      /**
//       * 获取某个单元格数据
//       */
//      @Override
//      public Object getValueAt(int rowIndex, int columnIndex) {
//          return ((Object[]) rows.get(rowIndex))[columnIndex];
//      }
//
//      // ***************** 前面三个方法是必须实现的，后面的方法看情况********/
//      /**
//       * 获取表头（如果没有这个方法则表头是ABCD...这样子）
//       */
//      @Override
//      public String getColumnName(int column) {
//          return columns[column];
//      }
//
//      /**
//       * 自定义某行或某列或某个单元格是否可以被编辑，如果没有这个方法的话就是全都不能被编辑
//       */
//      @Override
//      public boolean isCellEditable(int row, int col) {
//          // 此处是 如果是第一列(即id)则不可编辑，其余可以编辑
//          if (col == 0)
//              return false;
//          return true;
//      }
//
//      /**
//       * 修改单元格的值。如果要编辑单元格的话就要继承这个方法
//       */
//      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//          // 把旧的值取出，如果更新了就把它存入数据库中去,如是第四行不是整数则返回
//          Object old_val = ((Object[]) rows.get(rowIndex))[columnIndex];
//          if (old_val.toString().equals(aValue.toString()))
//              return;
//          updateToDB(aValue, rowIndex, columnIndex);
//          ((Object[]) rows.get(rowIndex))[columnIndex] = aValue;
//          model.fireTableCellUpdated(rowIndex, columnIndex);// 更新表格中的数据
//      }
//
//      /**
//       * 有了这一行，就不用怕‘分数’这一列会被输入别的字符了。
//       */
//      public Class getColumnClass(int c) {
//          return getValueAt(0, c).getClass();
//      }
//
//      /** 把更新的内容存入数据库 */
//      private void updateToDB(Object value, int rowIndex, int columnIndex) {
//          int id = (Integer) ((Object[]) rows.get(rowIndex))[0];// 被发动那一行的ID
//          // 找出被改动的字段
//          String colName = "";
//          switch (columnIndex) {
//          case 1:
//              colName = "name";
//              break;
//          case 2:
//              colName = "no";
//              break;
//          case 3:
//              colName = "grade";
//              break;
//          default:
//              break;
//          }
//          String sql = "update test set " + colName + " = '" + value
//                  + "' where id = " + id;
//          System.out.println(sql);
//          try {
//              conn = db.connectToMysql("work", "root", "123");
//              stat = conn.createStatement();
//              stat.executeUpdate(sql);
//          } catch (SQLException e) {
//              System.err.println("更新失败,确保数据不要太长");
//              e.printStackTrace();
//          } finally {
//              if (conn != null)
//                  try {
//                      conn.close();
//                      if (stat != null)
//                          stat.close();
//                  } catch (SQLException e) {
//                      System.err.println("数据库关闭异常");
//                  }
//          }
//      }
//  }
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//      if (e.getActionCommand().equals("load")) {
//          try {
//              db = new DBConnection(DBConnection.MYSQL_DRIVER);// 加载数据库连接java的驱动
//              conn = db.connectToMysql("work", "root", "123");// 连接work数据库，登录名是root,密码是123
//              stat = conn.createStatement();
//              String sql;
//              if ("".equals(nameField.getText().trim())) {// 如果没有搜索条件
//                  sql = "select * from test";
//              } else {
//                  sql = "select * from test where name like '%"
//                          + nameField.getText() + "%'";
//              }
//              ResultSet set = stat.executeQuery(sql);// 执行查询，并返回查询的结果集
//              rows.clear();
//              // 此处需要注意的是，如果返回的结果集有5条，则执行了set.next()之后则把指针（虽然java没有指针概念，
//              // 不过我还是觉得这个形容词贴切一点）移动到第一条数据所在的地址。所以while循环里面取得的是第一条数据
//              while (set.next()) {
//                  Object[] obj = new Object[] { set.getInt("id"),
//                          set.getString("name"), set.getString("no"),
//                          set.getInt("grade") };
//                  rows.add(obj);
//              }
//              model.fireTableChanged(null);// 告诉表格数据已更新，让他刷新显示
//          } catch (ClassNotFoundException cnfe) {
//              System.err.println("数据库驱动加载错误，请确保添加正确的jar包");
//          } catch (SQLException sqle) {
//              System.err.println("数据库连接错误，请仔细检查用户名、密码、端口等是否有错");
//          } finally {// 记得关闭连接
//              try {
//                  if (conn != null)
//                      conn.close();
//                  if (stat != null)
//                      stat.close();
//              } catch (SQLException e1) {
//                  System.err.println("关闭连接失败");
//              }
//          }
//      }
//      if (e.getActionCommand().equals("clear")) {
//          rows.clear();
//          model.fireTableChanged(null);
//      }
//      if (e.getActionCommand().equals("exit")) {
//          System.exit(0);
//      }
//  }
//
//  public static void main(String arsgs[]) {
//      new TestTable();
//  }
//}
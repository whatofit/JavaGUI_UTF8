package com.myblog.components.table.db.dialogdb;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;
import java.sql.* ;
import java.util.Vector;
public class ShowRecord extends JDialog implements ActionListener  //负责显示记录的类
{
    JTable table ;
    Vector vecData = new Vector<>();
    Vector vecTitle = new Vector<>();
//    Object a[][] ;
//    Object name[]={"学号","姓名","出生日期","身高"} ;
    JButton showRecord ;
    Connection con ;
    Statement sql ;
    ResultSet rs ;

	ShowRecord(String title){
      setTitle(title) ;
      showRecord=new JButton("显示记录") ;
      showRecord.addActionListener(this) ;
      add(showRecord,BorderLayout.NORTH) ;
      setBounds(200,60,400,250) ;
    }
	
    public void createIfNotExists() {
    	String sqlCreate = "CREATE TABLE IF NOT EXISTS message (name,number,birthday,height);";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sqlCreate);
            if (ps.executeUpdate() != 0) {
            }
        } catch (SQLException ex) {
            System.out.println("插入数据发生异常:" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex1) {
                System.out.println("插入数据关闭语句异常:" + ex1.getMessage());
            }
        }
    }
    public void actionPerformed(ActionEvent e){
      try
      {
        //con=DriverManager.getConnection("jdbc:odbc:hello","","") ;
        con=DriverManager.getConnection("jdbc:sqlite:Students.db3");
        createIfNotExists();
        //sql=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY) ;
        sql=con.createStatement();
        rs=sql.executeQuery("SELECT * FROM message") ;
//        rs.last() ;
//        int lastNumber=rs.getRow() ;
//        a=new Object[lastNumber][4] ;
//        int k=0 ;
//        rs.beforeFirst() ;
        while(rs.next()){
//            a[k][0]=rs.getString(1) ;
//            a[k][1]=rs.getString(2) ;
//            a[k][2]=rs.getDate(3) ;
//            a[k][3]=rs.getString(4) ;
            Vector vecRow = new Vector<>();
            vecRow.add(rs.getString(1));
            vecRow.add(rs.getString(2));
            vecRow.add(rs.getString(3));
            vecRow.add(rs.getString(4));
            vecData.add(vecRow);
//            k++ ;
        }
        con.close() ;
      }
      catch (SQLException ee)
      {
          System.out.println(ee) ;
      }
      vecTitle.add("学号");
      vecTitle.add("姓名");
      vecTitle.add("出生日期");
      vecTitle.add("身高");
      table=new JTable(vecData,vecTitle) ;
      getContentPane().removeAll() ;
      add(showRecord,BorderLayout.NORTH) ;
      add(new JScrollPane(table),BorderLayout.CENTER) ;
      //validate() ;
    }
}
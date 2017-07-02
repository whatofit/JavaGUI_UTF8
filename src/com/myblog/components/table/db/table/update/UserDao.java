package com.myblog.components.table.db.table.update;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    
    //查询所有用户
    public List<User> queryAllUser(){
        String sql="select * from user";
        List<User> list=new ArrayList<User>();
        try {
             conn=DbUtils.getConnection();
             ps=conn.prepareStatement(sql);
             rs=ps.executeQuery();
             System.out.println(ps.toString());
             while(rs.next()){
                 User user=new User();
                 user.setId(rs.getInt(1));
                 user.setName(rs.getString(2));
                 user.setPassword(rs.getString(3));
                 user.setSex(rs.getString(4));
                 user.setAge(rs.getInt(5));
                 user.setAddress(rs.getString(6));
                
                 list.add(user);
             }
             
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
package com.myblog.components.table.db.dialogdb;

public class Example {
    public static void main(String args[]) {
        try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("" + e);
        }
        DatabaseWin win = new DatabaseWin();
    }
}
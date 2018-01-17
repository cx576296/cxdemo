package com.cx.DBUTIL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil
{
    private static final String URL = "jdbc:mysql://localhost/test";
    private static final String UNAME = "root";
    private static final String PWD = "root";
    private static Connection conn = null;

    public DBUtil()
    {
        try
        {
            // 1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库的连接
            conn = DriverManager.getConnection(URL, UNAME, PWD);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return conn;
    }

    public static void close(){
        try {
            if (conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
package com.cx.DAO;

import com.cx.DBENTITY.Entity;
import com.cx.DBUTIL.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao
{
    private  static Connection conn=null;
    private  static PreparedStatement ptmt=null;
    private  static ResultSet rs=null;

    public Dao() {
        DBUtil dbUtil=new DBUtil();
        conn=dbUtil.getConnection();
    }
    public static Dao getDaoInstance() {
        Dao dao=new Dao();
        return dao;
    }
    /**
     * 查询全部
     */
    public List<Entity> queryAll() throws SQLException {
        List<Entity> entityList = new ArrayList<Entity>();
        StringBuilder sb = new StringBuilder();
        sb.append("select id,name,pwd from account");
        ptmt = conn.prepareStatement(sb.toString());
        rs = ptmt.executeQuery();
        Entity entity = null;
        while (rs.next())
        {
            entity = new Entity();
            entity.setId(rs.getInt("id"));
            entity.setName(rs.getString("name"));
            entity.setPwd(rs.getString("pwd"));
            entityList.add(entity);
        }
        return entityList;
    }
    /**
     * 查询单个
     */
    public Entity queryById(Integer id) throws SQLException {
        Entity g = null;
        String sql = "" + " select * from account " + " where id=? ";
        ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        rs = ptmt.executeQuery();
        while (rs.next())
        {
            g = new Entity();
            g.setId(rs.getInt("id"));
            g.setName(rs.getString("name"));
            g.setPwd(rs.getString("pwd"));
        }
        return g;
    }
    /**
     * 添加
     */
    public void add(Entity g) throws SQLException {
        String sql = "insert into account(name,pwd) values(?,?)";
        ptmt = conn.prepareStatement(sql);
      //  ptmt.setInt(1, g.getId());
        ptmt.setString(1, g.getName());
        ptmt.setString(2, g.getPwd());
        ptmt.execute();
    }
    /**
     * 修改
     */
    public void updatebyid(Entity g, Integer id) throws SQLException {
        String sql = "update account set name=?,pwd=? where id=?";
        ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, g.getName());
        ptmt.setString(2, g.getPwd());
        ptmt.setInt(3, id);
        ptmt.execute();
    }
    /**
     * 删除
     */
    public void deletebyid(Integer id) throws SQLException {
        String sql = "delete from account where id=?";
        ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        ptmt.execute();
    }
    /**
     * 开启事务
     */
    public  void OpenCommit() throws SQLException {
        conn.setAutoCommit(false);
    }
    /**
     * 提交事务
     */
    public  void Commit() throws SQLException{
        conn.commit();
    }
    /**
     * 回滚
     */
    public  void Rollback() throws SQLException{
        conn.rollback();
    }
    /**
     * 关闭连接
     */
    public static void close()throws SQLException {
        if (ptmt!=null){
            ptmt.close();
        }
        if (rs!=null){
            rs.close();
        }
        if (conn!=null){
            conn.close();
        }
        DBUtil.close();
    }
}

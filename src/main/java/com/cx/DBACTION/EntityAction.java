package com.cx.DBACTION;

import com.cx.DAO.Dao;
import com.cx.DBENTITY.Entity;

import java.sql.SQLException;
import java.util.List;

public class EntityAction
{
    private Dao dao;

    public EntityAction(){
        dao=Dao.getDaoInstance();
    }
    /**
     * 新增
     */
    public void add(Entity goddess) throws Exception
    {
        dao.add(goddess);
    }
    /**
     * 查询
     */
    public Entity selectbyid(Integer id) throws SQLException
    {
        return dao.queryById(id);
    }

    /**
     * 修改
     */
    public void editbyid(Entity goddess,Integer id) throws Exception
    {
        dao.updatebyid(goddess,id);
    }

    /**
     * 删除
     */
    public void deletebyid(Integer id) throws SQLException
    {
        dao.deletebyid(id);
    }
    /**
     * 查询全部
     */
    public List<Entity> queryall() throws Exception
    {
        return dao.queryAll();
    }
    /**
     * 开启事务
     */
    public void opencommit()throws SQLException{
        dao.OpenCommit();
    }
    /**
     * 提交事务
     */
    public void commit()throws SQLException{
        dao.Commit();
    }
    /**
     * 回滚
     */
    public void rollback()throws SQLException{
        dao.Rollback();
    }
    /**
     * 关闭连接
     */
    public void close()throws SQLException {
        dao.close();
    }
}
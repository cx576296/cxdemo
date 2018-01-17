package com.cx.DBENTITY;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author AlanLee
 *
 */
public class Entity implements Serializable
{
    private static final long serialVersionUID = 100000L;

    /**
     * 唯一主键
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String pwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
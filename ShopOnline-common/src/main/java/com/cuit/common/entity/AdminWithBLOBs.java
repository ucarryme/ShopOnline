package com.cuit.common.entity;

import java.io.Serializable;

/**
 * @author zhoubin 
 * @since 1.0.0
 */
public class AdminWithBLOBs extends Admin implements Serializable {
    /**
     * 权限
     */
    private String navList;

    /**
     * todolist
     */
    private String todolist;

    /**
     * t_admin
     */
    private static final long serialVersionUID = 1L;

    public String getNavList() {
        return navList;
    }

    public void setNavList(String navList) {
        this.navList = navList == null ? null : navList.trim();
    }

    public String getTodolist() {
        return todolist;
    }

    public void setTodolist(String todolist) {
        this.todolist = todolist == null ? null : todolist.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", navList=").append(navList);
        sb.append(", todolist=").append(todolist);
        sb.append("]");
        return sb.toString();
    }
}
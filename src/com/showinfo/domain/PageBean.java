package com.showinfo.domain;

import java.util.List;
import java.util.Map;

public class PageBean {
    //第几页
    Integer pageCnt;
    //每页几行
    Integer row;
    //总行数
    Integer totalCnt;
    //总页数
    Integer totalPage;

    @Override
    public String toString() {
        return "PageBean{" +
                "pageCnt=" + pageCnt +
                ", row=" + row +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", users=" + users +
                ", conditions=" + conditions +
                '}';
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    //当前页查询到的用户
    List<User> users;
    //查询条件
    Map<String, String[]> conditions;

    public Integer getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(Integer pageCnt) {
        this.pageCnt = pageCnt;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<String, String[]> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, String[]> conditions) {
        this.conditions = conditions;
    }

}

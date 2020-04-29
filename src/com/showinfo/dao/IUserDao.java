package com.showinfo.dao;

import com.showinfo.domain.PageBean;
import com.showinfo.domain.User;

import java.util.List;

public interface IUserDao {
    //查询所有用户
    List<User> findAll();

    //根据用户名称和密码查询用户
    User findByUsernameAndPassword(String username, String password);

    //根据条件查询用户数量
    void findUserCntByConditions(PageBean pb);

    //根据条件查询用户
    void findUserByConditions(PageBean pb);

    //根据id查询用户
    User findUserById(String id);

    //保存用户
    void sava(User u);

    //根据ID删除用户
    void deleteUserById(String id);

    //新增用户
    void add(User user);
}

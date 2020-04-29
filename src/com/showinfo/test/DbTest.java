package com.showinfo.test;

import com.showinfo.dao.impl.UserDao;
import com.showinfo.domain.PageBean;
import com.showinfo.domain.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbTest {

    UserDao userDao = new UserDao();

    @Test
    public void findAll(){
        List<User> users = userDao.findAll();
        for(User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void findByUsernameAndPassword(){
        User u = userDao.findByUsernameAndPassword("one", "134");
        if(u != null) {
            System.out.println(u);
        } else {
            System.out.println("没有找到或者有重复项");
        }
    }

    @Test
    public void findUserCntByConditions(){
        PageBean pb = new PageBean();
        pb.setRow(5);
        pb.setPageCnt(1);

        Map<String, String[]> map = new HashMap<>();
        String[] ss = new String[1];
        ss[0] = "广州";
        map.put("address", ss);
        pb.setConditions(map);

        userDao.findUserCntByConditions(pb);
        System.out.println(pb.getTotalCnt());
    }

    @Test
    public void findUserByConditions(){
        PageBean pb = new PageBean();
        pb.setRow(5);
        pb.setPageCnt(1);

        Map<String, String[]> map = new HashMap<>();
        String[] ss = new String[1];
        ss[0] = "广州";
        map.put("address", ss);
        pb.setConditions(map);

        userDao.findUserByConditions(pb);
        System.out.println(pb.getUsers());
    }
}

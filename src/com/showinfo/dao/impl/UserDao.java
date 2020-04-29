package com.showinfo.dao.impl;

import com.showinfo.dao.IUserDao;
import com.showinfo.domain.PageBean;
import com.showinfo.domain.User;
import com.showinfo.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDao implements IUserDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDs());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        try{
            User u = jdbcTemplate.queryForObject(sql,  new BeanPropertyRowMapper<>(User.class), username, password);
            return u;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void findUserCntByConditions(PageBean pb){
        Integer row = 10;
        Integer page = 1;
        Integer tmp = pb.getRow();
        if(tmp != null && tmp != 0) {
            row = tmp;
        }
        tmp = pb.getPageCnt();
        if(tmp != null && tmp !=0){
            page = tmp;
        }

        StringBuffer sb = new StringBuffer("select count(*) from user where 1=1 ");

        List<Object> params = new ArrayList<>();
        Map<String, String[]> cMap = pb.getConditions();
        if(cMap != null && !cMap.isEmpty()){
            Set<String> ks = cMap.keySet();
            String v = null;
            for(String s: ks) {
                v = cMap.get(s)[0];
                if(v != null && !"".equalsIgnoreCase(v)){
                if("age".equalsIgnoreCase(s)) {
                    sb.append(" and " + s + " = ? ");
                    params.add(v);
                } else {
                    sb.append(" and " + s + " like ? ");
                    params.add("%" + v +"%");
                }
            }}
        }
        System.out.println(sb.toString());
        System.out.println(params);
        Integer total = jdbcTemplate.queryForObject(sb.toString(), Integer.class, params.toArray());
        pb.setTotalCnt(total);
        System.out.println(total);
    }

    @Override
    public void findUserByConditions(PageBean pb) {
        Integer row = 10;
        Integer page = 1;
        Integer tmp = pb.getRow();
        if(tmp != null && tmp != 0) {
            row = tmp;
        }
        tmp = pb.getPageCnt();
        if(tmp != null && tmp !=0){
            page = tmp;
        }

        findUserCntByConditions(pb);

        StringBuffer sb = new StringBuffer("select * from user where 1=1 ");

        List<Object> params = new ArrayList<>();
        Map<String, String[]> cMap = pb.getConditions();
        if(cMap != null && !cMap.isEmpty()){
            Set<String> ks = cMap.keySet();
            String v = null;
            for(String s: ks) {
                v = cMap.get(s)[0];
                if(v != null && !"".equalsIgnoreCase(v)){
                    if("age".equalsIgnoreCase(s)) {
                        sb.append(" and " + s + " = ? ");
                        params.add(v);
                    } else {
                        sb.append(" and " + s + " like ? ");
                        params.add("%" + v +"%");
                    }
                }}
        }
        sb.append(" limit ?,?");
        params.add((page - 1) < 0?0:(page - 1) * row);
        params.add(row);
        System.out.println(sb.toString());
        System.out.println(params);
        List<User> users = new ArrayList<>();
        users = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
        pb.setUsers(users);
    }

    @Override
    public User findUserById(String id) {
        User user = null;
        String sql = "select * from user where id = ?";
        user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        return user;
    }

    @Override
    public void sava(User user) {
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public void deleteUserById(String id) {
        //1.定义sql
        String sql = "delete from user where id = ?";
        //2.执行sql
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void add(User user) {
        //1.定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?,?,null)";
        //2.执行sql
        jdbcTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(),
                user.getUsername());
    }
}

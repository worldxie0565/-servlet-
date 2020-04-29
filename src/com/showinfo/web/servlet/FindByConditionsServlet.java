package com.showinfo.web.servlet;

import com.showinfo.dao.impl.UserDao;
import com.showinfo.domain.PageBean;
import com.showinfo.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/findByConditionsServlet")
public class FindByConditionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ssUserName = (String)request.getSession().getAttribute("username");
        if(ssUserName == null || "".equalsIgnoreCase(ssUserName)){
            request.setAttribute("local_msg", "您还未登录，请登录");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String curPage = request.getParameter("curPage");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        PageBean pb = new PageBean();
        String[] s = new String[1];
        s[0] = name;
        Map<String, String[]> maps = new HashMap<>();
        maps.put("name", s);
        s = new String[1];
        s[0] = age;
        maps.put("age", s);
        s = new String[1];
        s[0] = gender;
        maps.put("gender", s);
        s = new String[1];
        s[0] = address;
        maps.put("address", s);
        pb.setConditions(maps);

        if(curPage != null && !"".equalsIgnoreCase(curPage)) {
            pb.setPageCnt(Integer.parseInt(curPage));

        } else {
            pb.setPageCnt(1);

        }
        Integer tmp = pb.getRow();
        if(tmp == null || tmp < 0){
            pb.setRow(10);
        }

        UserDao userDao = new UserDao();
        userDao.findUserByConditions(pb);


        tmp = (pb.getTotalCnt() % pb.getRow()) == 0 ? pb.getTotalCnt()/pb.getRow():pb.getTotalCnt()/pb.getRow()+1;
        pb.setTotalPage(tmp);

        request.setAttribute("pb", pb);

        request.getRequestDispatcher("/pages/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

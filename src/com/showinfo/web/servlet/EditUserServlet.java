package com.showinfo.web.servlet;

import com.showinfo.dao.impl.UserDao;
import com.showinfo.domain.User;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/editUserServlet")
public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        UserDao userDao = new UserDao();
        User u = null;
        String id = request.getParameter("id");
        System.out.println("id:" + id);
        u = userDao.findUserById(id);
        String name = request.getParameter("name");
        u.setName(name);
        String address = request.getParameter("address");
        u.setAddress(address);

        userDao.sava(u);

        response.sendRedirect(request.getContextPath() + "/findByConditionsServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

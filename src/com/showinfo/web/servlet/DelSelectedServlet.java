package com.showinfo.web.servlet;

import com.showinfo.dao.impl.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] cks = request.getParameterValues("uid");
        UserDao userDao = new UserDao();
        if(cks != null && cks.length > 0){
            for(String s:cks){
                userDao.deleteUserById(s);
            }
        }

        response.sendRedirect(request.getContextPath() + "/findByConditionsServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

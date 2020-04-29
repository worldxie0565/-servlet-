package com.showinfo.web.servlet;

import com.showinfo.dao.impl.UserDao;
import com.showinfo.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String vcode = request.getParameter("vcode");

        HttpSession ss = request.getSession();
        String checkCode = (String)ss.getAttribute("vcode");
        ss.removeAttribute("vcode");

        System.out.println(vcode + ":" + checkCode);

        if(vcode != null && vcode.length() != 0) {
            if(vcode.equalsIgnoreCase(checkCode)) {
                //验证码正确，用户名和密码
                UserDao userDao = new UserDao();
                User u = userDao.findByUsernameAndPassword(username, password);
                if(u != null) {
                    request.getSession().setAttribute("username", u.getUsername());

                    System.out.println("=====================");
                    System.out.println(u.getUsername());
                    response.sendRedirect( request.getContextPath() + "/findByConditionsServlet");
                } else {
                    request.setAttribute("local_msg", "用户名或者密码错误");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } else {
                //验证码错误
                request.setAttribute("local_msg", "验证码错误");
                System.out.println("验证码错误");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            //验证码为空
            request.setAttribute("local_msg", "验证码为空");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

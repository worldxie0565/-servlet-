package com.showinfo.web.servlet;

import com.showinfo.utils.ValidateCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/validateCodeServlet")
public class ValidateCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置响应头通知浏览器以图片的形式打开
        response.setContentType("image/jpeg;charset=utf-8");
        //设置响应头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");

        BufferedImage img = ValidateCodeUtil.getValidateCodeImage(100, 50, 5);
        String vcode = ValidateCodeUtil.getValidateCode();
        request.getSession().setAttribute("vcode", vcode);
        System.out.println(vcode);
        ImageIO.write(img, "png", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

package com.showinfo.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hreq = (HttpServletRequest)req;
        String uri = hreq.getRequestURI();
        if(uri.contains("/index.jsp") || uri.contains("/loginServlet") ||
                uri.contains("/validateCodeServlet") || uri.contains("/css/") ||
                uri.contains("/js/") || uri.contains("/fonts/")){
            chain.doFilter(req, resp);
        } else {
            String ssUserName = (String) hreq.getSession().getAttribute("username");
            if(ssUserName == null || "".equalsIgnoreCase(ssUserName)){
                req.setAttribute("local_msg", "您还未登录，请登录");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}

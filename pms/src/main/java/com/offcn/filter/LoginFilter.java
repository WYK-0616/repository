package com.offcn.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null || req.getRequestURI().contains("login")){
            chain.doFilter(req,res);
        } else {
            res.sendRedirect(req.getContextPath()+"/login.jsp");
        }
    }

    public void destroy() {

    }
}

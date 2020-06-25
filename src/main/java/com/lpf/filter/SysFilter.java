package com.lpf.filter;

import com.lpf.pojo.Admin;
import com.lpf.pojo.Student;
import com.lpf.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String identity = (String) request.getSession().getAttribute(Constants.IDENTITY_FLAG);
        Student stu = (Student) request.getSession().getAttribute(Constants.STU_SESSION);
        Admin adm = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);

        if (identity == null) {
            if (stu == null || adm == null) { //如果不是从登陆界面进来的。
                response.sendRedirect("/error.jsp");
            }
        } else if (identity.equals("student")) {
            if (stu == null) {//已经被移除
                response.sendRedirect("/error.jsp");
            }
        } else if (identity.equals("admin")) {
            if (adm == null) {
                response.sendRedirect("/error.jsp");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

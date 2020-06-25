package com.lpf.servlet;

import com.lpf.pojo.Admin;
import com.lpf.pojo.Student;
import com.lpf.service.admin.AdminService;
import com.lpf.service.admin.AdminServiceImpl;
import com.lpf.service.student.StuService;
import com.lpf.service.student.StuServiceImpl;
import com.lpf.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identity=req.getParameter("identity");
        if (identity==null){
            req.setAttribute("error", "请选择学生或管理员登录");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else if (identity.equals("student")){
            stLogin(req,resp);
        }else if (identity.equals("admin")){
            adLogin(req,resp);
        }
    }

    public void adLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("username");
        String password = req.getParameter("password");
        //调用业务层
        AdminService adminService = new AdminServiceImpl();
        Admin admin = adminService.login(uname, password);

        //查到这个人了
        if (admin != null) {
            req.getSession().setAttribute(Constants.ADMIN_SESSION, admin);
            req.getSession().setAttribute(Constants.IDENTITY_FLAG, "admin");
            resp.sendRedirect("/jsp/index.jsp");
        } else {
            req.setAttribute("error", "用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    public void stLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("username");
        String password = req.getParameter("password");
        int username = 0;
        try {
            username = Integer.parseInt(uname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用业务层
        StuService stuService = new StuServiceImpl();
        Student student = stuService.login(username, password);

        //查到这个人了
        if (student != null) {
            req.getSession().setAttribute(Constants.STU_SESSION, student);
            req.getSession().setAttribute(Constants.IDENTITY_FLAG, "student");
            resp.sendRedirect("/jsp/stuview/index.jsp");
        } else {
            req.setAttribute("error", "用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}

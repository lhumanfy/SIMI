package com.lpf.servlet.student;

import com.lpf.pojo.Student;
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
            resp.sendRedirect("/jsp/index.jsp");
        } else {
            req.setAttribute("error", "用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}

package com.lpf.servlet;

import com.lpf.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //移除用户的session
        req.getSession().removeAttribute(Constants.STU_SESSION);
        req.getSession().removeAttribute(Constants.ADMIN_SESSION);
        req.getSession().removeAttribute(Constants.IDENTITY_FLAG);
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }
}

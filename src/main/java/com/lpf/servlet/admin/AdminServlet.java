package com.lpf.servlet.admin;

import com.alibaba.fastjson.JSONArray;
import com.lpf.pojo.Admin;
import com.lpf.pojo.Student;
import com.lpf.service.admin.AdminService;
import com.lpf.service.admin.AdminServiceImpl;
import com.lpf.service.student.StuService;
import com.lpf.service.student.StuServiceImpl;
import com.lpf.util.Constants;
import com.microsoft.sqlserver.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd") && method != null) {
            this.updatePwd(req, resp);
        }else if (method.equals("pwdajax") && method != null) {
            this.checkPwd(req, resp);
        }
    }

    //更改管理员密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(Constants.ADMIN_SESSION);
        String pwd = req.getParameter("newPwd");

        boolean flag = false;
        if (attribute != null && !StringUtils.isEmpty(pwd)) {
            AdminService adminService=new AdminServiceImpl();
            System.out.println("-----------"+((Admin) attribute).getAccount()+pwd);
            flag = adminService.changePwd(((Admin) attribute).getAccount(), pwd);
            if (flag) {
                req.setAttribute(Constants.MESSAGE, "修改密码成功,请重新登录");
                //密码更改成功，移除session
                req.getSession().removeAttribute(Constants.ADMIN_SESSION);
            } else {
                req.setAttribute(Constants.MESSAGE, "修改密码失败");
            }
        } else {
            req.setAttribute(Constants.MESSAGE, "新密码输入错误");
        }
        req.getRequestDispatcher(req.getContextPath() + "jsp/changepwd.jsp").forward(req, resp);
    }

    //验证旧密码
    public void checkPwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(Constants.ADMIN_SESSION);
        String oldPwd = req.getParameter("oldPwd");

        Map<String, String> message = new HashMap<String, String>();
        if (attribute == null) {
            message.put("result", "sessionerror");
        } else if (StringUtils.isEmpty(oldPwd)) {
            message.put("result", "empty");
        } else {
            String sessionPwd = ((Admin) attribute).getPwd();
            if (oldPwd.equals(sessionPwd)) {
                message.put("result", "true");
            } else {
                message.put("result", "false");
            }
        }
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

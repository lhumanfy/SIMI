package com.lpf.dao.admin;

import com.lpf.pojo.Admin;
import com.lpf.pojo.Student;

import java.sql.Connection;

public interface AdminDao {
    //验证登陆账号密码
    public Admin getLoginAccount(Connection connection, String account, String pwd);
    //修改账号密码
    public int changePwd(Connection connection,String account,String pwd);
}

package com.lpf.service.admin;

import com.lpf.pojo.Admin;

public interface AdminService {
    //得到管理员用户实例
    public Admin login(String account,String pwd);
    //更改管理员密码
    public Boolean changePwd(String account,String pwd);
}

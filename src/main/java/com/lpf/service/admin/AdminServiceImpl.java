package com.lpf.service.admin;

import com.lpf.dao.BaseDao;
import com.lpf.dao.admin.AdminDao;
import com.lpf.dao.admin.AdminDaoImpl;
import com.lpf.pojo.Admin;

import java.sql.Connection;

public class AdminServiceImpl implements AdminService {
    //业务层首先调用Dao层
    private AdminDao adminDao;

    public AdminServiceImpl() {
        adminDao = new AdminDaoImpl();
    }

    @Override
    public Admin login(String account, String pwd) {
        Connection connection = null;
        Admin admin = null;

        try {
            connection = BaseDao.getConnection();
            admin = adminDao.getLoginAccount(connection, account, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return admin;
    }

    @Override
    public Boolean changePwd(String account, String pwd) {
        Connection connection = null;
        Boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            int i = adminDao.changePwd(connection, account, pwd);
            if (i > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return flag;
    }
}

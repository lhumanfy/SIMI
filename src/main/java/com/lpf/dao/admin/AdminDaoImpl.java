package com.lpf.dao.admin;

import com.lpf.dao.BaseDao;
import com.lpf.pojo.Admin;
import com.lpf.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao{
    @Override
    public Admin getLoginAccount(Connection connection, String account, String pwd) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Admin admin = null;

        if (connection != null) {
            String sql = "select * from Admin_tb where accnum=? and adpassword=?";
            Object[] params = {account, pwd};
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql, params);
                if (resultSet.next()) {
                    admin = new Admin();
                    admin.setAccount(resultSet.getString("accnum"));
                    admin.setPwd(resultSet.getString("adpassword"));
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return admin;
    }

    @Override
    public int changePwd(Connection connection, String account, String pwd) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        if (connection != null) {
            String sql = "update Admin_tb set adpassword=? where accnum=?";
            Object[] params = {pwd, account};
            result = BaseDao.update(connection, preparedStatement, sql, params);
            BaseDao.closeDb(connection, preparedStatement, null);
        }
        return result;
    }
}

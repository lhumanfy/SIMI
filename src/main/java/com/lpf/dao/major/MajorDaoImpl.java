package com.lpf.dao.major;

import com.lpf.dao.BaseDao;
import com.lpf.pojo.Major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorDaoImpl implements MajorDao {

    //查询专业信息
    @Override
    public List<Major> getMajorInfo(Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Major> majorList = new ArrayList<>();

        if (connection != null) {
            String sql = "select * from Major";
            Object[] params = {};
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql, params);
                while (resultSet.next()) {
                    Major major = new Major();
                    major.setMajorNo(resultSet.getInt(1));
                    major.setMajorName(resultSet.getString(2));
                    majorList.add(major);
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return majorList;
    }
}

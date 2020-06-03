package com.lpf.dao.faculty;

import com.lpf.dao.BaseDao;
import com.lpf.pojo.Faculty;
import com.lpf.pojo.Major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyDaoImpl  implements FacultyDao{
    @Override
    public List<Faculty> getFacultyInfo(Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Faculty> facultyList = new ArrayList<>();

        if (connection != null) {
            String sql = "select * from Faculty";
            Object[] params = {};
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql, params);
                while (resultSet.next()) {
                    Faculty faculty=new Faculty();
                    faculty.setFacultyNo(resultSet.getInt("Facultyno"));
                    faculty.setFacultyName(resultSet.getString("Facultyname"));
                    facultyList.add(faculty);
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return facultyList;
    }
}

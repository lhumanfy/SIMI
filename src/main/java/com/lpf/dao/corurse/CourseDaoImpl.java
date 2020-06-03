package com.lpf.dao.corurse;

import com.lpf.dao.BaseDao;
import com.lpf.pojo.Course;
import com.lpf.pojo.Major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public List<Course> getCourseInfo(Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Course> courseList = new ArrayList<>();

        if (connection != null) {
            String sql = "select * from CourseInfo";
            Object[] params = {};
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql, params);
                while (resultSet.next()) {
                    Course course = new Course();
                    course.setCouNumber(resultSet.getInt("Counumber"));
                    course.setCouName(resultSet.getString("Couname"));
                    course.setCredits(resultSet.getFloat("Credits"));
                    course.setCouTime(resultSet.getFloat("Coutime"));
                    courseList.add(course);
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courseList;
    }
}

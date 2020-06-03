package com.lpf.dao.corurse;

import com.lpf.pojo.Course;

import java.sql.Connection;
import java.util.List;

public interface CourseDao {
    //获取课程列表
    public List<Course> getCourseInfo(Connection connection);
}

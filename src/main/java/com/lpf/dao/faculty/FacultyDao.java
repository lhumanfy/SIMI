package com.lpf.dao.faculty;

import com.lpf.pojo.Faculty;
import com.lpf.pojo.Major;

import java.sql.Connection;
import java.util.List;

public interface FacultyDao {
    //查询学院信息
    public List<Faculty> getFacultyInfo(Connection connection);
}

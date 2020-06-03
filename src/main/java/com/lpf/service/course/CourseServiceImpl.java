package com.lpf.service.course;

import com.lpf.dao.BaseDao;
import com.lpf.dao.corurse.CourseDao;
import com.lpf.dao.corurse.CourseDaoImpl;
import com.lpf.pojo.Course;
import com.lpf.pojo.Faculty;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;
    public CourseServiceImpl(){
        courseDao=new CourseDaoImpl();
    }
    @Override
    public List<Course> getCourseList() {
        Connection connection=null;
        List<Course> courseList = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            courseList=courseDao.getCourseInfo(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return courseList;
    }
}

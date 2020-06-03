package com.lpf.service.faculty;

import com.lpf.dao.BaseDao;
import com.lpf.dao.faculty.FacultyDao;
import com.lpf.dao.faculty.FacultyDaoImpl;
import com.lpf.pojo.Faculty;
import com.lpf.pojo.Major;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class FacultyServiceImpl implements FacultyService {
    private FacultyDao facultyDao;

    public FacultyServiceImpl() {
        facultyDao = new FacultyDaoImpl();
    }

    @Override
    public List<Faculty> getFacultyList() {
        Connection connection = null;
        List<Faculty> facultyList = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            facultyList = facultyDao.getFacultyInfo(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return facultyList;
    }
}

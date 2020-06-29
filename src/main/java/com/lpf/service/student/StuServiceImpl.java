package com.lpf.service.student;

import com.lpf.dao.BaseDao;
import com.lpf.dao.student.StuDao;
import com.lpf.dao.student.StuDaoImpl;
import com.lpf.pojo.Major;
import com.lpf.pojo.Score;
import com.lpf.pojo.Student;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class StuServiceImpl implements StuService {

    //这里业务层用来调用dao层
    private StuDao stuDao;

    public StuServiceImpl() {
        stuDao = new StuDaoImpl();
    }

    @Override
    public Student login(int userName, String passWord) {
        Connection connection = null;
        Student student = null;
        try {
            connection = BaseDao.getConnection();
            student = stuDao.getLoginUname(connection, userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return student;
    }

    @Override
    public Boolean chage(int userName, String passWord) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (stuDao.changePwd(connection, userName, passWord) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return flag;
    }

    @Override
    public List<Score> getScoreList(int userName) {
        Connection connection = null;
        List<Score> scoreList = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            scoreList = stuDao.getUserScore(connection, userName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return scoreList;
    }

    @Override
    public int getStuListCount(Integer userName, int Majorno, int Facultyno) {
        Connection connection = null;
        int count=0;
        try {
            connection = BaseDao.getConnection();
            count = stuDao.getStuInfoCount(connection,userName,Majorno,Facultyno);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return count;
    }

    @Override
    public List<Student> getStuList(Integer userName, int Majorno, int Facultyno, int curPage, int pageSize) {
        Connection connection = null;
        List<Student> stuList = new ArrayList<>();
        try {
            connection = BaseDao.getConnection();
            stuList = stuDao.getStuInfo(connection, userName, Majorno, Facultyno, curPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return stuList;
    }

    @Override
    public Boolean upStudent(Student student) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            int i = stuDao.upStuInfo(connection, student);
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

    @Override
    public Boolean delStuInfo(int sno) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            int i = stuDao.delStuInfo(connection, sno);
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

    @Override
    public Boolean addStuInfo(Student student) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            int i = stuDao.addStuInfo(connection, student);
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

    @Override
    public Boolean exam(int Sno) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            flag = stuDao.examStuSno(connection, Sno);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return flag;
    }

    @Override
    public Student getSigStuInfo(int sNo) {
        Connection connection = null;
        Student student = null;
        try {
            connection = BaseDao.getConnection();
            student = stuDao.getSigStuInfo(connection, sNo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeDb(connection, null, null);
        }
        return student;
    }

    @Test
    public void test() {
        StuServiceImpl stuService = new StuServiceImpl();
        List<Student> stuList = stuService.getStuList(null, 0, 0,1,10);
        for (Student student : stuList) {
            System.out.println(student.getsNo());
        }
    }


}

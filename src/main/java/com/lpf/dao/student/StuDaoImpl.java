package com.lpf.dao.student;

import com.lpf.dao.BaseDao;
import com.lpf.pojo.Major;
import com.lpf.pojo.Score;
import com.lpf.pojo.Student;
import com.microsoft.sqlserver.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StuDaoImpl implements StuDao {
    @Override
    public Student getLoginUname(Connection connection, int Sno, String passWord) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;

        if (connection != null) {
            String sql = "select * from StudentInfo where Sno=? and password=?";
            Object[] params = {Sno, passWord};
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql, params);
                if (resultSet.next()) {
                    student = new Student();
                    student.setsNo(resultSet.getInt("Sno"));
                    student.setsName(resultSet.getString("Sname"));
                    student.setsSex(resultSet.getString("Ssex"));
                    student.setsBir(resultSet.getString("Sbir"));
                    student.setsClass(resultSet.getString("Sclass"));
                    student.setMajorNo(resultSet.getInt("Majorno"));
                    student.setFacltyNo(resultSet.getInt("Facultyno"));
                    student.setPassWord(resultSet.getString("password"));
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    //修改密码
    @Override
    public int changePwd(Connection connection, int Sno, String passWord) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        if (connection != null) {
            String sql = "update StudentInfo set password=? where Sno=?";
            Object[] params = {passWord, Sno};
            result = BaseDao.update(connection, preparedStatement, sql, params);
            BaseDao.closeDb(connection, preparedStatement, null);
        }
        return result;
    }

    //查询用户成绩
    @Override
    public List<Score> getUserScore(Connection connection, int Sno) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Score> scoreList = new ArrayList<>();
        if (connection != null) {
            String sql = "select * from Score sco inner join StudentInfo SI" +
                    " on sco.Sno = SI.Sno\n" +
                    "where SI.Sno=?";
            Object[] params = {Sno};
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql, params);
                while (resultSet.next()) {
                    Score score = new Score();
                    score.setsNo(resultSet.getInt("Sno"));
                    score.setCouNum(resultSet.getInt("Counumber"));
                    score.setCouName(resultSet.getString("Couname"));
                    score.setScores(resultSet.getString("Scores"));
                    scoreList.add(score);
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return scoreList;
    }

    //查询用户信息
    @Override
    public List<Student> getStuInfo(Connection connection, Integer Sno, int Majorno, int Facultyno) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> stuList = new ArrayList<>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from StudentInfo stu,Faculty fac ,Major M where stu.Facultyno = fac.Facultyno and" +
                    "  stu.Majorno = M.Majorno ");
            List<Object> list = new ArrayList<>();
            if (Sno != null) {
                sql.append(" and cast(Sno as CHAR) like ?");
                list.add("%" + Sno + "%");
            }
            if (Majorno > 0) {
                sql.append(" and stu.Majorno=?");
                list.add(Majorno);
            }
            if (Facultyno > 0) {
                sql.append(" and stu.Facultyno=?");
                list.add(Facultyno);
            }

            Object[] params = list.toArray();
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql.toString(), params);
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setsNo(resultSet.getInt("Sno"));
                    student.setsName(resultSet.getString("Sname"));
                    student.setsSex(resultSet.getString("Ssex"));
                    student.setsBir(resultSet.getString("Sbir"));
                    student.setsClass(resultSet.getString("Sclass"));
                    student.setMajorName(resultSet.getString("Majorname"));
                    student.setFacultyName(resultSet.getString("Facultyname"));
                    student.setMajorNo(resultSet.getInt("Majorno"));
                    student.setFacltyNo(resultSet.getInt("Facultyno"));
                    student.setPassWord(resultSet.getString("password"));
                    stuList.add(student);
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stuList;
    }

    @Override
    public int upStuInfo(Connection connection, Student student) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        if (connection != null) {
            String sql = "update StudentInfo set Sname=?,Ssex=?,Sbir=?,Sclass=?,Majorno=?,Facultyno=?" +
                    " where Sno=?";
            Object[] params = {student.getsName(), student.getsSex(), student.getsBir(), student.getsClass()
                    , student.getMajorNo(), student.getFacltyNo(), student.getsNo()};
            result = BaseDao.update(connection, preparedStatement, sql, params);
            BaseDao.closeDb(connection, preparedStatement, null);
        }
        return result;
    }

    @Override
    public int delStuInfo(Connection connection, int Sno) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        if (connection != null) {
            String sql = "delete StudentInfo where Sno=?";
            Object[] params = {Sno};
            result = BaseDao.update(connection, preparedStatement, sql, params);
            BaseDao.closeDb(connection, preparedStatement, null);
        }
        return result;
    }

    //添加新用户
    @Override
    public int addStuInfo(Connection connection, Student student) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        if (connection != null) {
            String sql = "insert into StudentInfo values (?,?,?,?,?,?,?,?)";
            Object[] params = {student.getsNo(), student.getsName(), student.getsSex(), student.getsBir(), student.getsClass()
                    , student.getMajorNo(), student.getFacltyNo(), student.getPassWord()};
            result = BaseDao.update(connection, preparedStatement, sql, params);
            BaseDao.closeDb(connection, preparedStatement, null);
        }
        return result;
    }

    //验证学号存在
    @Override
    public Boolean examStuSno(Connection connection, int Sno) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flage = false;

        if (connection != null) {
            String sql = "select * from StudentInfo where Sno=?";
            Object[] params = {Sno};

            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql, params);
                if (resultSet.next()) {
                    flage = true;
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flage;
    }

    @Override
    public Student getSigStuInfo(Connection connection, int Sno) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;

        if (connection != null) {
            String sql = "select * from StudentInfo where Sno=?";
            Object[] params = {Sno};
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql, params);
                if (resultSet.next()) {
                    student = new Student();
                    student.setsNo(resultSet.getInt("Sno"));
                    student.setsName(resultSet.getString("Sname"));
                    student.setsSex(resultSet.getString("Ssex"));
                    student.setsBir(resultSet.getString("Sbir"));
                    student.setsClass(resultSet.getString("Sclass"));
                    student.setMajorNo(resultSet.getInt("Majorno"));
                    student.setFacltyNo(resultSet.getInt("Facultyno"));
                    student.setPassWord(resultSet.getString("password"));
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }
}


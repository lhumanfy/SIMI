package com.lpf.dao.student;

import com.lpf.pojo.Major;
import com.lpf.pojo.Score;
import com.lpf.pojo.Student;
import com.lpf.util.Constants;

import java.sql.Connection;
import java.util.List;

public interface StuDao {
    //得到登录用户
    public Student getLoginUname(Connection connection, int Sno, String passWord);
    //修改登录密码
    public int changePwd(Connection connection,int Sno,String passWord);
    //查询登录用户成绩
    public List<Score> getUserScore(Connection connection,int Sno);
    //查询所有用户信息
    public List<Student> getStuInfo(Connection connection,Integer Sno,int Majorno,int Facultyno,int curPage,int pageSize);
    //获取用户个数
    public int getStuInfoCount(Connection connection,Integer Sno,int Majorno,int Facultyno);
    //更改用户信息
    public int upStuInfo(Connection connection,Student student);
    //删除用户
    public int delStuInfo(Connection connection,int Sno);
    //添加用户
    public int addStuInfo(Connection connection,Student student);
    //验证当前学号存在
    public Boolean examStuSno(Connection connection,int Sno);
    //根据学号查询用户名
    public Student getSigStuInfo(Connection connection,int Sno);
}

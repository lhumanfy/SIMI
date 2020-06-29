package com.lpf.service.student;

import com.lpf.pojo.Major;
import com.lpf.pojo.Score;
import com.lpf.pojo.Student;

import java.util.List;

public interface StuService {
    //用户登录
    public Student login(int userName,String passWord);
    //修改当前密码
    public Boolean chage(int userName,String passWord);
    //得到查询成绩集合
    public List<Score> getScoreList(int userName);
    //查询全部用户信息
    public List<Student> getStuList(Integer userName,int Majorno,int Facultyno, int curPage, int pageSize);
    //查询全部用户个数
    public int getStuListCount(Integer userName,int Majorno,int Facultyno);
    //更改用户信息
    public Boolean upStudent(Student student);
    //删除信息
    public Boolean delStuInfo(int sno);
    //插入信息
    public Boolean addStuInfo(Student student);
    //验证当前学号是否存在
    public Boolean exam(int Sno);
    //根据学号查询用户信息
    public Student getSigStuInfo(int sNo);
}

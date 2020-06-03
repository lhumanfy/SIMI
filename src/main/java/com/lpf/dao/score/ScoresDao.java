package com.lpf.dao.score;

import com.lpf.pojo.Score;

import java.sql.Connection;
import java.util.List;

public interface ScoresDao {
    //获取成绩
    public List<Score> getUserScore(Connection connection, Integer Sno,int Counumber);
    //更改成绩
    public int upScoreInfo(Connection connection,Score score);
    //删除成绩
    public int delScoreInfo(Connection connection,int Sno,int Counumber);
    //添加成绩
    public int addScoreInfo(Connection connection,Score score);
    //检查某学生某课程成绩是否存在
    public Boolean examSno(Connection connection,int sNo,int couNumber);
}

package com.lpf.service.score;

import com.lpf.pojo.Score;

import java.util.List;

public interface ScoreService {
    //得到成绩信息
    public List<Score> getScoreList(Integer Sno,int Counumber,int curPage,int pageSize);
    //得到成绩信息数量
    public int getScoreListCount(Integer Sno,int Counumber,int curPage,int pageSize);
    //更改成绩
    public Boolean upSores(Score score);
    //删除成绩
    public Boolean delScores(int Sno,int Counnumber);
    //添加成绩
    public Boolean addScores(Score score);
    //检查某用户名的某门课程是否存在
    public Boolean exam(int sNo,int couNumber);
}

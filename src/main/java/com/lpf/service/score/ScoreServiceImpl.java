package com.lpf.service.score;

import com.lpf.dao.BaseDao;
import com.lpf.dao.score.ScoresDao;
import com.lpf.dao.score.ScoresDaoImpl;
import com.lpf.pojo.Score;
import com.lpf.pojo.Student;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ScoreServiceImpl implements ScoreService{

    private ScoresDao scoresDao;
    public ScoreServiceImpl(){
        scoresDao=new ScoresDaoImpl();
    }
    @Override
    public List<Score> getScoreList(Integer Sno, int Counumber) {
        Connection connection=null;
        List<Score> scoreList=new ArrayList<>();
        try {
            connection= BaseDao.getConnection();
            scoreList=scoresDao.getUserScore(connection,Sno,Counumber);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeDb(connection,null,null);
        }
        return scoreList;
    }

    @Override
    public Boolean upSores(Score score) {
        Connection connection=null;
        boolean flag=false;
        try {
            connection=BaseDao.getConnection();
            int i = scoresDao.upScoreInfo(connection,score);
            if (i>0){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeDb(connection,null,null);
        }
        return flag;
    }

    @Override
    public Boolean delScores(int Sno, int Counnumber) {
        Connection connection=null;
        boolean flag=false;
        try {
            connection=BaseDao.getConnection();
            int i = scoresDao.delScoreInfo(connection,Sno,Counnumber);
            if (i>0){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeDb(connection,null,null);
        }
        return flag;
    }

    @Override
    public Boolean addScores(Score score) {
        Connection connection=null;
        boolean flag=false;
        try {
            connection=BaseDao.getConnection();
            int i = scoresDao.addScoreInfo(connection,score);
            if (i>0){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeDb(connection,null,null);
        }
        return flag;
    }

    @Override
    public Boolean exam(int sNo, int couNumber) {
        Connection connection=null;
        boolean flag=false;
        try {
            connection=BaseDao.getConnection();
            if (scoresDao.examSno(connection,sNo,couNumber)){
                flag=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeDb(connection,null,null);
        }
        return flag;
    }

    @Test
    public void test(){
        ScoreService scoreService=new ScoreServiceImpl();
        List<Score> scoreList = scoreService.getScoreList(2017205, 2);
        for (Score score:scoreList){
            System.out.println(score.getsName());
        }
    }
}

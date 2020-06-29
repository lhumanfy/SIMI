package com.lpf.dao.score;

import com.lpf.dao.BaseDao;
import com.lpf.pojo.Score;
import com.lpf.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoresDaoImpl implements ScoresDao {
    @Override
    public List<Score> getUserScore(Connection connection, Integer Sno,int Counumber,int curPage,int pageSize) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Score> scoreList = new ArrayList<>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select top "+pageSize+" *\n" +
                    "from  (select  row_number() over (order by stu.Sno) as rownumber,sco.Sno,sco.Counumber,cou.Couname,Scores,Sname\n" +
                    "from Score sco,StudentInfo stu,CourseInfo cou where sco.Sno=stu.Sno and sco.Counumber=cou.Counumber");
            List<Object> list = new ArrayList<>();
            if (Sno != null) {
                sql.append(" and cast(sco.Sno as CHAR) like ?");
                list.add("%" + Sno + "%");
            }
            if (Counumber > 0) {
                sql.append(" and cou.Counumber=?");
                list.add(Counumber);
            }

            //数据库分页
            sql.append(" ) temp_tb where rownumber>((?-1)*?) ");
            list.add(curPage);
            list.add(pageSize);
            Object[] params = list.toArray();
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql.toString(), params);
                while (resultSet.next()) {
                    Score score=new Score();
                    score.setsNo(resultSet.getInt("Sno"));
                    score.setsName(resultSet.getString("Sname"));
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

    @Override
    public int getUserScoreCount(Connection connection, Integer Sno, int Counumber, int curPage, int pageSize) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count=0;
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select  count(1) as count\n" +
                    "from Score sco,StudentInfo stu,CourseInfo cou where sco.Sno=stu.Sno and sco.Counumber=cou.Counumber");
            List<Object> list = new ArrayList<>();
            if (Sno != null) {
                sql.append(" and cast(sco.Sno as CHAR) like ?");
                list.add("%" + Sno + "%");
            }
            if (Counumber > 0) {
                sql.append(" and cou.Counumber=?");
                list.add(Counumber);
            }

            Object[] params = list.toArray();
            try {
                resultSet = BaseDao.query(connection, preparedStatement, resultSet, sql.toString(), params);
                while (resultSet.next()) {
                   count=resultSet.getInt("count");
                }
                BaseDao.closeDb(connection, preparedStatement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public int upScoreInfo(Connection connection, Score score) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        if (connection != null) {
            String sql = "update Score set Couname=?,Scores=?,Rescores=? where Sno=? and Counumber=?";
            Object[] params = {score.getCouName(),score.getScores(),"无",score.getsNo(),score.getCouNum()};
            result = BaseDao.update(connection, preparedStatement, sql, params);
            BaseDao.closeDb(connection, preparedStatement, null);
        }
        return result;
    }

    @Override
    public int delScoreInfo(Connection connection, int Sno, int Counumber) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        if (connection != null) {
            String sql = "delete Score where Sno=? and Counumber=?";
            Object[] params = {Sno,Counumber};
            result = BaseDao.update(connection, preparedStatement, sql, params);
            BaseDao.closeDb(connection, preparedStatement, null);
        }
        return result;
    }

    @Override
    public int addScoreInfo(Connection connection, Score score) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        if (connection != null) {
            String sql = "insert into Score values (?,?,?,?,?)";
            Object[] params = {score.getsNo(),score.getCouNum(),"空",score.getScores(),"无"};
            result = BaseDao.update(connection, preparedStatement, sql, params);
            BaseDao.closeDb(connection, preparedStatement, null);
        }
        return result;
    }

    @Override
    public Boolean examSno(Connection connection, int sNo, int couNumber) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flage = false;

        if (connection != null) {
            String sql = "select * from Score where Sno=? and Counumber=?";
            Object[] params = {sNo,couNumber};

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
}

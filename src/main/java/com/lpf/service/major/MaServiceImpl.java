package com.lpf.service.major;

import com.lpf.dao.BaseDao;
import com.lpf.dao.major.MajorDao;
import com.lpf.dao.major.MajorDaoImpl;
import com.lpf.dao.student.StuDao;
import com.lpf.dao.student.StuDaoImpl;
import com.lpf.pojo.Major;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MaServiceImpl implements MaService{

    private MajorDao majorDao;

    public MaServiceImpl() {
        majorDao=new MajorDaoImpl();
    }

    //获取专业列表
    @Override
    public List<Major> getMajorList() {
        Connection connection=null;
        List<Major> majorList=new ArrayList<>();
        try {
            connection= BaseDao.getConnection();
            majorList = majorDao.getMajorInfo(connection);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeDb(connection,null,null);
        }
        return majorList;
    }

    @Test
    public void test(){
        MaService maService=new MaServiceImpl();
        List<Major> majorList = maService.getMajorList();
        for (Major major:majorList){
            System.out.println(major.getMajorName());
        }
    }
}

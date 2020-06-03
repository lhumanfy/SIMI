package com.lpf.dao.major;

import com.lpf.pojo.Major;

import java.sql.Connection;
import java.util.List;

public interface MajorDao {
    //获取角色列表
    public List<Major> getMajorInfo(Connection connection);
}

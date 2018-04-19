package com.example.mybatis.dao;

import com.example.mybatis.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by Taxz on 2018/4/17.
 */

public interface ExecuteDao {
    //返回多个结果
    @Select(value = "${value}")
    List<Map> get(String value);
    @Delete("delete from user where id=#{id}")
    public void deleteUser(int id);

    @Update("update user set userName=#{userName},userAddress=#{userAddress}"
            + " where id=#{id}")
    public void updateUser(User user);

}

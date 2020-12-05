package com.booking.dao;

import com.booking.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    //查询用户
    User findByAccount(String account);

    void register(User user);
}

package com.booking.service;

import com.booking.entity.User;

//***业务层
public interface UserService {
    User login(User user);
    void register(User user);
}

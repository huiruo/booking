package com.booking.controller;

import com.booking.entity.Result;
import com.booking.service.UserService;
import com.booking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Result<User> login(@RequestBody User user, HttpServletRequest request) {
        User entityUser = new User();
        try {
            User userDb = userService.login(user);
            entityUser.setAccount(userDb.getAccount()).setEmail(userDb.getEmail()).setId(userDb.getId());
            //登录成功保存标记:方式1：存在 ServletContext application(暂用服务器资源) 2.Redis: 以userid为标记
            request.getServletContext().setAttribute(userDb.getId(), userDb);
            return new Result<>(entityUser);
        } catch (Exception e) {
            return new Result<>(1, e.getMessage());
        }
    }

    @RequestMapping("register")
    public Result<User> register(@RequestBody User user, HttpServletRequest request) {
        try {
            userService.register(user);
            return new Result<>();
        } catch (Exception e) {
            //return new Result<>(1, e.getMessage());
            return new Result<>(4, e.getMessage());
        }
    }
}

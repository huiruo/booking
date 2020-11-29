package com.booking.controller;

import com.booking.utils.Result;
import com.booking.utils.User;
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
public class LoginController {
    //***登录
    @RequestMapping("login")
    public Result login(@RequestBody User user, HttpServletRequest request){
        Result result=new Result();
        log.info("登录user"+user);
        log.info("登录userDb"+user);
            try{
            /*User  userDb = userService.login(user);
            result.setMsg("登录成功").setUserId(userDb.getId());
            //登录成功保存标记:  方式1：存在 ServletContext application(暂用服务器资源) 2.Redis: 以userid为标记
            request.getServletContext().setAttribute(userDb.getId(),userDb);
             */
        }catch (Exception e){
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }
    @RequestMapping("register")
    public Result register(@RequestBody User user, HttpServletRequest request){
        Result result=new Result();
        log.info("register1"+user);
        log.info("register2"+user);
        try{
            /*User  userDb = userService.login(user);
            result.setMsg("登录成功").setUserId(userDb.getId());
            //登录成功保存标记:  方式1：存在 ServletContext application(暂用服务器资源) 2.Redis: 以userid为标记
            request.getServletContext().setAttribute(userDb.getId(),userDb);
             */
        }catch (Exception e){
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }
}

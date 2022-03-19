package com.turingcourt.service.impl;

import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.User;
import com.turingcourt.service.UserLoginService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Cyanogen
 * @description 用户通用操作
 * @date 2022/3/7 19:58
 */
@Service
public class UserLoginServiceImpl implements UserLoginService, UserDetailsService {

    @Resource
    private UserDao userDao;

    @Override
    public Boolean register(User user) {
        user.setIsEnable(true);
        user.setSex("男");
        //密码加密
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int insert = userDao.register(user);
        return insert > 0;
    }

    @Override
    public Boolean checkAccount(String username) {
        return userDao.checkAccount(username);
    }

    @Override
    public Boolean verifyAnswer(String username, String answer) {
        User user = userDao.verifyAnswer(username, answer);
        return user != null;
    }

    @Override
    public Boolean changePassword(String username, String password) {
        User user = userDao.getUserByName(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        int update = userDao.update(user);
        return update > 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //密码认证在框架内实现
        User user = userDao.getUserByName(username);
        if (user != null) {
            //返回后会调用内部认证
            return user;
        }
        throw new UsernameNotFoundException("账户不存在!");
    }
}

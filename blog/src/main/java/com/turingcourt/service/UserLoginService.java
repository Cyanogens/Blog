package com.turingcourt.service;

import com.turingcourt.entity.User;

/**
 * 用户注册/登录
 *
 * @author Cyanogen
 */
public interface UserLoginService {

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    Boolean register(User user);

    /**
     * 帐号校验
     *
     * @param username 用户账号
     * @return 是否已经注册
     */
    Boolean checkAccount(String username);

    /**
     * 验证密保答案
     *
     * @param answer   密保答案
     * @param username 用户名
     * @return 答案是否正确
     */
    Boolean verifyAnswer(String username, String answer);

    /**
     * 更改密码
     *
     * @param password 新密码
     * @param username 用户名
     * @return 是否更改成功
     */
    Boolean changePassword(String username, String password);
}

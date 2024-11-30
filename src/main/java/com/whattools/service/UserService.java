package com.whattools.service;

import com.whattools.pojo.UserDao;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    UserDao userRegister(String userAccount, String userPassword, String checkPassword );
    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 用户信息
     */
    Object userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param userId
     * @return
     */
    UserDao getSafetyUser(String userId);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);
}

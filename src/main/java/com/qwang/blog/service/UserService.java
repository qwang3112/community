package com.qwang.blog.service;

import com.qwang.blog.model.po.User;

/**
 * @author qwang
 * @date 2020/7/27
 */
public interface UserService {

    /**
     * 检查用户在数据库中是否存在
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 返回存在的用户
     */
    User checkUser(String username, String password);

    User saveUser(User user);
}

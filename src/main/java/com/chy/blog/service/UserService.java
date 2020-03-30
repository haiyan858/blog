package com.chy.blog.service;

import com.chy.blog.po.User;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-26 22:43
 * @Description:
 */
public interface UserService {
    //检查用户名和密码
    User checkUser(String username, String password);
}

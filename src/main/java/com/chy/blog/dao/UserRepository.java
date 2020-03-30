package com.chy.blog.dao;

import com.chy.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-26 22:45
 * @Description:
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);
}

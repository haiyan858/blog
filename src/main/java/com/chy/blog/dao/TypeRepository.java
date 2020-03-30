package com.chy.blog.dao;

import com.chy.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-27 12:12
 * @Description:
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}

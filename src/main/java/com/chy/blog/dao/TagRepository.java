package com.chy.blog.dao;

import com.chy.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-27 12:12
 * @Description: 标签
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
}

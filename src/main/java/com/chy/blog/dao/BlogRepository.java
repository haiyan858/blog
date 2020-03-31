package com.chy.blog.dao;

import com.chy.blog.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-30 14:01
 * @Description:
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

}

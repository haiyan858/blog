package com.chy.blog.service;

import com.chy.blog.po.Blog;
import com.chy.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-30 13:54
 * @Description:
 */
public interface BlogService {
    /**
     * 查询
     * @param id 根据ID查询
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 查询
     * @param pageable 分页
     * @param blog 搜索框的条件
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * 保存
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 修改
     * @param id 根据id查处待修改的对象
     * @param blog 赋值给target对象
     * @return
     */
    Blog updateBlog(Long id, Blog blog);


    /**
     * 删除
     * @param id
     */
    void deleteBlog(Long id);

}

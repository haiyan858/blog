package com.chy.blog.service;

import com.chy.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-27 12:06
 * @Description:
 */
public interface TagService {
    /**新增*/
    Tag saveTag(Tag Tag);

    /**单个查询*/
    Tag getTag(Long id);

    /**批量查询:分页查询*/
    //List<Tag> listTag(Long id);
    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    /**
     * 修改
     * @param id 根据ID查询到要修改的记录
     * @param Tag 修改之后的
     * @return
     */
    Tag updateTag(Long id, Tag Tag);

    /**删除*/
    void deleteTag(Long id);

    /**根据名称查询*/
    Tag getTagByName(String name);
}

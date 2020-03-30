package com.chy.blog.service;

import com.chy.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-27 12:06
 * @Description:
 */
public interface TypeService {
    /**新增*/
    Type saveType(Type type);

    /**单个查询*/
    Type getType(Long id);

    /**批量查询:分页查询*/
    //List<Type> listType(Long id);
    Page<Type> listType(Pageable pageable);

    /**
     * 修改
     * @param id 根据ID查询到要修改的记录
     * @param type 修改之后的
     * @return
     */
    Type updateType(Long id, Type type);

    /**删除*/
    void deleteType(Long id);

    /**根据名称查询*/
    Type getTypeByName(String name);
}

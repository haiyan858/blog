package com.chy.blog.service;

import com.chy.blog.dao.BlogRepository;
import com.chy.blog.po.Blog;
import com.chy.blog.po.Type;
import com.chy.blog.vo.BlogQuery;
import com.chy.blog.web.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-30 14:00
 * @Description:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     *
     * @param id 根据ID查询
     * @return
     */
    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    /**
     * 分页动态查询
     * @param pageable 分页
     * @param blog 搜索框的条件
     * @return
     */
    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root,CriteriaQuery<?> cq,CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if (blog.isRecommend()){
                    predicates.add(cb.equal(root.get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));

                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog blog1 = blogRepository.getOne(id);
        if (blog1 == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog1,blog);
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}

package com.chy.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-26 18:58
 * @Description: 博客标签 Tag
 */
@Entity
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @ManyToMany(mappedBy = "tags") //被维护的一方
    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

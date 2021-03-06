package com.chy.blog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-26 19:01
 * @Description: 博客评论； 自关联
 */
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String nickname; //昵称
    private String email; //邮箱
    private String content; //评论内容
    private String avatar; //头像

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; //创建时间

    @ManyToOne
    private Blog blog;

    /**
     * 自关联
     */
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>(); //子级评论：回复的评论
    @ManyToOne
    private Comment parentComment; //父级评论

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

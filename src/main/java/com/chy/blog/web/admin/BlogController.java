package com.chy.blog.web.admin;

import com.chy.blog.po.Blog;
import com.chy.blog.po.User;
import com.chy.blog.service.BlogService;
import com.chy.blog.service.TagService;
import com.chy.blog.service.TypeService;
import com.chy.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-27 10:11
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT="admin/blogs-input";
    private static final String LIST="admin/blogs";
    private static final String REDIRECT_LIST="redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**列表*/
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return LIST;
    }

    /**列表-搜索*/
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blog, Model model){
        Page<Blog> blogs = blogService.listBlog(pageable, blog);
        model.addAttribute("page",blogs);
        return "admin/blogs :: blogList";
    }

    /**
     * 跳转到新增页面
     * @param model 初始化，和修改页面共用
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",new Blog()); //初始化
        return INPUT;
    }

    /*@PostMapping("/blogs")
    public String blogs(BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }*/

    /**新增时提交*/
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes ra){
        blog.setUser((User) session.getAttribute("user")); //用户
        blog.setType(typeService.getType(blog.getType().getId())); //分类
        blog.setTags(tagService.listTag(blog.getTagIds())); //标签

        Blog b = blogService.saveBlog(blog); //保存

        if (b == null){
            ra.addFlashAttribute("message","新增失败");
        }else {
            ra.addFlashAttribute("message","新增成功");
        }
        return REDIRECT_LIST;
    }

}

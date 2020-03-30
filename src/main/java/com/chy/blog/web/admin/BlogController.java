package com.chy.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-27 10:11
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @RequestMapping("/blogs")
    public String blogs(){
        return "admin/blogs";
    }

}

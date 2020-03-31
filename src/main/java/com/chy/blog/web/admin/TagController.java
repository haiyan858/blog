package com.chy.blog.web.admin;

import com.chy.blog.po.Tag;
import com.chy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-27 12:22
 * @Description: 标签管理
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagservice;

    /**
     * 分页查询
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String tags(
            @PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            Model model){
        Page<Tag> tags = tagservice.listTag(pageable);
        model.addAttribute("page",tags);
        return "admin/tags";
    }

    /**
     * 跳转到新增页面
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Tag tag = tagservice.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }

    /**
     * 新增分类
     * @param tag 表单中填写
     * @param bindingResult
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String posttags(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes attributes){
        //数据库check存在重复name
        Tag tag1 = tagservice.getTagByName(tag.getName());
        if (tag1 != null){
            bindingResult.rejectValue("name","nameError","不能添加重复的标签");
        }

        //后端校验参数不为空，校验结果返回前台
        if (bindingResult.hasErrors()){
            return "admin/tags-input";
        }

        Tag saveTag = tagservice.saveTag(tag);
        if (saveTag == null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    //修改分类名称
    @PostMapping("/tags/{id}")
    public String edittags(@Valid Tag tag, BindingResult bindingResult,@PathVariable Long id, RedirectAttributes attributes){
        //数据库check存在重复name
        Tag Tag1 = tagservice.getTagByName(tag.getName());
        if (Tag1 != null){
            bindingResult.rejectValue("name","nameError","不能添加重复的标签");
        }

        //后端校验参数不为空，校验结果返回前台
        if (bindingResult.hasErrors()){
            return "admin/tags-input";
        }

        Tag updateTag = tagservice.updateTag(id,tag);
        if (updateTag == null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除分类
     * @param id 主键ID
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String deletetags(@PathVariable Long id, RedirectAttributes attributes){
        tagservice.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}

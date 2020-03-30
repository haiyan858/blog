package com.chy.blog.web.admin;

import com.chy.blog.po.Type;
import com.chy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.rmi.runtime.Log;

import javax.validation.Valid;

/**
 * @Author cuihaiyan
 * @Create_Time 2020-03-27 12:22
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分页查询
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(
            @PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            Model model){
        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page",types);
        return "admin/types";
    }

    /**
     * 跳转到新增页面
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Type type = typeService.getType(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }

    /**
     * 新增分类
     * @param type 表单中填写
     * @param bindingResult
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String postTypes(@Valid Type type, BindingResult bindingResult, RedirectAttributes attributes){
        //数据库check存在重复name
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            bindingResult.rejectValue("name","nameError","不能添加重复的分类");
        }

        //后端校验参数不为空，校验结果返回前台
        if (bindingResult.hasErrors()){
            return "admin/types-input";
        }

        Type saveType = typeService.saveType(type);
        if (saveType == null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    //修改分类名称
    @PostMapping("/types/{id}")
    public String editTypes(@Valid Type type, BindingResult bindingResult,@PathVariable Long id, RedirectAttributes attributes){
        //数据库check存在重复name
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            bindingResult.rejectValue("name","nameError","不能添加重复的分类");
        }

        //后端校验参数不为空，校验结果返回前台
        if (bindingResult.hasErrors()){
            return "admin/types-input";
        }

        Type updateType = typeService.updateType(id,type);
        if (updateType == null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     * @param id 主键ID
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String deleteTypes(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}

package com.zhiwen.controller;

import com.zhiwen.entity.User;
import com.zhiwen.service.BlogService;
import com.zhiwen.service.TagService;
import com.zhiwen.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * 博客展示控制层
 *

 * @date : 2020/12/9 10:03
 */
@Controller
public class BlogShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 功能描述: 博客详情查看
     *
     * @param id    1
     * @param model 2
     * @return java.lang.String
     *
     * @date 2020/12/9 10:04
     **/
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        model.addAttribute("blog", blogService.viewBlogDetail(id, userId));
        model.addAttribute("typeMap", typeService.getAllTypes());
        model.addAttribute("tagMap", tagService.getAllTags());
        return "blog";
    }
}

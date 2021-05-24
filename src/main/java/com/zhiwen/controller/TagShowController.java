package com.zhiwen.controller;

import com.zhiwen.constant.BlogConstant;
import com.zhiwen.entity.BlogExample;
import com.zhiwen.entity.User;
import com.zhiwen.pojo.TopTag;
import com.zhiwen.service.BlogService;
import com.zhiwen.service.TagService;
import com.zhiwen.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 标签管理
 *

 * @date : 2020/12/5 09:01
 */
@Controller
public class TagShowController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    /**
     * 功能描述: 标签页
     *
     * @param pageNum  1
     * @param pageSize 2
     * @param id       3
     * @param model    4
     * @return java.lang.String
     *
     * @date 2020/12/5 19:43
     **/
    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize, @PathVariable Integer id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        List<TopTag> tags = blogService.getTopTagList(10000);
        if (id == -1 && tags.size()!=0) {
            id = tags.get(0).getTagId();
        }
        model.addAttribute("tags", tags);
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTagIdStrLike("%," + id + ",%").andPublishedEqualTo(true);
        blogExample.setOrderByClause("create_time desc");
        model.addAttribute("page", blogService.page(blogExample, pageNum, pageSize,userId));
        model.addAttribute("activeTagId", id);
        model.addAttribute("typeMap", typeService.getAllTypes());
        model.addAttribute("tagMap", tagService.getAllTags());
        return BlogConstant.TAGS_PAGE;
    }
}

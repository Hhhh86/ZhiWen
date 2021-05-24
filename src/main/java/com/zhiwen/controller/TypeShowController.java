package com.zhiwen.controller;

import com.zhiwen.constant.BlogConstant;
import com.zhiwen.entity.BlogExample;
import com.zhiwen.entity.User;
import com.zhiwen.pojo.TopType;
import com.zhiwen.service.BlogService;
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
 * 博客类型管理控制类
 *

 * @date : 2020/11/30 12:07
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 功能描述: 类型页
     *
     * @param pageNum  1
     * @param pageSize 2
     * @param id       3
     * @param model    4
     * @return java.lang.String
     *
     * @date 2020/12/5 19:44
     **/
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue =
            "10") Integer pageSize, @PathVariable Integer id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user == null ? null : user.getUserId();
        List<TopType> types = blogService.getTopTypeList(10000);
        if (id == -1 && types.size()!=0) {
            id = types.get(0).getTypeId();
        }
        model.addAttribute("types", types);
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andTypeIdEqualTo(id).andPublishedEqualTo(true);
        blogExample.setOrderByClause("create_time desc");
        model.addAttribute("page", blogService.page(blogExample, pageNum, pageSize, userId));
        model.addAttribute("typeMap", typeService.getAllTypes());
        model.addAttribute("activeTypeId", id);
        return BlogConstant.TYPES_PAGE;
    }
}

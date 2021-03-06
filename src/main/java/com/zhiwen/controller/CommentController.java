package com.zhiwen.controller;

import com.zhiwen.entity.User;
import com.zhiwen.service.BlogService;
import com.zhiwen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 评论控制层
 *

 * @date : 2020/12/9 10:02
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    /**
     * 功能描述: 获取评论信息
     *
     * @param blogId 1
     * @param model  2
     * @return java.lang.String
     *
     * @date 2020/12/9 18:08
     **/
    @GetMapping("/comments/{blogId}")
    public String getComments(@PathVariable Integer blogId, @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue =
                                      "10") Integer pageSize, Model model) {
        model.addAttribute("comments", commentService.getComments(blogId, pageNum, pageSize));
        model.addAttribute("blogUserId", blogService.findOne(blogId).getUserId());
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String addComments(Integer blogId, String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //新增评论
        commentService.addComments(blogId, content, user);
        return "redirect:/comments/" + blogId;
    }
}

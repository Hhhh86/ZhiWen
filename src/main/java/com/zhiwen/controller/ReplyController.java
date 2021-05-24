package com.zhiwen.controller;

import com.zhiwen.entity.User;
import com.zhiwen.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * 回复管理控制层
 *

 * @date : 2020/12/11 17:51
 */
@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/replys")
    public String addReplys(Integer commentId, Integer blogId, String content,
                            Integer repliedUserId, String replyType,HttpSession session) {
        User user = (User) session.getAttribute("user");
        //新增评论
        replyService.addReplys(commentId, blogId, content, user,repliedUserId,replyType);
        return "redirect:/comments/" + blogId;
    }
}

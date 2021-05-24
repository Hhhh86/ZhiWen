package com.zhiwen.controller;

import com.zhiwen.constant.BlogConstant;
import com.zhiwen.dto.RegisterDto;
import com.zhiwen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册控制器
 *

 * @date : 2020/12/1 13:59
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述: 跳转注册页面
     *
     * @return java.lang.String
     *
     * @date 2020/12/1 14:02
     **/
    @RequestMapping("/toRegisterPage")
    public String toRegisterPage() {
        return BlogConstant.REGISTER_PAGE;
    }

    /**
     * 功能描述:用户注册
     *
     * @param registerDto 1
     * @param request 2
     * @return java.lang.String
     *
     * @date 2020/12/1 18:10
     **/
    @PostMapping("/register")
    public String register(RegisterDto registerDto, HttpServletRequest request, RedirectAttributes attributes) {
        try {
            userService.register(registerDto,request.getRemoteAddr());
        } catch (Exception e) {
            log.error("注册失败!",e);
            attributes.addFlashAttribute("message", "注册失败!");
            return "redirect:/user/toRegisterPage";
        }
        return "redirect:/user/toLoginPage";
    }
}


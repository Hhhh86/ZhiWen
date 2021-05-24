package com.zhiwen.dto;

import lombok.Data;

/**
 * 登录提交信息传输类
 *

 * @date : 2020/12/1 19:45
 */
@Data
public class LoginDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 跳转路径
     */
    private String path;
    /**
     * 用户角色
     */
    private String userRole;
}

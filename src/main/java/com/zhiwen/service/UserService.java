package com.zhiwen.service;

import com.github.pagehelper.PageInfo;
import com.zhiwen.dto.LoginDto;
import com.zhiwen.dto.RegisterDto;
import com.zhiwen.entity.User;
import com.zhiwen.entity.UserExample;

/**
 * 用户管理服务层接口
 *
 * @date : 2020/12/1 18:14
 */
public interface UserService {

    /**
     * 功能描述: 注册用户
     *
     * @param registerDto 1
     * @param remoteIp 2
     * @return void
     *
     * @date 2020/12/21 11:55
     **/
    void register(RegisterDto registerDto,String remoteIp);

    /**
     * 功能描述: 登录校验
     *
     * @param loginDto 1
     * @return com.zhiwen.entity.User
     *
     * @date 2020/12/21 11:55
     **/
    User checkUser(LoginDto loginDto);

    /**
     * 功能描述: 登录更新用户信息
     *
     * @param user 1
     * @param lastLoginIp 2
     * @return void
     *
     * @date 2020/12/21 11:56
     **/
    void updateLoginInfo(User user,String lastLoginIp);

    /**
     * 功能描述: 获取用户信息
     *
     * @param userExample 1
     * @param pageNum 2
     * @param pageSize 3
     * @return com.github.pagehelper.PageInfo<com.zhiwen.entity.User>
     *
     * @date 2020/12/21 12:02
     **/
    PageInfo<User> getAllUser(UserExample userExample,Integer pageNum,Integer pageSize);

    /**
     * 功能描述: 删除用户
     *
     * @param id 1
     * @return void
     *
     * @date 2020/12/23 15:25
     **/
    void deleteUser(Integer id);

    /**
     * 功能描述: 封禁用户
     *
     * @param id 1
     * @return void
     *
     * @date 2020/12/23 15:27
     **/
    void banUser(Integer id);

    /**
     * 功能描述: 解封用户
     *
     * @param id 1
     * @return void
     *
     * @date 2020/12/23 15:27
     **/
    void unblockUser(Integer id);
}

package com.zhiwen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiwen.constant.BlogConstant;
import com.zhiwen.constant.BlogDataDictionary;
import com.zhiwen.dto.LoginDto;
import com.zhiwen.dto.RegisterDto;
import com.zhiwen.entity.*;
import com.zhiwen.mapper.BlogMapper;
import com.zhiwen.mapper.BlogTagMapper;
import com.zhiwen.mapper.UserMapper;
import com.zhiwen.service.UserService;
import com.zhiwen.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 用户管理服务层实现
 *

 * @date : 2020/12/1 18:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;

    /**
     * 功能描述: 用户注册
     *
     * @param registerDto 注册提交的信息
     * @param remoteIp    客户机IP
     * @return void
     *
     * @date 2020/12/1 18:25
     **/
    @Override
    public void register(RegisterDto registerDto, String remoteIp) {
        Date now = new Date();
        User user = User.builder().username(registerDto.getUsername())
                .password(MD5Utils.code(registerDto.getPassword()))
                .nickname(registerDto.getNickname())
                .userGender(registerDto.getGender())
                .email(registerDto.getEmail())
                .telephone(registerDto.getTelephone())
                .userRole(BlogDataDictionary.USER_ROLE_GENERAL_USER)
                .userStatus(BlogDataDictionary.USER_STATUS_NORMAL)
                .headPortrait(registerDto.getGender() ? BlogConstant.DEFAULT_HEAD_MALE_PORTRAIT :
                        BlogConstant.DEFAULT_HEAD_FEMALE_PORTRAIT)
                .lastLoginIp(remoteIp)
                .registerTime(now)
                .lastLoginTime(now)
                .createTime(now)
                .updateTime(now).delFlag(false)
                .build();
        userMapper.insertSelective(user);
    }

    /**
     * 功能描述: 登录校验
     *
     * @param loginDto 1
     *
     * @date 2020/12/1 19:47
     **/
    @Override
    public User checkUser(LoginDto loginDto) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria().andUsernameEqualTo(loginDto.getUsername())
                .andPasswordEqualTo(MD5Utils.code(loginDto.getPassword()))
                .andUserStatusEqualTo(BlogDataDictionary.USER_STATUS_NORMAL)
                .andDelFlagEqualTo(false);
        if (StringUtils.equalsAny(loginDto.getUserRole(), BlogDataDictionary.USER_ROLE_SUPER_ADMIN,
                BlogDataDictionary.USER_ROLE_GENERAL_ADMIN)) {
            criteria.andUserRoleIn(Arrays.asList(BlogDataDictionary.USER_ROLE_SUPER_ADMIN,
                    BlogDataDictionary.USER_ROLE_GENERAL_ADMIN));
        }
        List<User> list = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 功能描述: 更新登录信息
     *
     * @param user        1
     * @param lastLoginIp 2
     * @return void
     *
     * @date 2020/12/1 20:27
     **/
    @Override
    public void updateLoginInfo(User user, String lastLoginIp) {
        User record =
                User.builder().userId(user.getUserId()).lastLoginIp(lastLoginIp).lastLoginTime(new Date()).build();
        userMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 功能描述: 获取用户信息
     *
     * @param userExample 1
     * @param pageNum     2
     * @param pageSize    3
     * @return com.github.pagehelper.PageInfo<com.zhiwen.entity.User>
     *
     * @date 2020/12/21 16:06
     **/
    @Override
    public PageInfo<User> getAllUser(UserExample userExample, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectByExample(userExample);
        return PageInfo.of(list);
    }

    /**
     * 功能描述: TODO
     *
     * @param id 1
     * @return void
     *
     * @date 2020/12/23 15:31
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) {
        //删除用户信息
        Date date = new Date();
        User user = new User();
        user.setUserId(id);
        user.setDelFlag(true);
        user.setDelTime(date);
        userMapper.updateByPrimaryKeySelective(user);
        //删除用户的博客信息
        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andUserIdEqualTo(id).andDelFlagEqualTo(false);
        List<Blog> blogList = blogMapper.selectByExample(blogExample);
        BlogTagExample blogTagExample = new BlogTagExample();
        for (Blog blog : blogList) {
            blog.setDelFlag(true);
            blog.setDelTime(date);
            blogMapper.updateByExampleSelective(blog, blogExample);
            //删除用户博客-标签关系信息
            blogTagExample.clear();
            blogTagExample.createCriteria().andBlogIdEqualTo(blog.getBlogId());
            blogTagMapper.deleteByExample(blogTagExample);
        }
    }

    @Override
    public void banUser(Integer id) {
        Date date = new Date();
        User user = new User();
        user.setUserId(id);
        user.setUserStatus(BlogDataDictionary.USER_STATUS_BAN);
        user.setUpdateTime(date);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void unblockUser(Integer id) {
        Date date = new Date();
        User user = new User();
        user.setUserId(id);
        user.setUserStatus(BlogDataDictionary.USER_STATUS_NORMAL);
        user.setUpdateTime(date);
        userMapper.updateByPrimaryKeySelective(user);
    }
}

package com.zhiwen.service;

import com.zhiwen.entity.User;
import com.zhiwen.pojo.CommentExtend;

import java.util.List;

/**
 * 评论管理服务层接口
 *
 * @date : 2020/12/9 18:08
 */
public interface CommentService {

    /**
     * 功能描述: 获取评论信息列表
     *
     * @param blogId 1
     * @param pageNum 2
     * @param pageSize 3
     * @return java.util.List<com.zhiwen.pojo.CommentExtend>
     *
     * @date 2020/12/11 16:23
     **/
    List<CommentExtend> getComments(Integer blogId, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 新增评论信息
     *
     * @param blogId 1
     * @param content 2
     * @param user 3
     * @return void
     *
     * @date 2020/12/11 16:23
     **/
    void addComments(Integer blogId, String content, User user);
}

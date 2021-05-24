package com.zhiwen.mapper;

import com.zhiwen.pojo.CommentExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论管理扩展mapper
 *

 * @date : 2020/12/9 18:46
 */
public interface CommentExtendMapper {
    /**
     * 功能描述: 获取评论信息列表
     *
     * @param blogId 1
     * @return java.util.List<com.zhiwen.pojo.CommentExtend>
     *
     * @date 2020/12/9 18:51
     **/
    public List<CommentExtend> getComments(@Param("blogId") Integer blogId);
}

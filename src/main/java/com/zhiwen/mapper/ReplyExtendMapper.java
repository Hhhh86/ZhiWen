package com.zhiwen.mapper;

import com.zhiwen.pojo.ReplyExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 回复管理扩展mapper
 *

 * @date : 2020/12/9 19:06
 */
public interface ReplyExtendMapper {
    /**
     * 功能描述: 获取回复列表
     *
     * @param commentId 1
     * @return java.util.List<com.zhiwen.pojo.ReplyExtend>
     *
     * @date 2020/12/9 19:13
     **/
    List<ReplyExtend> getReplys(@Param("commentId") Integer commentId);
}

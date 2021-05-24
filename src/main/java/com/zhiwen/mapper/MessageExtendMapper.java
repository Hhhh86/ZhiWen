package com.zhiwen.mapper;

import com.zhiwen.entity.MessageExample;
import com.zhiwen.pojo.MessageExtend;

import java.util.List;

/**
 * 留言扩展mapper
 *

 * @date : 2020/12/12 18:54
 */
public interface MessageExtendMapper {
    /**
     * 功能描述: 分页获取留言信箱列表
     *
     * @return java.util.List<com.zhiwen.pojo.MessageExtend>
     *
     * @date 2020/12/12 19:02
     **/
    List<MessageExtend> page(MessageExample messageExample);
}

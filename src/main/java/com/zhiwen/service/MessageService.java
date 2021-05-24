package com.zhiwen.service;

import com.github.pagehelper.PageInfo;
import com.zhiwen.entity.Message;
import com.zhiwen.entity.MessageExample;
import com.zhiwen.entity.User;
import com.zhiwen.pojo.MessageExtend;

/**
 * 留言服务层接口
 * @date : 2020/12/12 18:52
 */
public interface MessageService {
    /**
     * 功能描述: 分页获取留言信箱列表
     *
     * @param pageNum 1
     * @param pageSize 2
     * @return java.util.List<com.zhiwen.pojo.MessageExtend>
     *
     * @date 2020/12/12 19:00
     **/
    PageInfo<MessageExtend> page(Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 新增留言
     *
     * @param nickname 1
     * @param email 2
     * @param content 3
     * @param user 4
     * @return void
     *
     * @date 2020/12/17 15:39
     **/
    void addMessage(String nickname, String email, String content, User user);

    /**
     * 功能描述: 新增留言的回复
     *
     * @param nickname 1
     * @param email 2
     * @param content 3
     * @param messageId 4
     * @param repliedUserId 5
     * @param repliedUserNickname 6
     * @param user 7
     * @return void
     *
     * @date 2020/12/17 18:40
     **/
    void addMessageReply(String nickname, String email, String content, Integer messageId, Integer repliedUserId, String repliedUserNickname, User user);

    /**
     * 功能描述: 分页查询留言信息（不考虑分层）
     *
     * @param messageExample 1
     * @param pageNum 2
     * @param pageSize 3
     * @return com.github.pagehelper.PageInfo<com.zhiwen.entity.Message>
     *
     * @date 2020/12/24 16:11
     **/
    PageInfo<Message> page(MessageExample messageExample, Integer pageNum, Integer pageSize);

    /**
     * 功能描述: 删除信息
     *
     * @param id 1
     * @return void
     *
     * @date 2020/12/24 13:48
     **/
    void deleteMessage(Integer id);
}

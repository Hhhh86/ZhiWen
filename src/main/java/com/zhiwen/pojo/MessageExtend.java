package com.zhiwen.pojo;

import com.zhiwen.entity.Message;
import lombok.Data;

import java.util.List;

/**
 * 留言扩展类
 *

 * @date : 2020/12/12 18:56
 */
@Data
public class MessageExtend extends Message {
    /**
     * 留言的回复数
     */
    private long replyNums;
    /**
     * 回复列表
     */
    private List<Message> replyList;
}

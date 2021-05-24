package com.zhiwen.pojo;

import com.zhiwen.entity.Reply;
import lombok.Data;

/**
 * 回复扩展类
 */
@Data
public class ReplyExtend extends Reply {
    /**
     * 回复用户昵称
     */
    private String nickname;
    /**
     * 回复用户头像
     */
    private String headPortrait;
    /**
     * 被回复用户昵称
     */
    private String repliedNickname;
    /**
     * 被回复用户头像
     */
    private String repliedHeadPortrait;
}

package com.zhiwen.vo;

import com.zhiwen.entity.Blog;
import com.zhiwen.entity.BlogTag;
import lombok.Data;
import java.util.List;


/**
 * 博客信息vo
 *

 * @date : 2020/12/3 13:58
 */
@Data
public class BlogVo extends Blog {
    /**
     * 博客作者昵称
     */
    private String nickname;


    /**
     * 博客作者头像
     */
    private String headPortrait;

    /**
     * 当前登录用户是否点赞过
     */
    private Boolean flag;

    /**
     * 博客标签列表
     */
    private List<BlogTag> tags;
}

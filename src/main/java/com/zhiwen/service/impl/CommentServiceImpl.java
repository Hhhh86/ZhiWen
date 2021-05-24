package com.zhiwen.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhiwen.constant.RedisConstant;
import com.zhiwen.entity.Comment;
import com.zhiwen.entity.User;
import com.zhiwen.mapper.CommentExtendMapper;
import com.zhiwen.mapper.CommentMapper;
import com.zhiwen.mapper.ReplyExtendMapper;
import com.zhiwen.pojo.CommentExtend;
import com.zhiwen.pojo.ReplyExtend;
import com.zhiwen.service.CommentService;
import com.zhiwen.util.RedisValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 评论管理服务层实现
 *
 * @date : 2020/12/9 18:16
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentExtendMapper commentExtendMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplyExtendMapper replyExtendMapper;

    @Autowired
    private RedisValueUtil redisValueUtil;

    /**
     * 功能描述: 获取评论信息列表
     *
     * @param blogId   1
     * @param pageNum  2
     * @param pageSize 3
     * @return java.util.List<com.zhiwen.pojo.CommentExtend>
     *
     * @date 2020/12/9 18:50
     **/
    @Override
    public List<CommentExtend> getComments(Integer blogId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "comment_time desc");
        List<CommentExtend> commentExtends = commentExtendMapper.getComments(blogId);
        for (CommentExtend commentExtend : commentExtends) {
            List<ReplyExtend> replyExtendList = replyExtendMapper.getReplys(commentExtend.getCommentId());
            commentExtend.setReplyNums(replyExtendList.size());
            commentExtend.setReplyList(replyExtendList);
        }
        return commentExtends;
    }

    /**
     * 功能描述: 新增评论信息
     *
     * @param blogId  1
     * @param content 2
     * @param user    3
     *
     * @date 2020/12/11 19:25
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addComments(Integer blogId, String content, User user) {
        //新增评论信息
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setUserId(user.getUserId());
        comment.setBlogId(blogId);
        Date date = new Date();
        comment.setCommentTime(date);
        comment.setCreateTime(date);
        comment.setDelFlag(false);
        comment.setUpdateTime(date);
        commentMapper.insertSelective(comment);
        //更新redis中博客评论数
        redisValueUtil.hIncr(RedisConstant.COMMENT_KEY, blogId.toString());
    }
}

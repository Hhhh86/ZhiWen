package com.zhiwen.mapper;

import com.zhiwen.entity.BlogTag;
import com.zhiwen.entity.BlogTagExample;
import com.zhiwen.pojo.TopTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagExtendMapper {

    /**
     * 功能描述: 查询指定数量的top标签的博客数
     *
     * @param topSize 1
     * @return java.util.List<com.zhiwen.pojo.TopTag>
     *
     * @date 2020/12/1 12:05
     **/
    List<TopTag> getTopTagList(Integer topSize);
}
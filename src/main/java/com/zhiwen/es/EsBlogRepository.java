package com.zhiwen.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 博客搜索持久层
 *

 * @date : 2020/12/27 12:00
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlogDo, Integer> {

}

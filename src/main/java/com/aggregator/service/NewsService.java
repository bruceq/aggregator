package com.aggregator.service;

import com.aggregator.base.BaseService;
import com.aggregator.model.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Bruce_Q
 * @create 2017-05-05 16:21
 **/
public interface NewsService extends BaseService<News> {
    List<News> selectAllByType(@Param("type") String type, @Param("source") String source);

    List<News> selectAllByTitle(@Param("title") String title);
}

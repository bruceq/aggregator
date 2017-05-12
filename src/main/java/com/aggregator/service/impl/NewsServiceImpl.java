package com.aggregator.service.impl;

import com.aggregator.base.BaseServiceImpl;
import com.aggregator.mapper.NewsMapper;
import com.aggregator.model.News;
import com.aggregator.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Bruce_Q
 * @create 2017-05-05 16:26
 **/
@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
    @Resource
    NewsMapper newsMapper;

    @Override
    public List<News> selectAllByType(String type) {
        return newsMapper.selectAllByType(type);
    }
}

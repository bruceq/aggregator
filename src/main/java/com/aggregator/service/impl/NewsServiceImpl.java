package com.aggregator.service.impl;

import com.aggregator.base.BaseServiceImpl;
import com.aggregator.mapper.NewsMapper;
import com.aggregator.model.News;
import com.aggregator.service.NewsService;

import javax.annotation.Resource;

/**
 * @author Bruce_Q
 * @create 2017-05-05 16:26
 **/
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
    @Resource
    NewsMapper newsMapper;
}

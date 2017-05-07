package com.aggregator.base;

import com.aggregator.crawler.pipeine.MysqlPipeline;
import com.aggregator.service.NewsService;

import javax.annotation.Resource;

/**
 * 基础控制类
 *
 * @author Bruce_Q
 * @create 2017-03-16 17:43
 **/
public abstract class AggregatorBaseController {

    @Resource
    protected MysqlPipeline mysqlPipeline;

    @Resource
    protected NewsService newsService;

}

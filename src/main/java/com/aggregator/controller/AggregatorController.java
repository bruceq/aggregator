package com.aggregator.controller;

import com.aggregator.base.AggregatorBaseController;
import com.aggregator.crawler.processor.GuanchaProcessor;
import com.aggregator.model.News;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * videoIn搜索功能
 *
 * @author Bruce_Q
 * @create 2017-03-29 16:59
 **/
@RestController
@RequestMapping(value = "/aggregator/")
public class AggregatorController extends AggregatorBaseController {

    /**
     * 测试接口，爬取【观察网】新闻
     * @return
     */
    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public String test(){
        Spider.create(new GuanchaProcessor())
                .addUrl("http://www.guancha.cn/")
                .addPipeline(mysqlPipeline)
                .run();
        return "";
    }

    /**
     * 获取数据库新闻内容
     *
     * @return newsList（新闻集合）
     */
    @RequestMapping(value = "/getNews", method = {RequestMethod.POST, RequestMethod.GET})
    public List<News> getNews(){
        List<News> newsList = new ArrayList<>();
        List<News> newss = newsService.selectAll();
        for(int i=0;i<5;i++){
            newsList.add(newss.get(i));
        }
        return newsList;
    }

}
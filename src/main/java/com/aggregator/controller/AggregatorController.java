package com.aggregator.controller;

import com.aggregator.base.AggregatorBaseController;
import com.aggregator.crawler.processor.GuanchaProcessor;
import com.aggregator.model.News;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     */
    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public void test() {
        Spider.create(new GuanchaProcessor())
                .addUrl("http://www.guancha.cn/")
                .thread(5)
                .addPipeline(mysqlPipeline)
                .run();
    }

    /**
     * 获取数据库新闻内容
     *
     * @return newsList（新闻集合）
     */
    @RequestMapping(value = "/getNews", method = {RequestMethod.POST, RequestMethod.GET})
    public List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        List<News> newss = newsService.selectAll();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Integer index = random.nextInt(newsService.selectAll().size()) - 1;
            newsList.add(newss.get(index));
        }
        return newsList;
    }

}
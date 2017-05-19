package com.aggregator.controller;

import com.aggregator.base.AggregatorBaseController;
import com.aggregator.crawler.processor.GuanchaProcessor;
import com.aggregator.crawler.processor.NetEaseProcessor;
import com.aggregator.crawler.processor.qq.QQProcessor;
import com.aggregator.model.News;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
     */
    @RequestMapping(value = "/crawler", method = {RequestMethod.POST, RequestMethod.GET})
    public void test() {
        Spider.create(new GuanchaProcessor())
                .addUrl("http://www.guancha.cn/")
                .addUrl("http://www.guancha.cn/internation")
                .addUrl("http://www.guancha.cn/economy")
                .addUrl("http://www.guancha.cn/industry-science")
                .addUrl("http://www.guancha.cn/politics")
                .addUrl("http://www.guancha.cn/military-affairs")
                .thread(5)
                .addPipeline(mysqlPipeline)
                .start();
        Spider.create(new NetEaseProcessor())
                .addUrl("http://temp.163.com/special/00804KVA/cm_guoji.js?callback=data_callback")
                .addUrl("http://temp.163.com/special/00804KVA/cm_guonei.js?callback=data_callback")
                .addUrl("http://temp.163.com/special/00804KVA/cm_shehui.js?callback=data_callback")
                .addUrl("http://temp.163.com/special/00804KVA/cm_war.js?callback=data_callback")
                .addUrl("http://tech.163.com/special/00097UHL/tech_datalist.js?callback=data_callback")
                .addUrl("http://money.163.com/special/002557S5/newsdata_idx_index.js?callback=data_callback")
                .thread(5)
                .addPipeline(mysqlPipeline)
                .start();
        Spider.create(new QQProcessor())
                .addUrl("http://news.qq.com/world_index.shtml")
                .addUrl("http://society.qq.com")
                .addUrl("http://mil.qq.com/mil_index.htm")
                .addUrl("http://tech.qq.com")
                .addUrl("http://ent.qq.com")
                .addUrl("http://finance.qq.com")
                .thread(5)
                .addPipeline(mysqlPipeline)
                .start();
    }

    /**
     * 获取观察者网新闻内容
     *
     * @return newsList（新闻集合）
     */
    @RequestMapping(value = "/getNews", method = {RequestMethod.POST, RequestMethod.GET})
    public List<News> getNews(@RequestParam(required = false) String type) {
        List<News> newsList = new ArrayList<>();
        List<News> newss = newsService.selectAllByType(type, "观察者网");
        for (int i = 0; i < 10; i++) {
            newsList.add(newss.get(i));
        }
        return newsList;
    }

    /**
     * 获取网易新闻内容
     *
     * @return newsList（新闻集合）
     */
    @RequestMapping(value = "/getWangyi", method = {RequestMethod.POST, RequestMethod.GET})
    public List<News> getWangyi(@RequestParam(required = false) String type) {
        List<News> newsList = new ArrayList<>();
        List<News> newss = newsService.selectAllByType(type, "网易新闻");
        for (int i = 0; i < 10; i++) {
            newsList.add(newss.get(i));
        }
        return newsList;
    }

    /**
     * 获取腾讯新闻内容
     *
     * @return newsList（新闻集合）
     */
    @RequestMapping(value = "/getQQ", method = {RequestMethod.POST, RequestMethod.GET})
    public List<News> getQQ(@RequestParam(required = false) String type) {
        List<News> newsList = new ArrayList<>();
        List<News> newss = newsService.selectAllByType(type, "腾讯新闻");
        for (int i = 0; i < 10; i++) {
            newsList.add(newss.get(i));
        }
        return newsList;
    }

}
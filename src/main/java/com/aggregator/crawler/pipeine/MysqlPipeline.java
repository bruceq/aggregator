package com.aggregator.crawler.pipeine;

import com.aggregator.model.News;
import com.aggregator.service.NewsService;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Bruce_Q
 * @create 2017-05-05 22:50
 **/
@Component
public class MysqlPipeline implements Pipeline {
    @Resource
    NewsService newsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (null != resultItems.get("news")) {
            News news = resultItems.get("news");
            if (null != news) {
                newsService.insert(news);
            }
        }
        if (null != resultItems.get("newsList")) {
            List<News> newsList = resultItems.get("newsList");
            for (News news : newsList) {
                if (null != news) {
                    newsService.insert(news);
                }
            }
        }
    }
}

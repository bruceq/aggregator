package com.aggregator.crawler.processor.qq;

import com.aggregator.model.News;
import us.codecraft.webmagic.Page;

/**
 * Created by 鑫 on 2017/5/19.
 */
public class PutFieldByType {
    public void putField(Page page, String author, StringBuilder content, String time, StringBuilder keyword, String title, StringBuilder img, String type) {
        News news = new News();
        news.setAuthor(author);
        news.setContent(content.toString());
        news.setCreate(time);
        news.setKeyword(keyword.toString());
        news.setSource("腾讯新闻");
        news.setTitle(title);
        news.setType(type);
        news.setUrl(page.getUrl().toString());
        news.setImageUrl(img.toString());
        if (!"".equals(img.toString())) {
            page.putField("news", news);
        }
    }

}

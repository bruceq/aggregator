package com.aggregator.crawler.processor;

import com.aggregator.model.News;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
public class NetEaseProcessor implements PageProcessor {
    //配置请求参数
    private Site site = Site.me().setCharset("gbk").setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    public static void main(String[] args) {
        Spider.create(new NetEaseProcessor())
                .addUrl("http://temp.163.com/special/00804KVA/cm_guoji.js?callback=data_callback")
                .start();
    }

    @Override
    public void process(Page page) {
        if (page.getRequest().getUrl().contains("callback=data_callback")) {
            JSONArray newsArray = (JSONArray) JSONArray.parse(StringUtils.substringBetween(page.getRawText(), "data_callback(", "])") + "]");
            for (int i = 0; i < newsArray.size(); i++) {
                JSONArray keywords = newsArray.getJSONObject(i).getJSONArray("keywords");
                StringBuilder keyword = new StringBuilder();
                for (int j = 0; j < keywords.size(); j++) {
                    keyword.append(keywords.getJSONObject(j).get("keyname") + " ");
                }
                News news = new News();
                news.setTitle((String) newsArray.getJSONObject(i).get("title"));
                news.setImageUrl((String) newsArray.getJSONObject(i).get("imgurl"));
                news.setAuthor("网易新闻");
                news.setCreate((String) newsArray.getJSONObject(i).get("time"));
                news.setKeyword(keyword.toString());
                news.setUrl((String) newsArray.getJSONObject(i).get("docurl"));
                if (page.getRequest().getUrl().contains("guoji")) {
                    news.setType("国际");
                }
                if (page.getRequest().getUrl().contains("guonei")) {
                    news.setType("国内");
                }
                if (page.getRequest().getUrl().contains("shehui")) {
                    news.setType("社会");
                }
                if (page.getRequest().getUrl().contains("war")) {
                    news.setType("军事");
                }
                if (page.getRequest().getUrl().contains("tech")) {
                    news.setType("科技");
                }
                if (page.getRequest().getUrl().contains("newsdata_idx")) {
                    news.setType("财经");
                }
                news.setSource("网易新闻");
                if (!"".equals(news.getImageUrl())) {
                    Request request = new Request((String) newsArray.getJSONObject(i).get("docurl"));
                    request.putExtra("news", news);
                    page.addTargetRequest(request);
                }
            }
        } else {
            News news = (News) page.getRequest().getExtra("news");
            List<String> contents = page.getHtml().xpath("//div[@class='post_text']/p/text()").all();
            StringBuilder content = new StringBuilder();
            for (String temp : contents) {
                if ("" != temp) {
                    content.append("    " + temp + "\n");
                }
            }
            news.setContent(content.toString());
            page.putField("news", news);
        }
    }


    @Override
    public Site getSite() {
        return site;
    }
}

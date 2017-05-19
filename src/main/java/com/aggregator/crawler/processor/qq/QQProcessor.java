package com.aggregator.crawler.processor.qq;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by 鑫 on 2017/5/19.
 */
public class QQProcessor implements PageProcessor {
    //配置请求参数
    private Site site = Site.me().setCharset("gbk").setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    public static void main(String[] args) {
        Spider.create(new QQProcessor())
//                .addUrl("http://news.qq.com/world_index.shtml")
//                .addUrl("http://society.qq.com")
//                .addUrl("http://mil.qq.com/mil_index.htm")
//                .addUrl("http://tech.qq.com")
//                .addUrl("http://ent.qq.com")
                .addUrl("http://finance.qq.com")
//                .addUrl("http://news.qq.com/a/20170519/000902.htm")
                .start();
    }

    @Override
    public void process(Page page) {
        if (page.getRequest().getUrl().matches("http://news.qq.com/world_index.shtml")) {
            List<String> urls = page.getHtml().xpath("//div[@class='Q-tpWrap']/a/@href").all();
            for (String url : urls) {
                if (url.matches("http://news.qq.com/a/\\d+/\\d+.htm")) {
                    Request request = new Request();
                    request.setUrl(url);
                    request.putExtra("type", "国际");
                    page.addTargetRequest(request);
                }
            }
        }
        if (page.getRequest().getUrl().matches("http://society.qq.com")) {
            List<String> urls = page.getHtml().xpath("//div[@class='Q-tpWrap']/a/@href").all();
            for (String url : urls) {
                if (url.matches("http://society.qq.com/a/\\d+/\\d+.htm")) {
                    Request request = new Request();
                    request.setUrl(url);
                    request.putExtra("type", "社会");
                    page.addTargetRequest(request);
                }
            }
        }
        if (page.getRequest().getUrl().matches("http://mil.qq.com/mil_index.htm")) {
            List<String> urls = page.getHtml().xpath("//div[@class='Q-tpWrap']/a/@href").all();
            for (String url : urls) {
                if (url.matches("http://mil.qq.com/a/\\d+/\\d+.htm")) {
                    Request request = new Request();
                    request.setUrl(url);
                    request.putExtra("type", "军事");
                    page.addTargetRequest(request);
                }
            }
        }
        if (page.getRequest().getUrl().matches("http://tech.qq.com")) {
            List<String> urls = page.getHtml().xpath("//div[@class='Q-tpListInner']/a/@href").all();
            for (String url : urls) {
                if (url.matches("http://tech.qq.com/a/\\d+/\\d+.htm")) {
                    Request request = new Request();
                    request.setUrl(url);
                    request.putExtra("type", "科技");
                    page.addTargetRequest(request);
                }
            }
        }
        if (page.getRequest().getUrl().matches("http://ent.qq.com")) {
            List<String> urls = page.getHtml().xpath("//div[@class='Q-tpWrap']/a/@href").all();
            for (String url : urls) {
                if (url.matches("http://ent.qq.com/a/\\d+/\\d+.htm")) {
                    Request request = new Request();
                    request.setUrl(url);
                    request.putExtra("type", "娱乐");
                    page.addTargetRequest(request);
                }
            }
        }
        if (page.getRequest().getUrl().matches("http://finance.qq.com")) {
            List<String> urls = page.getHtml().xpath("//div[@class='Q-tpWrap']/a/@href").all();
            for (String url : urls) {
                if (url.matches("http://finance.qq.com/a/\\d+/\\d+.htm") ||
                        url.matches("http://money.qq.com/a/\\d+/\\d+.htm") ||
                        url.matches("http://stock.qq.com/a/\\d+/\\d+.htm")) {
                    Request request = new Request();
                    request.setUrl(url);
                    request.putExtra("type", "财经");
                    page.addTargetRequest(request);
                }
            }
        }
        if (page.getRequest().getUrl().matches("http://news.qq.com/a/\\d+/\\d+.htm") ||
                page.getRequest().getUrl().matches("http://society.qq.com/a/\\d+/\\d+.htm") ||
                page.getRequest().getUrl().matches("http://mil.qq.com/a/\\d+/\\d+.htm") ||
                page.getRequest().getUrl().matches("http://tech.qq.com/a/\\d+/\\d+.htm") ||
                page.getRequest().getUrl().matches("http://ent.qq.com/a/\\d+/\\d+.htm") ||
                page.getRequest().getUrl().matches("http://finance.qq.com/a/\\d+/\\d+.htm") ||
                page.getRequest().getUrl().matches("http://money.qq.com/a/\\d+/\\d+.htm") ||
                page.getRequest().getUrl().matches("http://stock.qq.com/a/\\d+/\\d+.htm")
                ) {
            String title = page.getHtml().xpath("//h1/text()").toString();
            List<String> contents = page.getHtml().xpath("//div[@class='Cnt-Main-Article-QQ']/p/allText()").all();
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < contents.size(); i++) {
                if (!"".equals(contents)) {
                    content.append("    " + contents.get(i) + "\n");
                }
            }
            String author = String.valueOf(page.getHtml().xpath("//div[@class='qq_editor']/text()").replace("责任编辑：", ""));
            List<String> keywords = page.getHtml().xpath("//div[@bosszone='keyword']/span/a/text()").all();
            StringBuilder keyword = new StringBuilder();
            for (int i = 0; i < keywords.size(); i++) {
                if (!"".equals(keywords)) {
                    keyword.append(keywords.get(i) + " ");
                }
            }
            String time = page.getHtml().xpath("//span[@class='a_time']/text()").toString();
            List<String> imgs = page.getHtml().xpath("//p/img/@src").all();
            StringBuilder img = new StringBuilder();
            for (int i = 0; i < imgs.size(); i++) {
                if (!"".equals(imgs)) {
                    img.append(imgs.get(i) + ",");
                }
            }
            PutFieldByType putFieldByType = new PutFieldByType();
            if (page.getRequest().getExtra("type").equals("国际")) {
                putFieldByType.putField(page, author, content, time, keyword, title, img, "国际");
            }
            if (page.getRequest().getExtra("type").equals("社会")) {
                putFieldByType.putField(page, author, content, time, keyword, title, img, "社会");
            }
            if (page.getRequest().getExtra("type").equals("军事")) {
                putFieldByType.putField(page, author, content, time, keyword, title, img, "军事");
            }
            if (page.getRequest().getExtra("type").equals("科技")) {
                putFieldByType.putField(page, author, content, time, keyword, title, img, "科技");
            }
            if (page.getRequest().getExtra("type").equals("娱乐")) {
                putFieldByType.putField(page, author, content, time, keyword, title, img, "娱乐");
            }
            if (page.getRequest().getExtra("type").equals("财经")) {
                putFieldByType.putField(page, author, content, time, keyword, title, img, "财经");
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}

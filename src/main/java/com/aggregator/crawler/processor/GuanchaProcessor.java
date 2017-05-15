package com.aggregator.crawler.processor;

import com.aggregator.crawler.pipeine.MysqlPipeline;
import com.aggregator.model.News;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author Bruce_Q
 * @create 2017-05-05 17:02
 **/
public class GuanchaProcessor implements PageProcessor {
    //配置请求参数
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {
        if (page.getRequest().getUrl().startsWith("http://www.guancha.cn/") && !page.getRequest().getUrl().endsWith("shtml")) {
            List<String> urls = page.getHtml().xpath("//h4[@class='module-title']/a/@href").all();
            for (String url : urls) {
                if (page.getRequest().getUrl().matches("http://www.guancha.cn/")) {
                    Request request = new Request(url);
                    request.putExtra("type", "首页");
                    page.addTargetRequest(request);
                }
                if (page.getRequest().getUrl().contains("politics")) {
                    Request request = new Request(url);
                    request.putExtra("type", "政治");
                    page.addTargetRequest(request);
                }
                if (page.getRequest().getUrl().contains("internation")) {
                    Request request = new Request(url);
                    request.putExtra("type", "国际");
                    page.addTargetRequest(request);
                }
                if (page.getRequest().getUrl().contains("economy")) {
                    Request request = new Request(url);
                    request.putExtra("type", "财经");
                    page.addTargetRequest(request);
                }
                if (page.getRequest().getUrl().contains("industry-science")) {
                    Request request = new Request(url);
                    request.putExtra("type", "科技");
                    page.addTargetRequest(request);
                }
                if (page.getRequest().getUrl().contains("military-affairs")) {
                    Request request = new Request(url);
                    request.putExtra("type", "军事");
                    page.addTargetRequest(request);
                }
            }
        } else {
            //正文&标题
            String title = "" + page.getHtml().getDocument().title().replace("'", "");
            page.putField("title", title);
            List<String> contents = page.getHtml().xpath("//div[@class='content all-txt']/p/allText()").all();
            StringBuffer text = new StringBuffer();
            for (String content : contents) {
                text.append("    " + content + "\n");
            }
            page.putField("text", text);

            //正文图片
            List<String> img_urls = page.getHtml().xpath("//div[@class='content all-txt']/p/img/@src").all();
            StringBuilder img_url = new StringBuilder();
            for (String img : img_urls) {
                img_url.append(img + ",");
            }

            //url链接
            String url = page.getRequest().getUrl();
            page.putField("url", url);

            //类型
            String type = "" + page.getRequest().getExtra("type");
            page.putField("type", type);

            //关键字
            List<String> labels = page.getHtml().xpath("//div[@class='key-word']/span/text()").all();
            StringBuffer keywords = new StringBuffer();
            for (int i = 1; i < (labels.size() - 2); i++) {
                keywords.append(labels.get(i) + " ");
            }
            page.putField("keywords", keywords);

            //有关作者
            String author = "" + page.getHtml().xpath("//div[@class='author-intro fix']/p/a/text()").toString();
            page.putField("author", author);
            String author_link = "" + page.getHtml().xpath("//div[@class='author-intro fix']/p/a/@href").toString();
            page.putField("author_link", author_link);
            String author_honour = "" + page.getHtml().xpath("//div[@class='author-intro fix']/p/span/text()").toString();
            page.putField("author_honour", author_honour);

            //热推文章链接
            List<String> hot_links = page.getHtml().xpath("//ul[@class='hot-list']/li/a/@href").all();
            page.putField("hot_links", hot_links);
            Integer link_flag = 0;
            if (null != page.getHtml().xpath("//ul[@class='hot-list']/li/a/@href").all()) {
                link_flag = 1;
            }
            List<String> hot_imgs = page.getHtml().xpath("//ul[@class='hot-list']/li/a/img/@src").all();
            page.putField("hot_imgs", hot_imgs);

            //日期
            String create = "" + page.getHtml().xpath("//div[@class='time fix']/span/text()").toString();
            page.putField("create", create);

            //持久化
            News news = new News();
            news.setTitle(title);
            news.setAuthor(author);
            news.setContent(text.toString());
            news.setCreate(create);
            news.setType(type);
            news.setUrl(url);
            news.setLinkFlag(link_flag);
            news.setImageUrl(img_url.toString());
            news.setKeyword(keywords.toString());
            news.setSource("观察者网");
            if (!"".equals(img_url.toString())) {
                page.putField("news", news);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new GuanchaProcessor())
                .addUrl("http://www.guancha.cn/politics/2017_05_11_407886.shtml")
                .addPipeline(new MysqlPipeline())
                .start();
    }
}

package com.aggregator.crawler.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class NetEaseMusicProcessor implements PageProcessor {
    //配置请求参数
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    public static void main(String[] args) {
        Spider.create(new NetEaseMusicProcessor())
                .addUrl("http://music.163.com/playlist?id=801091817")
                .start();

    }

    @Override
    public void process(Page page) {
        String a = "";
    }

    @Override
    public Site getSite() {
        return site;
    }
}

package com.aggregator.crawler.servlet;

import com.aggregator.crawler.pipeine.MysqlPipeline;
import com.aggregator.crawler.processor.GuanchaProcessor;
import com.aggregator.utils.BaseServlet;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Spider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bruce_Q
 * @create 2017-05-05 23:49
 **/
public class ServletTest extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Spider.create(new GuanchaProcessor())
//                .addUrl("http://www.guancha.cn/")
//                .addPipeline(MysqlPipeline.class.newInstance())
//                .run();
    }
}

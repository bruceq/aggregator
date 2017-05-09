package com.aggregator.crawler.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

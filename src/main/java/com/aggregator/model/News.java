package com.aggregator.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 新闻表
 *
 * @author Bruce_Q
 * @create 2017-05-05 15:14
 **/
@Table(name = "news")
public class News {
    //唯一标识，主键，自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标题
    private String title;

    //作者
    private String author;

    //类型
    private String type;

    //正文
    private String content;

    //创建时间
    private String time;

    //是否含有图片（没有0，有1）
    private String imageUrl;

    //是否含有相关链接（没有0，有1）
    private Integer linkFlag;

    //url地址
    private String url;

    //关键字
    private String keyword;

    //关键字
    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate() {
        return time;
    }

    public void setCreate(String time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLinkFlag() {
        return linkFlag;
    }

    public void setLinkFlag(Integer linkFlag) {
        this.linkFlag = linkFlag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

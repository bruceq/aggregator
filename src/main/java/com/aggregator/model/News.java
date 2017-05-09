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
    private String image_url;

    //是否含有相关链接（没有0，有1）
    private Integer link_flag;

    //url地址
    private String url;

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getLink_flag() {
        return link_flag;
    }

    public void setLink_flag(Integer link_flag) {
        this.link_flag = link_flag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.aggregator.mapper;

import com.aggregator.model.News;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Bruce_Q
 * @create 2017-05-05 16:31
 **/
public interface NewsMapper extends Mapper<News> {
    List<News> selectAllByType( @Param("type") String type);
}

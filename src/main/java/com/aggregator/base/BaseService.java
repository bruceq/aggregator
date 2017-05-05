package com.aggregator.base;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Bruce_Q
 * @create 2017-03-27 15:55
 **/
public interface BaseService<T> {

    /**
     *
     * @Title: save
     * @Description: 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    public abstract int insert(T entity);

    /**
     *
     * @Title: saveSelective
     * @Description: 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    public abstract int insertSelective(T entity);

    /**
     *
     * @Title: updateByPrimaryKey
     * @Description: 根据主键更新实体全部字段，null值会被更新
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    public abstract int updateByPrimaryKey(T entity);

    /**
     *
     * @Title: updateByPrimaryKeySelective
     * @Description: 根据主键更新属性不为null的值
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    public abstract int updateByPrimaryKeySelective(T entity);

    /**
     *
     * @Title: delete
     * @Description: 根据实体属性作为条件进行删除，查询条件使用等号
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    public abstract int delete(T entity);

    /**
     *
     * @Title: deleteByPrimaryKey
     * @Description: 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @author mengqch
     * @param @param key
     * @param @return
     * @return int
     * @throws
     */
    public abstract int deleteByPrimaryKey(Object key);

    /**
     *
     * @Title: select
     * @Description: 根据实体中的属性值进行查询，查询条件使用等号
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return List<T>
     * @throws
     */
    public abstract List<T> select(T entity);

    /**
     *
     * @Title: selectByPrimaryKey
     * @Description: 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @author mengqch
     * @param @param key
     * @param @return
     * @return T
     * @throws
     */
    public abstract T selectByPrimaryKey(Object key);

    /**
     *
     * @Description: 查询全部结果，select(null)方法能达到同样的效果
     * @author mengqch
     * @param @return
     * @return List<T>
     * @throws
     */
    public abstract List<T> selectAll();

    /**
     *
     * @Title: selectOne
     * @Description: 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return T
     * @throws
     */
    public abstract T selectOne(T entity);

    /**
     *
     * @Title: selectCount
     * @Description: 根据实体中的属性查询总数，查询条件使用等号
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return int
     * @throws
     */
    public abstract int selectCount(T entity);

    /**
     *
     * @Title: findByProperties
     * @Description: 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * @author mengqch
     * @param @param entity
     * @param @return
     * @return T
     * @throws
     */
    public abstract T findByProperties(T entity);

    /**
     *
     * @Title: selectPage
     * @Description: 单表分页查询，根据实体中的属性值进行查询，查询条件使用等号
     * @author mengqch
     * @param @param pageNum
     * @param @param pageSize
     * @param @return
     * @return List<T>
     * @throws
     */
    public abstract List<T> selectPage(int pageNum, int pageSize, T paramBean);

    public List<T> selectByExample(Example example);
    public List<T> selectByExampleWithPage(Example example,int offset,int count);

    public int selectCountByExample(Example example);

}
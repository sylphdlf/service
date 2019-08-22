package com.dlf.model.mapper;

import java.util.List;

public interface BaseMapper<T> {

    /**
     * 查询每页数量
     * @param t
     * @return
     */
    int countByParams(T t);

    /**
     * 查询列表
     * @param t
     * @return
     */
    List queryListByParams(T t);

    /**
     * 分页查询列表
     * @param t
     * @return
     */
    List queryPageByParams(T t);

    /**
     * 根据用户id查询列表
     * @param t
     * @return
     */
    List queryListByUser(T t);

    /**
     * 查询最后一个id
     * @return
     */
    Long selectLastId();

    /**
     * 根据参数来更新
     * @param t
     * @return
     */
    int updateByParams(T t);

    /**
     * 获取一条数据
     * @param t
     * @return
     */
    Object getOne(T t);
}

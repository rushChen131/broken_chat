package com.rush.chat.service.common;

import com.rush.chat.models.common.Order;
import com.rush.chat.models.common.RollPage;

import java.util.List;
import java.util.Map;

public interface IBaseService<T> {

    /**
     * 增加记录
     * @author
     * @param obj
     * @throws Exception
     */
    public Integer addBasic(T obj);

    /**
     * 修改记录
     * @author
     * @param obj
     * @throws Exception
     */
    public  Integer modifyBasic(T obj);

    /**
     * 删除记录
     * @author 
     * @param obj
     * @throws Exception
     */
    public Integer delBasic(T obj);

    /**
     * 根据主键查询记录
     * @author
     * @param seq
     * @return
     * @throws Exception
     */
    public Object findObjByKey(Object seq) throws Exception;

    /**
     * 根据条件查询记录
     * @author
     * @param params
     * @return
     * @throws Exception
     */
    public Object findObj(Map<String, Object> params) throws Exception;

    /**
     * 根据条件查询列表
     * @author
     * @param params
     * @param order
     * @throws Exception
     */
    public List findListByParams(Map<String, Object> params, Order order) throws Exception;



    /**
     * 根据条件查询列表
     * @param params
     * @param order
     * @param maxRecord
     * @return
     * @throws Exception
     */
    public List findListByParams(Map<String, Object> params, Order order, Integer maxRecord) throws Exception;

    /**
     * 根据条件查询 ，返回记录总数
     * @param params
     * @return
     */
    public Integer findCountByParams(Map<String, Object> params);

    /**
     * 根据条件查询 列表（分页查询）
     * @author
     * @param params
     * @param order
     * @param pageNum
     * @param pageSize 等于null 则使用默认值10
     * @return
     * @throws Exception
     */
    public RollPage findListPageByParams(Map<String, Object> params, Order order, Integer pageNum, Integer pageSize)throws Exception;


}
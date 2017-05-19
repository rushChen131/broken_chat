/**
 * 
 */
package com.rush.chat.dao.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * 基础类 MAPPER
 */
public interface IBaseMapper<T> {
	
    /**
     * 获取 序列号
     * @author
     * @return
     */
    Long getIdValue();
	
	/**
	 * 插入记录
	 * @author
	 * @param obj javaBean
	 * @return
	 */
    int insert(T obj);

    /**
     * 插入记录(有效字段,即非空字段)
     * @author
     * @param obj javaBean
     * @return
     */
    Integer insertSelective(T obj);

	/**
	 * 物理删除记录
	 * @author
	 * @param seq 主键iD
	 * @return
	 */
	<K>int deleteByPrimaryKey(K seq);


    /**
     * 根据ids更新一批记录
     * @param params sql条件查询
     * @return
     */
    int updateByIds(@Param(value = "params") Map<String, Object> params);
	
    /**
     * 更新记录
     * @author
     * @param obj javaBean
     * @return 更新次数
     */
    int updateByPrimaryKey(T obj);
    
    /**
     * 更新记录(有效字段,即非空字段)
     * @author
     * @param obj javaBean
     * @return
     */
    int updateByPrimaryKeySelective(T obj);

    /**
     * 根据主键 返回记录
     * @author
     * @param seq 主键ID
     * @return javaBean
     */
    <K>T selectByPrimaryKey(K seq);

    /**
     * 根据 条件返回记录
     * @author
     * @param params sql条件查询
     * @return JavaBean列表
     */
    T selectByParams(@Param(value = "params") Map<String, Object> params);

    
    /**
     * 查询 符合条件的记录总数
     * @author
     * @param params sql条件查询
     * @return   符合条件的记录总数
     */
    int selectCountByParams(@Param(value = "params") Map<String, Object> params);

    /**
     * 查询 符合条件的记录总数 （联合查询）
     * @author
     * @param params sql条件查询
     * @return  符合条件的记录总数
     */
    int selectUnionCountByParams(@Param(value = "params") Map<String, Object> params);
    
    /**
     * 分页查询 记录，分页条件为null，返回所有
     * @author
     * @param params 查询条件
     * @param pageOffset 开始游标
     * @param pageSize 每页显示的数量z
     * @param orderParam 排序参数
     * @return JavaBean列表
     */
    List<T> selectListByParams(@Param(value = "params") Map<String, Object> params,
                               @Param(value = "pageOffset") Integer pageOffset,
                               @Param(value = "pageSize") Integer pageSize,
                               @Param(value = "orderParam") String orderParam);
    /**
     * 分页查询 记录，分页条件为null，返回所有，联合查询
     * @author
     * @param params 查询条件
     * @param pageOffset 开始游标
     * @param pageSize 每页显示的数量z
     * @param orderParam 排序参数
     * @return JavaBean列表
     */
    List<T> selectUnionListByParams(@Param(value = "params") Map<String, Object> params,
                                    @Param(value = "pageOffset") Integer pageOffset,
                                    @Param(value = "pageSize") Integer pageSize,
                                    @Param(value = "orderParam") String orderParam);
	
}

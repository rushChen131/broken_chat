package com.rush.chat.service.common;

import com.rush.chat.dao.mapper.IBaseMapper;
import com.rush.chat.models.common.Order;
import com.rush.chat.models.common.RollPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {
    protected Integer pageSizeDefault = 10;

    protected IBaseMapper baseMapper;

    public void setBaseMapper(IBaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public abstract  Integer addBasic(T obj);

    @Override
    public abstract  Integer modifyBasic(T obj);

    @Override
    public  abstract Integer delBasic(T obj);

    @Override
    public Object findObjByKey(Object seq) throws Exception {
        return baseMapper.selectByPrimaryKey(seq);
    }

    @Override
    public Object findObj(Map<String, Object> params) throws Exception {

        return baseMapper.selectByParams(params);
    }

    @Override
    public List findListByParams(Map<String, Object> params, Order order) throws Exception {
        return baseMapper.selectListByParams(params,null,null,order==null?null:order.toString());
    }
    
    @Override
    public Integer findCountByParams(Map<String, Object> params) {
        return baseMapper.selectCountByParams(params);
    }

    @Override
    public List findListByParams(Map<String, Object> params, Order order, Integer maxRecord) throws Exception {
       return baseMapper.selectListByParams(params, 0, maxRecord, order==null?null:order.toString());
    }

    @Override
    public RollPage findListPageByParams(Map<String, Object> params, Order order, Integer pageNum, Integer pageSize) throws Exception {
        Integer recordSum= baseMapper.selectCountByParams(params);
        Integer pageSizeCustom = this.pageSizeDefault;
        RollPage rollPage=new RollPage();
        rollPage.iTotalRecords=recordSum;
        rollPage.pageSize=pageSize==null?pageSizeCustom:pageSize;
        rollPage.pageNum=pageNum==null?1:pageNum;

        Integer pageOffset=(rollPage.pageNum - 1) * rollPage.pageSize;

        if (recordSum>0) {
            rollPage.recordList =baseMapper.selectListByParams(params, pageOffset, rollPage.pageSize, order == null ? null : order.toString());
        }
        else{
            rollPage.recordList = new ArrayList();
        }

        return rollPage;
    }


}

package com.rush.chat.models.common;

import java.util.List;


public class RollPage<T> {

    public List<T> recordList; //记录列表

    public Integer iTotalRecords; //记录总数

    public Integer pageSize; //分页参数

    public Integer pageNum; //当前页数

    public Integer getPageSum() {
        return (int) Math.ceil(this.iTotalRecords / this.pageSize)+ ((this.iTotalRecords % this.pageSize==0)?0:1);
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

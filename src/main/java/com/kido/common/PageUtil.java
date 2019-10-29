package com.kido.common;

public class PageUtil {
    private int pageSize;//每页的数量
    private int totalCount;//消息总数
    private int startRow;    //起始行位置
    private int pageNo;      //当前页数
    private int totalPageCount; //总页数

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int pageNo,int pageSize) {
        //动态获取起始行位置
        this.startRow = (pageNo-1)*pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalCount,int pageSize) {
        //动态计算总页数
        this.totalPageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
    }

    //定义有参构造方法

    public PageUtil(int totalCount, int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageNo = pageNo;
        this.setStartRow(pageNo,pageSize);
        this.setTotalPageCount(totalCount,pageSize);
    }

}

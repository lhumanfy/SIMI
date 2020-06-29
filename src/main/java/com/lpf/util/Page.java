package com.lpf.util;

public class Page {
    //当前页数
    private int curPage = 1;
    //数据总条数
    private int totalCount = 0;
    //每页显示条数
    private int pageSize = 0;
    //总页数=(数据总条数/每页数据条数)+1
    private int totalPage = 1;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        if (this.totalCount % this.pageSize == 0) {
            this.totalPage = this.totalCount / this.pageSize;
        } else if (this.totalCount % this.pageSize > 0) {
            this.totalPage = this.totalCount / this.pageSize + 1;
        }
    }
}

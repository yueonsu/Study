package com.koreait.web.model;

public class BoardPageVO {
    private int idx;
    private int recordCnt;
    private int page;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getRecordCnt() {
        return recordCnt;
    }

    public void setRecordCnt(int recordCnt) {
        this.recordCnt = recordCnt;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.idx = (page - 1) * recordCnt;
    }
}

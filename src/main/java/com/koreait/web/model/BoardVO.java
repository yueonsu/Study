package com.koreait.web.model;

public class BoardVO {
    private int id;
    private String title;
    private int writer;
    private String rdt;
    private String mdt;
    private String contents;
    private String category;
    private int price;

    private String writerNm;

    public String getWriterNm() {
        return writerNm;
    }

    public void setWriterNm(String writerNm) {
        this.writerNm = writerNm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWriter() {
        return writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    public String getRdt() {
        return rdt;
    }

    public void setRdt(String rdt) {
        this.rdt = rdt;
    }

    public String getMdt() {
        return mdt;
    }

    public void setMdt(String mdt) {
        this.mdt = mdt;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

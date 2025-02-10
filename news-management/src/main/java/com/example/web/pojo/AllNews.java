package com.example.web.pojo;

import java.sql.Timestamp;

public class AllNews {
    private int id;
    private String title;
    private String author;
    private String type;
    private String content;
    private Timestamp time;
    private String actualTime;

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public AllNews() {
    }

    public AllNews(int id, String title, String author, String type, String content, Timestamp time) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.content = content;
        this.time = time;
    }

    @Override
    public String toString() {
        return "AllNews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", time='" + actualTime + '\'' +
                '}';
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

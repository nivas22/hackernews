package com.example.hackernews.modal;

import java.util.Arrays;

public class News {

    private Long id;
    private String by;
    private String[] kids;
    private Long parent;
    private String text;
    private String title;
    private String score;
    private Long time;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String[] getKids() {
        return kids;
    }

    public void setKids(String[] kids) {
        this.kids = kids;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", by='" + by + '\'' +
                ", kids=" + Arrays.toString(kids) +
                ", parent=" + parent +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                '}';
    }
}

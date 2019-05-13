package com.example.hackernews.modal;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {


    @Test
    public void setTitle() {
        News news = new News();
        news.setTitle("Title");
        assertEquals("Title Test", "Title", news.getTitle());
    }



    @Test
    public void setScore() {
        News news = new News();
        news.setScore("100");
        assertEquals("score Test", "100", news.getScore());
    }

    @Test
    public void setBy() {
        News news = new News();
        news.setBy("John");
        assertEquals("By Test", "John", news.getBy());
    }

    @Test
    public void setKids() {
        News news = new News();
        news.setKids(new String[]{"One", "Two", "Three"});
        assertEquals("Kids Test", 3, news.getKids().length);
    }

    @Test
    public void setParent() {
        News news = new News();
        news.setParent(12345L);
        assertEquals(Long.valueOf(12345L), news.getParent());
    }

    @Test
    public void setText() {
        News news = new News();
        news.setText("Text");
        assertEquals("Text Test", "Text", news.getText());
    }

    @Test
    public void setTime() {
        News news = new News();
        news.setTime(12345678L);
        assertEquals(Long.valueOf(12345678), news.getTime());
    }

    @Test
    public void setType() {
        News news = new News();
        news.setType("Type");
        assertEquals("Type Test", "Type", news.getType());
    }
}
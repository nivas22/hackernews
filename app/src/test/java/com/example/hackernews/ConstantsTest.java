package com.example.hackernews;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstantsTest {

    public void Constants(){}

    @Test
    public void testHttpResponseCode() {
        assertEquals(1, Constants.SUCCESS);
        assertEquals(2, Constants.INTERNET_DISCONNECTED);
        assertEquals(3, Constants.FAILURE);
    }

    @Test
    public void testApiCalls() {
        assertEquals("https://hacker-news.firebaseio.com/v0/topstories.json", Constants.GET_TOP_STORIES);
        assertEquals("https://hacker-news.firebaseio.com/v0/item/", Constants.GET_STORY_ITEM);
    }

}
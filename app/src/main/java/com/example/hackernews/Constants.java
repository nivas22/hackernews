package com.example.hackernews;

import okhttp3.MediaType;

public class Constants {

    public static final int SUCCESS = 1;
    public static final int INTERNET_DISCONNECTED = 2;
    public static final int FAILURE = 3;
    public static final int INVALID_CREDS = 4;
    public static final int SESSION_EXPIRED = 5;
    public static final int RELOAD = 6;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static final String GET_TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json";

    public static final String GET_STORY_ITEM = "https://hacker-news.firebaseio.com/v0/item/";


}

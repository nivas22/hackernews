package com.example.hackernews;

import okhttp3.MediaType;

public class Constants {

    //HTTP Status Returns
    public static final int SUCCESS = 1;
    public static final int INTERNET_DISCONNECTED = 2;
    public static final int FAILURE = 3;

    //API Calls
    public static final String GET_TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json";
    public static final String GET_STORY_ITEM = "https://hacker-news.firebaseio.com/v0/item/";


}

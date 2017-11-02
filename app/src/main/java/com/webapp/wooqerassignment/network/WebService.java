package com.webapp.wooqerassignment.network;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class WebService {

    public static final int FIREBASE_SERVICE        =       1;
    public static final int RETROFIT_SERVICE        =       2;


    public static final int REQ_SEND                =       1;
    public static final int RED_FAIL                =       3;
    public static final int REQ_SUCCESS             =       4;


    public static final String SERVICE_BASE_URL             =   "https://hacker-news.firebaseio.com";
    public static final String SERVICE_VERSION              =   "/v0";
    public static final String SERVICE_TOP_STORIES          =   "/topstories";
    public static final String SERVICE_STORY_ITEM           =   "/item";

    public static synchronized String getServiceForTopStrories() {
        return SERVICE_BASE_URL + SERVICE_VERSION + SERVICE_TOP_STORIES;
    }

    public static synchronized String getServiceForStoryItem() {
        return SERVICE_BASE_URL + SERVICE_VERSION + SERVICE_STORY_ITEM;
    }

}

package com.webapp.wooqerassignment.constants;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 *
 * class to define all the app level cotsnats
 *
 */

public class AppConstants {

    public static final String APP_TAG         =    "Wooqer Assignment";
    public static final String TAG_PAGE_NO     =    "PageNumber";
    public static final int PAGE_NO_ZERRO      =    0;
    public static final int ARTICLES_PER_PAGE  =    10;
    public static final int ARTICLES_LIST_MAX  =    50;
    public static final String TAG_URL         =    "url";
    public static final String TAG_ID          =    "id";
    public static final String TAG_SEARCH_KEY  =    "search_key";

    /**
     * Webservice service response tag names
     */
    public static class ArticleRespTag {

        public static final String TAG_BY             = "by";
        public static final String TAG_ID             = "id";
        public static final String TAG_TYPE           = "type";
        public static final String TAG_TIME           = "time";
        public static final String TAG_SCORE          = "score";
        public static final String TAG_TITLE          = "title";
        public static final String TAG_URL            = "url";
    }

    /**
     * Data base flag for read status states
     */
    public static class ReadStatus {
        public static final int READ           =       1;
        public static final int UN_READ        =       0;
    }
}
package com.webapp.wooqerassignment.database;

import android.content.ContentValues;
import android.content.Context;

import com.webapp.wooqerassignment.constants.AppConstants;
import com.webapp.wooqerassignment.database.coloumn.ArticleTableColoumn;
import com.webapp.wooqerassignment.model.Article;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class DatabaseManager {

    private static DatabaseManager mDatabaseManager;

    private StringBuilder queryBuilder;

    private DatabaseManager() {

    }

    public static DatabaseManager getInstance() {
        if(mDatabaseManager == null) {
            mDatabaseManager = new DatabaseManager();
        }
        return mDatabaseManager;
    }

    public void modifyArticle(Article article, Context context) {
        if(updateArticle(article, context) == 0) {
            insertArticle(article,context);
        }
    }

    public boolean updateReadStatus(long id, Context context) {
        ContentValues values = new ContentValues();
        values.put(ArticleTableColoumn.READ_STATUS, AppConstants.ReadStatus.READ);
        String where = ArticleTableColoumn.ARTICLE_ID + SqlConstants.EQUALS_ARG;
        int updatedRow = context.getContentResolver().update(ProviderUri.URI_ARTICLES,
                values, where , new String[] {String.valueOf(id)});
        return updatedRow > 0 ? true : false;
    }

    private int updateArticle(Article article, Context context) {
        ContentValues values = new ContentValues();
        values.put(ArticleTableColoumn.ARTICLE_ID, article.getId());
        values.put(ArticleTableColoumn.TITLE, article.getTitle());
        values.put(ArticleTableColoumn.TIME, article.getTime());
        values.put(ArticleTableColoumn.SCORE, article.getScore());
        values.put(ArticleTableColoumn.AUTHOR, article.getBy());
        values.put(ArticleTableColoumn.TYPE, article.getType());
        values.put(ArticleTableColoumn.URL, article.getUrl());
        String where = ArticleTableColoumn.ARTICLE_ID + SqlConstants.EQUALS_ARG;
        return context.getContentResolver().update(ProviderUri.URI_ARTICLES,
                values, where , new String[] {String.valueOf(article.getId())});
    }

    public void insertArticle(Article article, Context context) {
        ContentValues values = new ContentValues();
        values.put(ArticleTableColoumn.ARTICLE_ID, article.getId());
        values.put(ArticleTableColoumn.TITLE, article.getTitle());
        values.put(ArticleTableColoumn.TIME, article.getTime());
        values.put(ArticleTableColoumn.SCORE, article.getScore());
        values.put(ArticleTableColoumn.AUTHOR, article.getBy());
        values.put(ArticleTableColoumn.TYPE, article.getType());
        values.put(ArticleTableColoumn.URL, article.getUrl());
        context.getContentResolver().insert(ProviderUri.URI_ARTICLES, values);
    }

}

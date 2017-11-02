package com.webapp.wooqerassignment.database.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.webapp.wooqerassignment.constants.AppConstants;
import com.webapp.wooqerassignment.database.SqlConstants;
import com.webapp.wooqerassignment.database.coloumn.ArticleTableColoumn;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class TableArticle {

    public static final String NAME = "article";

    private static final String DROP_OLD_TABLE = SqlConstants.DROP_TABLE + NAME;

    public static void create(final SQLiteDatabase db) {

        final String tableBuilder = SqlConstants.CREATE_TABLE + NAME + SqlConstants.PARANTHESIS_OPEN
                + BaseColumns._ID + SqlConstants.DATA_INT_PK
                + ArticleTableColoumn.TITLE + SqlConstants.DATA_TEXT
                + ArticleTableColoumn.AUTHOR + SqlConstants.DATA_TEXT
                + ArticleTableColoumn.TYPE + SqlConstants.DATA_TEXT
                + ArticleTableColoumn.URL + SqlConstants.DATA_TEXT
                + ArticleTableColoumn.ARTICLE_ID + SqlConstants.DATA_INTEGER
                + ArticleTableColoumn.READ_STATUS + SqlConstants.DATA_INTEGER_DEFAULT +
                        AppConstants.ReadStatus.UN_READ + SqlConstants.COMMA
                + ArticleTableColoumn.TIME + SqlConstants.DATA_INTEGER
                + ArticleTableColoumn.SCORE + SqlConstants.DATA_INTEGER_END
                + SqlConstants.SEMI_COLON;
        db.execSQL(tableBuilder);
    }

    public static void upgrade(final SQLiteDatabase database, final int oldVersion, final int
            newVersion) {
        database.execSQL(DROP_OLD_TABLE);
        create(database);
    }
}

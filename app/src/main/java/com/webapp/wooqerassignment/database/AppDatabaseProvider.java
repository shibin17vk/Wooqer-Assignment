package com.webapp.wooqerassignment.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.webapp.wooqerassignment.database.tables.TableArticle;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 *
 * Provider class to difine the db authority
 *
 */

public class AppDatabaseProvider extends ContentProvider {

    public static final String DB_NAME = "app_database.db";

    public static final int CONTENT_TYPE_ARTICLE_LIST       =       100;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(DatabaseProviderConstants.AUTHORITY, TableArticle.NAME, CONTENT_TYPE_ARTICLE_LIST);
    }

    private DBOpenHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = DBOpenHelper.getInstance(getContext());
        return true;
    }


    @Override
    public int delete(final Uri uri, final String selection, final String[] selectionArgs) {
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        int count = 0;

        final String tableName = getTableName(URI_MATCHER.match(uri));
        try {
            count = database.delete(tableName, selection, selectionArgs);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public String getType(final Uri uri) {
        return null;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull final Uri uri, @Nullable final String[] projection, @Nullable final String selection,
            @Nullable final String[] selectionArgs,@Nullable final String sortOrder) {
        Cursor cursor;
        final SQLiteDatabase database = dbHelper.getReadableDatabase();
        final String limit = uri.getQueryParameter(SqlConstants.LIMIT);
        final String tableName = getTableName(URI_MATCHER.match(uri));
        cursor = database.query(false, tableName, projection, selection, selectionArgs, null, null, sortOrder, limit);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(final Uri uri, final ContentValues values) {

        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        long rowId = -1;

        final String tableName = getTableName(URI_MATCHER.match(uri));
        try {
            rowId = database.insert(tableName, null, values);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        if (rowId != -1) {
            final Uri insertUri = ContentUris.withAppendedId(uri, rowId);
            return insertUri;
        }
        return null;

    }

    @Override
    public int update(final Uri uri, final ContentValues values, final String selection, final String[] selectionArgs) {

        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        int count = 0;

        final String tableName = getTableName(URI_MATCHER.match(uri));

        try {
            count = database.update(tableName, values, selection, selectionArgs);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int bulkInsert(final Uri uri, final ContentValues[] values) {

        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        final String tableName = getTableName(URI_MATCHER.match(uri));
        int numInserted = 0;
        db.beginTransaction();

        try {

            for (final ContentValues aValue : values) {

                if (aValue.size() == 0) {
                    break;
                }
                db.insert(tableName, null, aValue);
            }
            numInserted = values.length;
            db.setTransactionSuccessful();
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return numInserted;
    }

    /**
     * Call to get the table name associated to given contentType
     */
    private static String getTableName(final int contentType) {

        switch (contentType) {
            case CONTENT_TYPE_ARTICLE_LIST :
                return TableArticle.NAME;
            default:
                return null;

        }
    }

}

package com.webapp.wooqerassignment.database;

import android.net.Uri;

import com.webapp.wooqerassignment.database.tables.TableArticle;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class ProviderUri {

    public static final Uri URI_ARTICLES = Uri.parse(DatabaseProviderConstants.URI_PREFIX + TableArticle.NAME);

}

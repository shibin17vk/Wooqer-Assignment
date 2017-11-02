package com.webapp.wooqerassignment.network;

import com.firebase.client.DataSnapshot;
import com.webapp.wooqerassignment.constants.AppConstants;
import com.webapp.wooqerassignment.model.Article;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 *
 * TO parse the webservice responses.
 *
 */

public class DataParcer {

    public static synchronized ArrayList<Long> getArticleIds(DataSnapshot dataSnapshot) {
        ArrayList<Long> articlesIdLis = null;
        if(dataSnapshot != null && dataSnapshot.getChildrenCount() > 0) {
            articlesIdLis = new ArrayList<>();
            for (int i = 0; i < dataSnapshot.getChildrenCount() && i < AppConstants.ARTICLES_LIST_MAX; i++) {
                Long id = (Long) dataSnapshot.child(String.valueOf(i)).getValue();
                articlesIdLis.add(id);
            }
        }
        return articlesIdLis;
    }

    public static synchronized Article getArticle(DataSnapshot dataSnapshot) {
        Article article = new Article();
        if(dataSnapshot != null && dataSnapshot.getChildrenCount() > 0) {
            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
            if(map != null && map.size() > 0) {
                if(map.containsKey(AppConstants.ArticleRespTag.TAG_TITLE)) {
                    article.setTitle((String) map.get(AppConstants.ArticleRespTag.TAG_TITLE));
                }
                if(map.containsKey(AppConstants.ArticleRespTag.TAG_TYPE)) {
                    article.setType((String) map.get(AppConstants.ArticleRespTag.TAG_TYPE));
                }
                if(map.containsKey(AppConstants.ArticleRespTag.TAG_SCORE)) {
                    article.setScore((long) map.get(AppConstants.ArticleRespTag.TAG_SCORE));
                }
                if(map.containsKey(AppConstants.ArticleRespTag.TAG_TIME)) {
                    article.setTime((long) map.get(AppConstants.ArticleRespTag.TAG_TIME));
                }
                if(map.containsKey(AppConstants.ArticleRespTag.TAG_BY)) {
                    article.setBy((String) map.get(AppConstants.ArticleRespTag.TAG_BY));
                }
                if(map.containsKey(AppConstants.ArticleRespTag.TAG_URL)) {
                    article.setUrl((String) map.get(AppConstants.ArticleRespTag.TAG_URL));
                }
                if(map.containsKey(AppConstants.ArticleRespTag.TAG_ID)) {
                    article.setId((long) map.get(AppConstants.ArticleRespTag.TAG_ID));
                }
            }
        }
        return article;
    }

}

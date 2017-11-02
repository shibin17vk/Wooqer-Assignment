package com.webapp.wooqerassignment.topstories.controller;

import android.content.Context;

import com.webapp.wooqerassignment.constants.AppConstants;
import com.webapp.wooqerassignment.database.DatabaseManager;
import com.webapp.wooqerassignment.model.Article;
import com.webapp.wooqerassignment.network.WebService;
import com.webapp.wooqerassignment.network.WebServiceCallbackListener;
import com.webapp.wooqerassignment.network.WebServiceExecutor;
import com.webapp.wooqerassignment.network.WebServiceManager;
import com.webapp.wooqerassignment.network.WebserviceRequestEntity;
import com.webapp.wooqerassignment.network.WebserviceResponseEntity;
import com.webapp.wooqerassignment.topstories.interfaces.ActivityControllCallback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 */

public class HackerNewsListActivityController implements WebServiceCallbackListener {

    private Context context;
    private ActivityControllCallback mActivityControllCallback;
    private ArrayList<Long> topArticlesIdList;
    private int currentPageNumber = AppConstants.PAGE_NO_ZERRO;
    private HashSet<Long> requestQueIdSet = null;

    public HackerNewsListActivityController(Context context,ActivityControllCallback activityControllCallback) {
        this.context = context;
        this.requestQueIdSet = new HashSet<>();
        this.mActivityControllCallback = activityControllCallback;
    }

    public void getTopArticlesIdList() {
        WebserviceRequestEntity webserviceRequestEntity = new WebserviceRequestEntity();
        webserviceRequestEntity.setServiceUrl(WebService.getServiceForTopStrories());
        WebServiceExecutor webServiceExecutor = WebServiceManager.getInstance().getWebservice(WebService.FIREBASE_SERVICE,this);
        webServiceExecutor.execute(webserviceRequestEntity);
    }

    public void loadMoreArticles() {
        getTopArticlesList(currentPageNumber);
    }

    public void getTopArticlesList(int pageNumber) {
        ArrayList<Long> articlesId = getArticleIdsOf(pageNumber);
        if(articlesId != null && articlesId.size() > 0) {
            for(Long id : articlesId) {
                requestQueIdSet.add(id);
                String serviceUrl = WebService.getServiceForStoryItem() + "/" + id;
                WebserviceRequestEntity webserviceRequestEntity = new WebserviceRequestEntity();
                webserviceRequestEntity.setServiceUrl(serviceUrl);
                webserviceRequestEntity.addTag(AppConstants.TAG_ID, id);
                WebServiceExecutor webServiceExecutor = WebServiceManager.getInstance().getWebservice(WebService.FIREBASE_SERVICE,this);
                webServiceExecutor.execute(webserviceRequestEntity);
            }
        }
    }

    public boolean isLoadingQue() {
        return requestQueIdSet.size() > 0 ;
    }

    private ArrayList<Long> getArticleIdsOf(int pageSize) {
        ArrayList<Long> arrayList = null;
        if(topArticlesIdList != null && topArticlesIdList.size() > 0) {
            arrayList = new ArrayList<>();
            int artilcesListSize = topArticlesIdList.size();
            for(int index = pageSize; index < artilcesListSize; index ++) {
                arrayList.add(topArticlesIdList.get(index));
                if(arrayList.size() >= AppConstants.ARTICLES_PER_PAGE) {
                    break;
                }
            }
        }
        return arrayList;
    }

    private void removeRequestQueItem(long id) {
        if(requestQueIdSet.contains(id)) {
            requestQueIdSet.remove(id);
        }
    }

    @Override
    public void onWebserviceCall(final WebserviceResponseEntity webserviceResponseEntity, final int reqState) {
        mActivityControllCallback.onWebserviceCall(webserviceResponseEntity, reqState);

        if(webserviceResponseEntity.getServiceUrl().equalsIgnoreCase(WebService.getServiceForTopStrories())) {
            onWebserviceGetTopStoriesIdList(webserviceResponseEntity,reqState);
        }
        else if(webserviceResponseEntity.getServiceUrl().contains(WebService.getServiceForStoryItem())) {
            onWebserviceGetArticleItem(webserviceResponseEntity, reqState);
        }
    }

    private void onWebserviceGetArticleItem(final WebserviceResponseEntity webserviceResponseEntity, int reqState) {
        switch (reqState) {
            case WebService.REQ_SEND:
                break;

            case WebService.REQ_SUCCESS:
                onSuccessGetArticleItem(webserviceResponseEntity);
                break;

            case WebService.RED_FAIL:
                long id = (long) webserviceResponseEntity.getTag(AppConstants.TAG_ID);
                removeRequestQueItem(id);
                break;
        }
    }

    private void onWebserviceGetTopStoriesIdList(final WebserviceResponseEntity webserviceResponseEntity, int reqState) {
        switch (reqState) {
            case WebService.REQ_SEND:
                break;

            case WebService.REQ_SUCCESS:
                onSuccessGetTopStoriesIds(webserviceResponseEntity);
                break;

            case WebService.RED_FAIL:
                break;
        }
    }

    private void onSuccessGetArticleItem(WebserviceResponseEntity webserviceResponseEntity) {
        if(webserviceResponseEntity.getRespParam() != null && webserviceResponseEntity.getRespParam().length > 0) {
            if(webserviceResponseEntity.getRespParam()[0] instanceof Article) {
                currentPageNumber ++;
                Article article = (Article) webserviceResponseEntity.getRespParam()[0];
                DatabaseManager.getInstance().modifyArticle(article,context);
                removeRequestQueItem(article.getId());
            }
        }
    }

    private void onSuccessGetTopStoriesIds(WebserviceResponseEntity webserviceResponseEntity) {
        if(webserviceResponseEntity.getRespParam() != null && webserviceResponseEntity.getRespParam().length > 0) {
            if(webserviceResponseEntity.getRespParam()[0] instanceof List) {
                topArticlesIdList = (ArrayList<Long>) webserviceResponseEntity.getRespParam()[0];
                currentPageNumber = AppConstants.PAGE_NO_ZERRO;
                getTopArticlesList(currentPageNumber);
            }
        }
    }
}

package com.webapp.wooqerassignment.network.firbase;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.webapp.wooqerassignment.exception.AppException;
import com.webapp.wooqerassignment.model.Article;
import com.webapp.wooqerassignment.network.DataParcer;
import com.webapp.wooqerassignment.network.WebService;
import com.webapp.wooqerassignment.network.WebServiceCallbackListener;
import com.webapp.wooqerassignment.network.WebServiceExecutor;
import com.webapp.wooqerassignment.network.WebserviceRequestEntity;
import com.webapp.wooqerassignment.network.WebserviceResponseEntity;

import java.util.ArrayList;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 *
 * fire base excecutor class to handle all the firebase api request calls and the response will be given
 * back through @WebServiceCallbackListener
 *
 */

public class FirebaseWebserviceExcecutor extends WebServiceExecutor {

    public FirebaseWebserviceExcecutor(WebServiceCallbackListener webServiceCallbackListener) {
        super(webServiceCallbackListener);
    }

    @Override
    public void execute(final WebserviceRequestEntity webserviceRequestEntity) {
        final Firebase firebaseService = new Firebase(webserviceRequestEntity.getServiceUrl());
        final WebserviceResponseEntity webserviceResponseEntity = new WebserviceResponseEntity();
        webserviceResponseEntity.setServiceUrl(webserviceRequestEntity.getServiceUrl());
        webserviceResponseEntity.addAllTag(webserviceRequestEntity.getTags());
        mWebServiceCallbackListener.onWebserviceCall(webserviceResponseEntity,WebService.REQ_SEND);

        firebaseService.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                firebaseService.removeEventListener(this);
                try {
                    getResponseData(dataSnapshot,webserviceResponseEntity);
                    mWebServiceCallbackListener.onWebserviceCall(webserviceResponseEntity,WebService.REQ_SUCCESS);
                }
                catch (AppException ex) {
                    ex.printStackTrace();
                    webserviceResponseEntity.setAppException(ex);
                    mWebServiceCallbackListener.onWebserviceCall(webserviceResponseEntity,WebService.RED_FAIL);
                }
            }

            @Override
            public void onCancelled(final FirebaseError firebaseError) {
                firebaseService.removeEventListener(this);
                AppException appException = new AppException(firebaseError.getCode(),firebaseError.getMessage());
                webserviceResponseEntity.setAppException(appException);
                mWebServiceCallbackListener.onWebserviceCall(webserviceResponseEntity,WebService.RED_FAIL);
            }
        });
    }

    private void getResponseData(final DataSnapshot dataSnapshot, final WebserviceResponseEntity webserviceResponseEntity) throws AppException{

        if(webserviceResponseEntity.getServiceUrl().equalsIgnoreCase(WebService.getServiceForTopStrories())) {
            ArrayList<Long> articlesIdList = DataParcer.getArticleIds(dataSnapshot);
            if(articlesIdList != null && articlesIdList.size() > 0) {
                webserviceResponseEntity.addRespParam(articlesIdList);
            } else {
                throw new AppException(0,"No articles are available");
            }
        }
        else if(webserviceResponseEntity.getServiceUrl().contains(WebService.getServiceForStoryItem())) {
            Article article = DataParcer.getArticle(dataSnapshot);
            if(article != null) {
                webserviceResponseEntity.addRespParam(article);
            } else {
                throw new AppException(0,"Article Can not be loaded !");
            }
        }
    }
}

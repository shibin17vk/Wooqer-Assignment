package com.webapp.wooqerassignment.network;

import com.webapp.wooqerassignment.network.firbase.FirebaseWebserviceExcecutor;
import com.webapp.wooqerassignment.network.retrofit.RetrofitWebserviceExcecutor;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 *
 * class to provide the required webservice provider (Factory design pattern).
 * Service provider will be different depends on the situation.
 * Few apis in this application are supported with Google Firebase api service and few are with retrofit.
 *
 */

public class WebServiceManager {

    private static WebServiceManager mWebServiceManager;

    private WebServiceManager() {

    }

    public static synchronized WebServiceManager getInstance() {
        if(mWebServiceManager == null) {
            mWebServiceManager = new WebServiceManager();
        }
        return mWebServiceManager;
    }

    public WebServiceExecutor getWebservice(int service, WebServiceCallbackListener webServiceCallbackListener) {

        switch (service) {

            case WebService.FIREBASE_SERVICE:
                return new FirebaseWebserviceExcecutor(webServiceCallbackListener);

            case WebService.RETROFIT_SERVICE:
                return new RetrofitWebserviceExcecutor(webServiceCallbackListener);

            default:
                return null;

        }

    }

}

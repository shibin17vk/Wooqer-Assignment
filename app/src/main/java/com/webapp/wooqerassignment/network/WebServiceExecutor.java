package com.webapp.wooqerassignment.network;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 */

public abstract class WebServiceExecutor {

    protected WebServiceCallbackListener mWebServiceCallbackListener;

    protected WebServiceExecutor(WebServiceCallbackListener webServiceCallbackListener) {
        mWebServiceCallbackListener = webServiceCallbackListener;
    }

    public abstract void execute(WebserviceRequestEntity webserviceRequestEntity);

}

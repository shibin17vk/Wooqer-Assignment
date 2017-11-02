package com.webapp.wooqerassignment.network;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 *
 * base class to define the signatures of the services being provided by different web services excecutors
 *
 */

public abstract class WebServiceExecutor {

    protected WebServiceCallbackListener mWebServiceCallbackListener;

    protected WebServiceExecutor(WebServiceCallbackListener webServiceCallbackListener) {
        mWebServiceCallbackListener = webServiceCallbackListener;
    }

    public abstract void execute(WebserviceRequestEntity webserviceRequestEntity);

}

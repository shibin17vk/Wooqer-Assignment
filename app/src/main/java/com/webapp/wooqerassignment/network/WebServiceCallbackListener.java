package com.webapp.wooqerassignment.network;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 */

public interface WebServiceCallbackListener {

    public void onWebserviceCall(WebserviceResponseEntity webserviceResponseEntity, int reqState);

}

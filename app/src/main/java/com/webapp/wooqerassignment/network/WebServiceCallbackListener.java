package com.webapp.wooqerassignment.network;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 *
 * call back to retrieve the webservice response from Webservice exceutors to working Context
 *
 */

public interface WebServiceCallbackListener {

    public void onWebserviceCall(WebserviceResponseEntity webserviceResponseEntity, int reqState);

}

package com.webapp.wooqerassignment.topstories.interfaces;

import com.webapp.wooqerassignment.network.WebserviceResponseEntity;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public interface ActivityControllCallback {

    public void onWebserviceCall(WebserviceResponseEntity webserviceResponseEntity, int state);

}

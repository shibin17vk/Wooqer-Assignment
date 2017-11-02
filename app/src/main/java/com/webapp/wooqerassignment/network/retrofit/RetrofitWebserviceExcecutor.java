package com.webapp.wooqerassignment.network.retrofit;

import com.webapp.wooqerassignment.network.WebServiceCallbackListener;
import com.webapp.wooqerassignment.network.WebServiceExecutor;
import com.webapp.wooqerassignment.network.WebserviceRequestEntity;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 *
 *
 * Retrofit excecutor class to handle all the firebase api request calls and the response will be given
 * back through @WebServiceCallbackListener
 *
 */

public class RetrofitWebserviceExcecutor extends WebServiceExecutor {

    public RetrofitWebserviceExcecutor(
            final WebServiceCallbackListener webServiceCallbackListener) {
        super(webServiceCallbackListener);
    }

    @Override
    public void execute(final WebserviceRequestEntity webserviceRequestEntity) {

    }
}

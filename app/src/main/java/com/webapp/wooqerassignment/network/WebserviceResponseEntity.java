package com.webapp.wooqerassignment.network;

import com.webapp.wooqerassignment.exception.AppException;

import java.util.HashMap;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 */

public class WebserviceResponseEntity {

    private String serviceUrl;

    private AppException appException;

    private HashMap<String, Object> requestTag;

    private Object[] respParam;

    public void addRespParam(Object... objects) {
        respParam = objects;
    }

    public Object[] getRespParam() {
        return respParam;
    }

    public WebserviceResponseEntity() {
        requestTag = new HashMap<>();
    }

    public void addTag(String key, Object value) {
        this.requestTag.put(key,value);
    }

    public Object getTag(String key) {
        if(requestTag != null && requestTag.containsKey(key)) {
            return requestTag.get(key);
        } else {
            return null;
        }
    }


    public void addAllTag( HashMap<String, Object> requestTag) {
        requestTag.putAll(requestTag);
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(final String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public AppException getAppException() {
        return appException;
    }

    public void setAppException(final AppException appException) {
        this.appException = appException;
    }
}

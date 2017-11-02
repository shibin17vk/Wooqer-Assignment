package com.webapp.wooqerassignment.network;

import java.util.HashMap;

/**
 * @author shibin
 * @version 1.0
 * @date 31/10/17
 */

public class WebserviceRequestEntity {

    public WebserviceRequestEntity() {
        requestTag = new HashMap<>();
    }

    public void addTag(String key, Object value) {
        this.requestTag.put(key,value);
    }

    private String serviceUrl;

    private HashMap<String, Object> requestTag;

    private HashMap<String,String> header;

    private String requestBody;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(final String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public HashMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(final HashMap<String, String> header) {
        this.header = header;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(final String requestBody) {
        this.requestBody = requestBody;
    }

    public HashMap getTags() {
        return requestTag;
    }


}

package com.webapp.wooqerassignment.model;

/**
 * @author shibin
 * @version 1.0
 * @date 01/11/17
 */

public class Article {

    private  String title, by, type, url;

    private Long id, time, score;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getBy() {
        return by;
    }

    public void setBy(final String by) {
        this.by = by;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(final Long time) {
        this.time = time;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(final Long score) {
        this.score = score;
    }
}

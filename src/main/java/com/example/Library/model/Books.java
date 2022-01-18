package com.example.Library.model;

import java.io.Serializable;

public class Books implements Serializable {

    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String worksId) {
        this.url = url;
    }

    public String getUrl() {
        return url;


    }
}

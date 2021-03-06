package com.example.Library.model;

import jakarta.json.JsonObject;

import java.io.Serializable;

public class Books implements Serializable {

    private String title;
    private String id;
    private String url;
    private String Description;
    private String Excerpt;
    private boolean cached;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return Description;
    }

    public String getExcerpt() {
        return Excerpt;
    }

    public boolean isCached() {
        return cached;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setExcerpt(String excerpt) {
        Excerpt = excerpt;
    }

    public void setCached(boolean cached) {
        this.cached = cached;
    }

    public static Books jsonToBook(JsonObject o){
        Books book = new Books();
        book.setTitle(o.getString("title"));
        book.setId(o.getString("key"));
        book.setCached(false);
        book.setDescription(o.getString("description"));
        book.setExcerpt(o.getString("excerpt"));
        return book;
    }



}

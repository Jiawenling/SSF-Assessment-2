package com.example.Library.model;

import java.io.Serializable;

public class Books implements Serializable {

    private String title;
    private String worksId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWorksId(String worksId) {
        this.worksId = worksId;
    }

    public String getWorksId() {
        return worksId;


    }
}

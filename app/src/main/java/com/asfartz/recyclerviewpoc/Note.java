package com.asfartz.recyclerviewpoc;

public class Note {

    private String title;
    private String description;

    public Note(String title, String s2) {
        this.title = title;
        this.description = s2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
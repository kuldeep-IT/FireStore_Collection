package com.example.firestorecollection;

public class Data {

    String title,thoughts;

    public Data() {
    }

    public Data(String title, String thoughts) {
        this.title=title;
        this.thoughts=thoughts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts=thoughts;
    }

}

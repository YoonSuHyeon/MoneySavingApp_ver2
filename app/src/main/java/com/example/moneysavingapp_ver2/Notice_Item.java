package com.example.moneysavingapp_ver2;

public class Notice_Item {
    private String title; //제목
    private String content; //내용

    public Notice_Item(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

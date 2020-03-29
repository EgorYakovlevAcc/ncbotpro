package com.ncquizbot.ncbot.pojo;

public class Option {
    private String content;
    private String reaction;

    public Option() {
    }

    public Option(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("В период работы из дома все мы соскучились по путешествиям. Этот квиз позволит вам " +
            "отправиться в разные точки мира и проверить, что вы знаете про страны, города и достопримечательности!"),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

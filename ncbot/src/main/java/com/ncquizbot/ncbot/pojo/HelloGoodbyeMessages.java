package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Сколько новых фильмов вы успели посмотреть за время самоизоляции? " +
            "А сколько любимых пересмотреть и узнать интересные факты о них? Пришло время использовать эти знания!"),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

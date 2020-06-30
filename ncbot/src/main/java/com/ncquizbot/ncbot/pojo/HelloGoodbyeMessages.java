package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Вокруг вселенной Шерлока Холмса образовалось немало мифов. Предлагаем тебе проверить свои знания книг и экранизаций про этого великого сыщика."),
    GOODBYE_MESSAGE("Спасибо за участие в викторине. До новых встреч !!!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

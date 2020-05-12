package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Привет! Как много книг ты успел прочитать на самоизоляции и майских праздниках? Сегодня мы" +
            " проводим викторину по литературе, которая покажет самых начитанных!"),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

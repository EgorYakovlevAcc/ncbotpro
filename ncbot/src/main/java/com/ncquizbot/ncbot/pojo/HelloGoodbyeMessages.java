package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Разбираешься ли ты в мемах так, как разбираешься в IT? \n" +
            "Чего на самом деле ждет Ждун? Откуда взялся малыш Йода? Ответь на десять вопросов " +
            "и узнай, насколько ты в теме! - вот наш приветственный текст "),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

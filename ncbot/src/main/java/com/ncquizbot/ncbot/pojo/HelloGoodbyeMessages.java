package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Разбираешься ли ты в мемах так, как разбираешься в IT? \n" +
            "Чего на самом деле ждет Ждун? Откуда взялся малыш Йода? Ответь на десять вопросов " +
            "и узнай, насколько ты в теме! \n" +
            "[Если бот на тебя не реагирует, пожалуйста, набери текст на клавиатуре и отправь ему!]"),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

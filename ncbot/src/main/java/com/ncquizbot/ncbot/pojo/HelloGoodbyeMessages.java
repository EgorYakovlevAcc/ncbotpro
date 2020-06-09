package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Ты готов к очередному мини-испытанию? Вопросы будут самые разные, " +
            "но обязательно с участием пушистых эгоистов."),
    GOODBYE_MESSAGE("Спасибо за участие в викторине. До новых встреч !!!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

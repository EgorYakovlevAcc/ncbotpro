package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Король RPG: Эта викторина для тех, кто уверен, что лучший спорт — это киберспорт! Проверь, так ли хорошо ты знаком с шедеврами игровой индустрии. " +
            "\n[Если бот на тебя не реагирует, пожалуйста, набери текст на клавиатуре и отправь ему!]"),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

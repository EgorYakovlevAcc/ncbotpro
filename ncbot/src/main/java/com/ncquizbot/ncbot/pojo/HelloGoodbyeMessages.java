package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Осторожно! Во время прохождения этого квиза вам точно захочется заглянуть в холодильник. Все вопросы будут о еде! А вот ответить на них правильно сможет только настоящий знаток интернациональной кухни. Готовы?"),
    GOODBYE_MESSAGE("Спасибо за участие в викторине. До новых встреч !!!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

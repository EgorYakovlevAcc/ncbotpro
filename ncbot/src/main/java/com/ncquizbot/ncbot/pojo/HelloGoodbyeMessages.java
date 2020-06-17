package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Все мы меняемся, когда становимся старше, иногда просто до неузнаваемости." +
                  "Мы собрали фотографии знаменитостей в детстве, попробуй угадать, кто есть кто на детских фото!"),
    GOODBYE_MESSAGE("Спасибо за участие в викторине. До новых встреч !!!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Насколько хорошо ты помнишь школьную программу? Мы собрали в викторине типичные тестовые вопросы" + 
                  "из ЕГЭ по русскому языку и математике. Сдашь ли ты экзамен на проходной балл?"),
    GOODBYE_MESSAGE("Спасибо за участие в викторине. До новых встреч !!!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

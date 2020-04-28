package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Сегодня тебя ждёт квиз на тему \"Такой разный спорт\". Удаленная работа не повод отказываться от " +
            "активностей, пускай и в онлайн-формате. 10 вопросов покажут, насколько ты близок к теме спорта."),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

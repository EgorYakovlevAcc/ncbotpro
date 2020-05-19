package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("Этот квиз специально для фанатов истории о мальчике, который выжил! " +
            "Надеемся ты помнишь, кто такие маглы и что такое патронус?"),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

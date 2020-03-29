package com.ncquizbot.ncbot.pojo;

public enum HelloGoodbyeMessages {
    HELLO_MESSAGE("HELLO, Dear user!"),
    GOODBYE_MESSAGE("Goodbye, Dear user!");
    public String text;

    HelloGoodbyeMessages(String text) {
        this.text = text;
    }
}

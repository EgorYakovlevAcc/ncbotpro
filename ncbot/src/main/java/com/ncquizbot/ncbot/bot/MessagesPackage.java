package com.ncquizbot.ncbot.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.api.methods.PartialBotApiMethod;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MessagesPackage {
    private List<PartialBotApiMethod> messages;

    public MessagesPackage() {
        this.messages = new ArrayList<>();
    }

    public MessagesPackage addMessageToPackage(PartialBotApiMethod message){
        this.messages.add(message);
        return this;
    }

    public MessagesPackage addMessagesToPackage(List<PartialBotApiMethod> messages){
        this.messages.addAll(messages);
        return this;
    }
}

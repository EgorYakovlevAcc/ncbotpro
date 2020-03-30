package com.ncquizbot.ncbot.bot;

public class BotCommand {
    private String command;
    private boolean isActive;

    public BotCommand(String command){
        this.command = command;
        this.isActive = true;
    }

    public String getCommand() {
        return command;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BotCommand){
            BotCommand botCommand = (BotCommand) obj;
            return botCommand.command.equals(this.command);
        }
        return false;
    }
}

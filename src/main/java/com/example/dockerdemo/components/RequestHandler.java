package com.example.dockerdemo.components;


import com.example.dockerdemo.entities.Request;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class RequestHandler {

    public abstract boolean isApplicable(Request request);

    public abstract void handle(Request request);

    public abstract boolean isGlobal();

    public boolean isCommand(Update update, String command) {
        return update.hasMessage() && update.getMessage().isCommand()
                && update.getMessage().getText().equals(command);
    }

    public boolean isTextMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    public boolean isTextMessage(Update update, String text) {
        return update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals(text);
    }

    public boolean isBackQuery(Update update) {
        return update.hasCallbackQuery();
    }
}

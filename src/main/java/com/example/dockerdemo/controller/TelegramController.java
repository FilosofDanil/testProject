package com.example.dockerdemo.controller;

import com.example.dockerdemo.configs.BotConfig;
import com.example.dockerdemo.dispatcher.Dispatcher;
import com.example.dockerdemo.entities.Request;
import com.example.dockerdemo.entities.UserSession;
import com.example.dockerdemo.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Controller
@RequiredArgsConstructor
public class TelegramController extends TelegramLongPollingBot {

    private final Dispatcher dispatcher;

    private final BotConfig config;

    private final SessionService sessionService;


    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId;
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {
            chatId = update.getMessage().getChatId();
        }
        UserSession session = sessionService.getSession(chatId);
        Request request = Request
                .builder()
                .update(update)
                .chatId(chatId)
                .build();
        sessionService.saveSession(request.getChatId(), session);
        System.out.println(session);
        dispatcher.dispatch(request);
    }

    public void sendMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

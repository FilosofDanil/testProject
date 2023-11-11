package com.example.dockerdemo.components;

import com.example.dockerdemo.entities.Request;
import com.example.dockerdemo.entities.UserSession;
import com.example.dockerdemo.enums.States;
import com.example.dockerdemo.services.SessionService;
import com.example.dockerdemo.services.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommandHandler extends RequestHandler {
    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    private static final String command = "/start";

    @Override
    public boolean isApplicable(Request request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(Request request) {
        UserSession session = sessionService.getSession(request.getChatId());
        session.setState(States.START);
        telegramService.sendMessage(request.getChatId(), "Heil! That's a fcking piece of shit(simple test weather forecast application)");
        sessionService.saveSession(request.getChatId(), session);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}

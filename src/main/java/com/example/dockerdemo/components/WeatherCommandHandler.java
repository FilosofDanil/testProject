package com.example.dockerdemo.components;

import com.example.dockerdemo.entities.Request;
import com.example.dockerdemo.entities.UserSession;
import com.example.dockerdemo.enums.States;
import com.example.dockerdemo.services.SessionService;
import com.example.dockerdemo.services.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherCommandHandler extends RequestHandler {
    private static final String command = "/weather";

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public boolean isApplicable(Request request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(Request request) {
        UserSession session = sessionService.getSession(request.getChatId());
        session.setState(States.WAIT_CITY);
        telegramService.sendMessage(request.getChatId(), "Enter the city, which you want to get the forecast for");
        sessionService.saveSession(request.getChatId(), session);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}

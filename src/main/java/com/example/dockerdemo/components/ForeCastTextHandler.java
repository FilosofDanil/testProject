package com.example.dockerdemo.components;

import com.example.dockerdemo.entities.Request;
import com.example.dockerdemo.entities.UserSession;
import com.example.dockerdemo.enums.States;
import com.example.dockerdemo.services.ForecastService;
import com.example.dockerdemo.services.SessionService;
import com.example.dockerdemo.services.TelegramBotService;
import com.example.dockerdemo.utils.ReplyKeyboardHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ForeCastTextHandler implements TextHandler {
    private final ForecastService forecastService;

    private final SessionService sessionService;

    private final TelegramBotService telegramService;

    @Override
    public void handle(Request request) {
        UserSession session = sessionService.getSession(request.getChatId());
        session.setState(States.FORECAST);
        String responseMsg = "Here's your fcking forecast: \n ";
        try {
            responseMsg += forecastService.getForecast(request.getUpdate().getMessage().getText());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpClientErrorException ex){
            responseMsg+=ex.getMessage();
        }
        telegramService.sendMessage(request.getChatId(), responseMsg, ReplyKeyboardHelper.buildMainMenu(List.of("🔙 Back")));
        sessionService.saveSession(request.getChatId(), session);
    }

    @Override
    public States getApplicableState() {
        return States.WAIT_CITY;
    }
}

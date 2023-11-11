package com.example.dockerdemo.components;


import com.example.dockerdemo.entities.Request;
import com.example.dockerdemo.entities.UserSession;
import com.example.dockerdemo.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GTextHandler extends RequestHandler {
    private final List<TextHandler> textHandlers;

    private final SessionService sessionService;

    @Override
    public boolean isApplicable(Request request) {
        return isTextMessage(request.getUpdate());
    }


    @Override
    public void handle(Request request) {
        UserSession session = sessionService.getSession(request.getChatId());
        for (TextHandler textHandler : textHandlers) {
            if (textHandler.getApplicableState().equals(session.getState())) {
                textHandler.handle(request);
                return;
            }
        }
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}

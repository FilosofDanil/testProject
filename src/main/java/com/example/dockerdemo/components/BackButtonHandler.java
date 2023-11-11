package com.example.dockerdemo.components;

import com.example.dockerdemo.entities.Request;
import com.example.dockerdemo.enums.States;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BackButtonHandler implements TextHandler{
    private final StartCommandHandler startCommandHandler;

    @Override
    public void handle(Request request) {
        if(request.getUpdate().getMessage().getText().equals("🔙 Back")
                || request.getUpdate().getMessage().getText().equals("🔙 Назад")) startCommandHandler.handle(request);
    }

    @Override
    public States getApplicableState() {
        return States.FORECAST;
    }
}

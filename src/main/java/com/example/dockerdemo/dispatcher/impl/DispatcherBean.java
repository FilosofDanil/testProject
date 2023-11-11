package com.example.dockerdemo.dispatcher.impl;


import com.example.dockerdemo.components.RequestHandler;
import com.example.dockerdemo.dispatcher.Dispatcher;
import com.example.dockerdemo.entities.Request;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DispatcherBean implements Dispatcher {

    private final List<RequestHandler> handlers;

    public DispatcherBean(List<RequestHandler> handlers) {
        this.handlers = handlers
                .stream()
                .sorted(Comparator
                        .comparing(RequestHandler::isGlobal)
                        .reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void dispatch(Request userRequest) {
        for (RequestHandler userRequestHandler : handlers) {
            if (userRequestHandler.isApplicable(userRequest)) {
                userRequestHandler.handle(userRequest);
                return;
            }
        }
    }
}

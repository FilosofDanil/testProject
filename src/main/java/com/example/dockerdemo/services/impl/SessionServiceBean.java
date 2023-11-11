package com.example.dockerdemo.services.impl;

import com.example.dockerdemo.entities.UserSession;
import com.example.dockerdemo.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
public class SessionServiceBean implements SessionService {
    private Map<Long, UserSession> sessionMap;

    @Override
    public UserSession getSession(Long chatId) {
        return sessionMap.getOrDefault(chatId, UserSession
                .builder()
                .chatId(chatId)
                .build());
    }

    public UserSession saveSession(Long chatId, UserSession session) {
        return sessionMap.put(chatId, session);
    }
}

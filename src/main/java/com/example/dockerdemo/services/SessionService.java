package com.example.dockerdemo.services;

import com.example.dockerdemo.entities.UserSession;

public interface SessionService {
    UserSession getSession(Long chatId);

    UserSession saveSession(Long chatId, UserSession session);
}

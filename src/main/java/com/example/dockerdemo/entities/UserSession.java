package com.example.dockerdemo.entities;

import com.example.dockerdemo.enums.States;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSession {
    private Long chatId;
    private States state;
}

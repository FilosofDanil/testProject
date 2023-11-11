package com.example.dockerdemo.components;

import com.example.dockerdemo.entities.Request;
import com.example.dockerdemo.enums.States;

public interface TextHandler {
    void handle(Request request);

    States getApplicableState();
}

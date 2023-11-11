package com.example.dockerdemo.dispatcher;

import com.example.dockerdemo.entities.Request;

public interface Dispatcher {
    void dispatch(Request request);
}

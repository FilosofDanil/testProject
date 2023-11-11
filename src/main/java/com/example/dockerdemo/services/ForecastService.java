package com.example.dockerdemo.services;

import java.io.IOException;
import java.net.ProtocolException;

public interface ForecastService {
    String  getForecast(String city) throws IOException;
}

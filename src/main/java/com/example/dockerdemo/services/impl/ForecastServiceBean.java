package com.example.dockerdemo.services.impl;

import com.example.dockerdemo.entities.WeatherData;
import com.example.dockerdemo.services.ForecastService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;

@Service
public class ForecastServiceBean implements ForecastService {

    private static final String key = "58b3905433bd4abb860190544231011";

    @Override
    public String getForecast(String city) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://api.weatherapi.com/v1/forecast.json?q="
                + city + "&key=" + key; // or any other uri

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.postForObject(uri, entity, WeatherData.class).toString();
    }
}

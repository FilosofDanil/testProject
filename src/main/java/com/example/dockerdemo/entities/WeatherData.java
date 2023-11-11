package com.example.dockerdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private Location location;
    private CurrentWeather current;
    private Forecast forecast;

    @Override
    public String toString() {

        return "Location: " + location.toString() + "\n" +
                "CurrentWeather: " + current.toString() + "\n"
                + "Forecast: " + forecast.toString();
    }
    // Getters and setters
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Location {
    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;
    private String tz_id;
    private long localtime_epoch;
    private String localtime;

    @Override
    public String toString() {
        return " region: '" + region + '\'' +
                ", country: '" + country + '\'' +
                ", localtime: '" + localtime + '\'' + "\n";
    }

    // Getters and setters
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class CurrentWeather {
    private double temp_c;
    private Condition condition;
    private double feelslike_c;

    @Override
    public String toString() {
        return "temperature: " + temp_c + "\n" +
                "condition: " + condition.getText() + "\n" +
                ", feelslike: " + feelslike_c;
    }

    // Getters and setters
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Forecast {
    private List<ForecastDay> forecastday;

    // Getters and setters

    @Override
    public String toString() {
        return forecastday.toString();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ForecastDay {
    private String date;
    private DayForecast day;
    private Astro astro;
    private List<HourlyForecast> hour;

    @Override
    public String toString() {
        return "date: '" + date + '\'' +
                "day: " + day.toString() +
                "hour: " + hour.toString() +
                 "\n";
    }

    // Getters and setters
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class DayForecast {
    private double totalsnow_cm;
    private int daily_will_it_rain;
    private int daily_chance_of_rain;
    private Condition condition;

    @Override
    public String toString() {
        return "chance of rain: " + daily_chance_of_rain +
                "condition: " + condition.getText() + "\n";
    }
    // Getters and setters
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class HourlyForecast {
    private double temp_c;
    private Condition condition;
    private double feelslike_c;
    private int will_it_rain;
    private int chance_of_rain;

    @Override
    public String toString() {
        return "\ntemperature: " + temp_c + "\n" +
                "condition: " + condition.getText() + "\n" +
                "chance of rain: " + chance_of_rain +
                "\n";
    }
    // Getters and setters
}

@Data
@NoArgsConstructor
class Astro {
    // You can add properties for astronomical data if needed
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Condition {
    private String text;

    // Getters and setters
}

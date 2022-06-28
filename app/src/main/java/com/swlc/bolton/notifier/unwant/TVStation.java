package com.swlc.bolton.notifier.unwant;

public class TVStation implements WeatherObserver {

    private final String name;
    private final WeatherStation station;
    private int currentTemp;

    public TVStation(String name, WeatherStation station) {
        this.name = name;
        this.station = station;
        this.currentTemp = station.getTemp();
    }

    @Override
    public void update(String post) {
        currentTemp = station.getTemp();
        System.out.println("TV Station, " + name + ", received a notification. " +
                "The current temperature is " + currentTemp + " degrees Fahrenheit.::: "+post);
    }
}
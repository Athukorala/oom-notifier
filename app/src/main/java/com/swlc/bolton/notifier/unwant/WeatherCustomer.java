package com.swlc.bolton.notifier.unwant;

public class WeatherCustomer implements WeatherObserver {

    private final String name;
    private final WeatherStation station;
    private int currentTemp;

    public WeatherCustomer(String name, WeatherStation station) {
        this.name = name;
        this.station = station;
        this.currentTemp = station.getTemp();
    }

    @Override
    public void update(String post) {
        currentTemp = station.getTemp();
        System.out.println("Weather customer, " + name + ", received a notification. " +
                "The current temperature is " + currentTemp + " degrees Fahrenheit.::: "+post);
    }
}
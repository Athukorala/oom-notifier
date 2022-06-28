package com.swlc.bolton.notifier.unwant;

public interface WeatherSubject {

    public void addObserver(WeatherObserver weatherObserver);

    public void removeObserver(WeatherObserver weatherObserver);

    public void sendNotification();

}
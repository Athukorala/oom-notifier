
package com.swlc.bolton.notifier.unwant;
public class ObserverPattern {

    public static void main(String[] args) {

        System.out.println("****** Creating new Weather Station 'Colombo Weather Station' with current temperature 70 F  ******");
        WeatherStation weatherStation = new WeatherStation("Colombo Weather Station", 70);
        WeatherStation weatherStation1 = new WeatherStation("Kandy Station", 90);

        WeatherCustomer wc1 = new WeatherCustomer("Terance", weatherStation);
        WeatherCustomer wc2 = new WeatherCustomer("Thilina", weatherStation);
        WeatherCustomer wc3 = new WeatherCustomer("Terance", weatherStation1);

        System.out.println("****** New customers, Terance and Thilina, have just subscribed " +
                "to Weather Station 'Colombo Weather Station'. ******");
        weatherStation.addObserver(wc1);
        weatherStation.addObserver(wc2);
        weatherStation1.addObserver(wc3);

        weatherStation.setTemp(77);
        weatherStation1.setTemp(88);

        System.out.println("\n****** Weather Station wc1 customer Terance has unsubscribed " +
                "from the weather station. ******");
        weatherStation.removeObserver(wc1);

        weatherStation.setTemp(68);

        TVStation tv = new TVStation("Rupavahini", weatherStation);
        System.out.println("\n****** The Rupavahini TV Station has just subscribed " +
                "to Weather Station CWS. ******");
        weatherStation.addObserver(tv);

        weatherStation.setTemp(73);
    }
}

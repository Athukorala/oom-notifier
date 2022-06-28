package com.swlc.bolton.notifier.data_store;

public interface ChannelSubject {

    public void addObserver(ChannelObserver weatherObserver);

    public void removeObserver(ChannelObserver weatherObserver);

    public void sendNotification();

}
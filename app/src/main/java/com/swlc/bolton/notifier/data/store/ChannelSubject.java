package com.swlc.bolton.notifier.data.store;

import com.swlc.bolton.notifier.enums.ObserverType;

public interface ChannelSubject<T> {
    public void addObserver(ChannelObserver weatherObserver);
    public void removeObserver(ChannelObserver weatherObserver);
    public void sendNotification(T obj, ObserverType observerType);
}
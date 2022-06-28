package com.swlc.bolton.notifier.data_store;

import java.util.HashSet;
import java.util.Set;

public class ChannelProvider implements ChannelSubject {
    private final Set<ChannelObserver> setOfChannelObservers = new HashSet<>();
    private String post;

    public ChannelProvider() {
    }

    public ChannelProvider(String post) {
        this.post = post;
        sendNotification();
    }
    public void setPost(String post) {
        this.post = post;
        sendNotification();
    }

    @Override
    public void addObserver(ChannelObserver observer) {
        setOfChannelObservers.add(observer);
        System.out.println("call-length: "+setOfChannelObservers.size());
    }

    @Override
    public void removeObserver(ChannelObserver observer) {
        setOfChannelObservers.remove(observer);
    }

    @Override
    public void sendNotification() {
        System.out.println("call-length: "+setOfChannelObservers.size());
        for(ChannelObserver observer : setOfChannelObservers) {
            System.out.println("test...");
            observer.update(post);
        }
    }
}

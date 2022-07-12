package com.swlc.bolton.notifier.data.store.impl;

import com.swlc.bolton.notifier.controller.SubscriptionController;
import com.swlc.bolton.notifier.data.store.ChannelObserver;
import com.swlc.bolton.notifier.data.store.ChannelSubject;
import com.swlc.bolton.notifier.dto.PostDTO;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.ObserverType;
import com.swlc.bolton.notifier.json.CommonResponse;
import com.swlc.bolton.notifier.views.Home;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChannelProvider implements ChannelSubject<Object> {

    private final Set<ChannelObserver> setOfChannelObservers;
    private final SubscriptionController subscriptionController;

    public ChannelProvider() {
        setOfChannelObservers = new HashSet<>();
        subscriptionController = new SubscriptionController();
    }

    @Override
    public void addObserver(ChannelObserver observer) {
        setOfChannelObservers.add(observer);
    }

    @Override
    public void removeObserver(ChannelObserver observer) {
        setOfChannelObservers.remove(observer);
    }

    @Override
    public void sendNotification(Object obj, ObserverType observerType) {
        for (ChannelObserver observer : setOfChannelObservers) {
            Home home = (Home) observer;

            switch (observerType) {
                case CREATE_ACCOUNT:
                    observer.notifyAccountCreated((UserDTO) obj); // call notify method
                    break;

                case REMOVE_ACCOUNT:
                    observer.notifyAccountRemoved((UserDTO) obj); // call notify method
                    break;

                case PUBLISHED_POST:
                    PostDTO postDTO = (PostDTO) obj;
                    if (postDTO.getSharedUser().getId() == home.getLoggedUserObj().getId()) {
                        observer.notifyPost(postDTO); // call notify method
                    } else {
                        CommonResponse<?> subscribers = subscriptionController.getSubscribersIDHandler(postDTO.getSharedUser().getId());
                        if (subscribers.isSuccess()) {
                            ArrayList<Long> subsIds = (ArrayList<Long>) subscribers.getBody();
                            subsIds.forEach(id -> {
                                if (home.getLoggedUserObj().getId() == id) {
                                    observer.notifyPost(postDTO); // call notify method
                                }
                            });
                        }
                    }
                    break;

                case SUBSCRIBED_COUNT:
                    UserDTO userDTO = (UserDTO) obj;
                    if (userDTO.getId() == home.getLoggedUserObj().getId()) {
                        CommonResponse<Long> subscribers = subscriptionController.getSubscribedCountHandler(userDTO.getId());
                        if (subscribers.isSuccess()) {
                            long subsCount = subscribers.getBody();
                            observer.notifySubscribers(subsCount); // call notify method
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

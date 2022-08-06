package com.swlc.bolton.notifier.controller;

import com.swlc.bolton.notifier.data.store.impl.SubscriptionStore;
import com.swlc.bolton.notifier.dto.SubscriptionDTO;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.json.CommonResponse;

/**
 *
 * @author athukorala
 */
public class SubscriptionController implements SuperController {
    private final SubscriptionStore subscriptionStore;
    public SubscriptionController() {
        subscriptionStore = new SubscriptionStore();
    }
    /**
     * retrieve all registered accounts (channels)
     * @param userDTO related user details
     * @return CommonResponse
     */
    public CommonResponse retrieveAllSubscriptionHandler(UserDTO userDTO) {
        return subscriptionStore.retrieveData(new SubscriptionDTO(0, userDTO.getId()));
    }
    /**
     * subscribe channel
     * @param subscriberDTO subscription details
     * @return CommonResponse
     */
    public CommonResponse subscriptionUserHandler(SubscriptionDTO subscriberDTO) {
        return subscriptionStore.reserve(subscriberDTO);
    }
    /**
     * retrieve subs count for each channels
     * @param userId channel owner id
     * @return CommonResponse
     * */
    public CommonResponse getSubscribedCountHandler(long userId) {
        return subscriptionStore.getSubscriberCount(userId);
    }
    /**
     * retrieve subscribed user IDs
     * @param userId channel owner id
     * @return CommonResponse
     * */
    public CommonResponse getSubscribersIDHandler(long userId) {
        return subscriptionStore.getSubscribers(userId);
    }
    /**
     * un-subscribe channel
     * @return CommonResponse
     * */
    public CommonResponse removeSubscriptionHandler(SubscriptionDTO subscriberDTO) {
        return subscriptionStore.release(subscriberDTO);
    }
}

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

    public CommonResponse retrieveAllSubscriptionHandler(UserDTO userDTO) {
        return subscriptionStore.retrieveData(new SubscriptionDTO(0, userDTO.getId()));
    }

    /**
     *
     * @param subscriberDTO
     * @return CommonResponse
     */
    public CommonResponse subscriptionUserHandler(SubscriptionDTO subscriberDTO) {
        return subscriptionStore.reserve(subscriberDTO);
    }

    public CommonResponse getSubscribedCountHandler(long userId) {
        return subscriptionStore.getSubscriberCount(userId);
    }

    public CommonResponse getSubscribersIDHandler(long userId) {
        return subscriptionStore.getSubscribers(userId);
    }
    public CommonResponse removeSubscriptionHandler(SubscriptionDTO subscriberDTO) {
        return subscriptionStore.release(subscriberDTO);
    }
}


package com.swlc.bolton.notifier.controller;

import com.swlc.bolton.notifier.data_store.SubscriptionStore;
import com.swlc.bolton.notifier.data_store.UserStore;
import com.swlc.bolton.notifier.dto.SubscribeUserDTO;
import com.swlc.bolton.notifier.dto.SubscriptionDTO;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.json.CommonResponse;
import java.util.ArrayList;

/**
 *
 * @author athukorala
 */
public class SubscriptionController {

    private final SubscriptionStore subscriptionStore;

    public SubscriptionController() {
        subscriptionStore = new SubscriptionStore();
    }

    public CommonResponse retrieveAllSubcriptionHandler(UserDTO userDTO) {
        return subscriptionStore.retriveData(new SubscriptionDTO(0, userDTO.getId()));
    }

    /**
     *
     * @param subscriberDTO
     * @return CommonResponse
     */
    public CommonResponse subscriptionUserHandler(SubscriptionDTO subscriberDTO) {
        return subscriptionStore.reserve(subscriberDTO);
    }
}

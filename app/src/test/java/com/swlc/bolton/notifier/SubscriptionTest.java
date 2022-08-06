package com.swlc.bolton.notifier;

import com.swlc.bolton.notifier.controller.ControllerFactory;
import com.swlc.bolton.notifier.controller.SubscriptionController;
import com.swlc.bolton.notifier.dto.SubscriptionDTO;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.ControllerTypes;
import com.swlc.bolton.notifier.json.CommonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionTest {
    private SubscriptionController subscriptionController;

    @BeforeEach
    public void init() {
        subscriptionController = (SubscriptionController) ControllerFactory.getInstance().getController(ControllerTypes.SUBSCRIPTION);
    }

    @Test
    @DisplayName("Test Subscribe and Unsubscribe feature")
    public void testSubscribeAndUnsubscribeUser() {
        CommonResponse response = subscriptionController.subscriptionUserHandler(new SubscriptionDTO(1, 2));
        assertTrue(response.isSuccess());
    }
    @Test
    @DisplayName("Test retrieve all channels")
    public void testRetrieveAllSubscriptionHandler() {
        CommonResponse resp = subscriptionController.retrieveAllSubscriptionHandler(new UserDTO(new LoginTest().TEST_ID,new LoginTest().TEST_NAME,new LoginTest().TEST_EMAIL,new LoginTest().TEST_PASSWORD));
        assertTrue(resp.isSuccess());
    }

    @Test
    @DisplayName("Test get subscribed count for user")
    public void testDisplaySubscriberCountHandler() {
        CommonResponse resp = subscriptionController.getSubscribedCountHandler(1);
        assertEquals(Long.parseLong("0"), resp.getBody());
    }
}

package com.swlc.bolton.notifier;

import com.swlc.bolton.notifier.data.store.impl.ChannelProvider;
import com.swlc.bolton.notifier.dto.PostDTO;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.ObserverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostContentTest {
    private ChannelProvider channelProvider;

    @BeforeEach
    public void init() {
        channelProvider = new ChannelProvider();
    }

    @Test
    @DisplayName("Test user publish post")
    public void testPublishPost() {
        UserDTO loggedUserObj = new UserDTO(new LoginTest().TEST_ID, new LoginTest().TEST_NAME, new LoginTest().TEST_EMAIL, new LoginTest().TEST_PASSWORD);
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy  hh:mm a");
        String formatDate = df.format(new Date());
        channelProvider.sendNotification(new PostDTO(loggedUserObj, formatDate, "TEST POST"), ObserverType.PUBLISHED_POST);
    }
}

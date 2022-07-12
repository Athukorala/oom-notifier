package com.swlc.bolton.notifier.data.store;

import com.swlc.bolton.notifier.dto.PostDTO;
import com.swlc.bolton.notifier.dto.UserDTO;

public interface ChannelObserver {
    public void notifyPost(PostDTO postObj);
    public void notifySubscribers(long subsCount);
    public void notifyAccountRemoved(UserDTO userDTO);
    public void notifyAccountCreated(UserDTO userDTO);
}

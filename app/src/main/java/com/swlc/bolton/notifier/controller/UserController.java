
package com.swlc.bolton.notifier.controller;

import com.swlc.bolton.notifier.data.store.impl.UserStore;
import com.swlc.bolton.notifier.dto.SubscriptionDTO;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.json.CommonResponse;

/**
 *
 * @author athukorala
 */
public class UserController implements SuperController{
    private final UserStore userStore;
    private final SubscriptionController subscriptionController;

    public UserController() {
        userStore = new UserStore();
        subscriptionController = new SubscriptionController();
    }
    

    public CommonResponse registerHandler(UserDTO userDTO) {
        return userStore.reserve(userDTO);
    }
    
    public CommonResponse loginHandler(UserDTO userDTO) {
        CommonResponse checkAvailability = userStore.retrieveData(userDTO);
        
        if(checkAvailability.isSuccess()) {
            UserDTO retrieveUserObj = (UserDTO) checkAvailability.getBody();
            if(!userDTO.getPassword().equalsIgnoreCase(retrieveUserObj.getPassword())){
                checkAvailability.setSuccess(false);
            }
        }
        return checkAvailability;
    }
    
    public CommonResponse removeAccountHandler(UserDTO userDTO) {
        CommonResponse release = userStore.release(userDTO);
        // remove subscriptions
        if(release.isSuccess()) subscriptionController.removeSubscriptionHandler(new SubscriptionDTO(0, userDTO.getId()));
        return release;
    }
}

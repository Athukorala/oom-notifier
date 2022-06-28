
package com.swlc.bolton.notifier.controller;

import static com.swlc.bolton.notifier.constants.ApplicationConstant.*;
import com.swlc.bolton.notifier.data_store.UserStore;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.StoreType;
import com.swlc.bolton.notifier.json.CommonResponse;

/**
 *
 * @author athukorala
 */
public class UserController {
    private UserStore userStore;

    public UserController() {
        userStore = new UserStore();
    }
    

    public CommonResponse registerHandler(UserDTO userDTO) {
        return userStore.reserve(userDTO);
    }
    
    public CommonResponse loginHandler(UserDTO userDTO) {
        CommonResponse checkAvailability = userStore.retriveData(userDTO);
        
        if(checkAvailability.isSuccess()) {
            UserDTO retriveUserObj = (UserDTO) checkAvailability.getBody();
            if(!userDTO.getPassword().equalsIgnoreCase(retriveUserObj.getPassword())){
                checkAvailability.setSuccess(false);
            }
        }
        return checkAvailability;
    }
}

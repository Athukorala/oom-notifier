
package com.swlc.bolton.notifier.dto;

/**
 *
 * @author athukorala
 */
public class SubscribeUserDTO extends UserDTO{

    private UserDTO userDTO;
    private boolean isSubscribe;

    public SubscribeUserDTO() {
   
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    
    public boolean isIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

}


package com.swlc.bolton.notifier.data.store.impl;

import static com.swlc.bolton.notifier.constants.ApplicationConstant.*;

import com.swlc.bolton.notifier.data.store.SuperStore;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.StoreType;
import com.swlc.bolton.notifier.json.CommonResponse;
import java.util.ArrayList;

/**
 *
 * @author athukorala
 */
public class UserStore implements SuperStore<UserDTO> {

    public static boolean isDevVersion = false; // for dev purposes
    private static final ArrayList<UserDTO> registeredUserList = new ArrayList<>(); // registered user list

    @Override
    public synchronized CommonResponse reserve(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreType.RESERVE);
    }

    @Override
    public CommonResponse release(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreType.RELEASE);
    }
    
    
    @Override
    public CommonResponse retrieveListHandler() {
        return new CommonResponse(true, registeredUserList);
    }
    
    @Override
    public CommonResponse retrieveData(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreType.RETRIEVE);
    }

    @Override
    public CommonResponse checkAvailability(UserDTO userDTO, StoreType store) {
        try {
            UserDTO availableObj = null;
            for (UserDTO dto : registeredUserList) {
                if (userDTO.getEmail().equals(dto.getEmail())) availableObj = dto;
            }
            switch (store) {
                case RESERVE:
                    if (availableObj == null) {
                        registeredUserList.add(userDTO);
                        return new CommonResponse(true, COMMON_SUCCESS_MSG, userDTO);
                    } else {
                        return new CommonResponse(false,  ADDED_EMAIL_ALREADY_EXIST);
                    }
                case RELEASE:
                    if (availableObj != null) {
                        registeredUserList.remove(availableObj);
                        return new CommonResponse(true, availableObj);
                    }   break;
                case RETRIEVE:
                    if (availableObj != null) return new CommonResponse(true, availableObj);
                    break;
                default:
                    break;
            }
            return new CommonResponse(false,  USER_NOT_FOUND);
        } catch (Exception e) {
            return new CommonResponse(false, null, e.getMessage());
        }
    }
}

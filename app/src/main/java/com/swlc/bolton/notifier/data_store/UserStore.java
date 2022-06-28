
package com.swlc.bolton.notifier.data_store;

import static com.swlc.bolton.notifier.constants.ApplicationConstant.*;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.StoreType;
import com.swlc.bolton.notifier.json.CommonResponse;
import java.util.ArrayList;

/**
 *
 * @author athukorala
 */
public class UserStore implements SuperStore<UserDTO>{

    public static boolean isDevVersion = true; // for testing purposes
    private static final ArrayList<UserDTO> registeredUserList = new ArrayList<>();

    @Override
    public synchronized CommonResponse reserve(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreType.RESERVE);
    }

    @Override
    public CommonResponse release(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreType.RELEASE);
    }
    
    
    @Override
    public CommonResponse retireveListHandler() {
        return new CommonResponse<>(true, registeredUserList);
    }
    
    @Override
    public CommonResponse retriveData(UserDTO userDTO) {
        return checkAvailability(userDTO, StoreType.RETRIEVE);
    }

    @Override
    public CommonResponse checkAvailability(UserDTO userDTO, StoreType store) {
        try {
            UserDTO availableObj = null;
            for (int i = 0; i < registeredUserList.size(); i++) {
                if (userDTO.getEmail().equals(registeredUserList.get(i).getEmail())) {
                    availableObj = registeredUserList.get(i);
                }
            }

            switch (store) {
                case RESERVE:
                    if (availableObj == null) {
                        registeredUserList.add(userDTO);
                        return new CommonResponse<>(true, userDTO);
                    } else {
                        return new CommonResponse<>(false,  ADDED_EMAIL_ALREADY_EXIST);
                    }
                case RELEASE:
                    if (availableObj != null) {
                        registeredUserList.remove(availableObj);
                        return new CommonResponse<>(true, availableObj);
                    }   break;
                case RETRIEVE:
                    if (availableObj != null) return new CommonResponse<>(true, availableObj);
                    break;
                default:
                    break;
            }
            
            return new CommonResponse<>(false,  USER_NOT_FOUND);

        } catch (Exception e) {
            return new CommonResponse<>(false, null, e.getMessage());
        }
    }

}

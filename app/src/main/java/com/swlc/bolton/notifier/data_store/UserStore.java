/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swlc.bolton.notifier.data_store;

import static com.swlc.bolton.notifier.constants.ApplicationConstant.*;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.UserStoreType;
import com.swlc.bolton.notifier.json.CommonResponse;
import java.util.ArrayList;

/**
 *
 * @author athukorala
 */
public class UserStore {

    public static boolean isDevVersion = true; // for testing purposes
    
    private static ArrayList<UserDTO> registeredUserList = new ArrayList<>();

    public synchronized CommonResponse reserve(UserDTO userDTO) {
        return checkAvailability(userDTO, UserStoreType.RESERVE);
    }

    public CommonResponse release(UserDTO userDTO) {
        return checkAvailability(userDTO, UserStoreType.RELEASE);
    }

    private CommonResponse checkAvailability(UserDTO userDTO, UserStoreType store) {
        try {
            UserDTO availableObj = null;
            for (int i = 0; i < registeredUserList.size(); i++) {
                if (userDTO.getEmail().equals(registeredUserList.get(i).getEmail())) {
                    availableObj = registeredUserList.get(i);
                }
            }

            if (store.equals(UserStoreType.RESERVE)) {
                if (availableObj == null) {
                    registeredUserList.add(userDTO);
                    return new CommonResponse<UserDTO>(true, userDTO);
                } else {
                    return new CommonResponse<>(false,  ADDED_EMAIL_ALREADY_EXIST);
                }

            } else if (store.equals(UserStoreType.RELEASE)) {
                if (availableObj != null) {
                    registeredUserList.remove(availableObj);
                    return new CommonResponse<UserDTO>(true, availableObj);
                }
                
            } else if (store.equals(UserStoreType.RETRIEVE)) {
                if (availableObj != null) return new CommonResponse<UserDTO>(true, availableObj);
            }
            
            // for RELEASE and RETRIEVE Process
            return new CommonResponse<>(false,  USER_NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse<>(false, null, e.getMessage());
        }
    }

    public CommonResponse retireveListHandler() {
        return new CommonResponse<>(true, registeredUserList);
    }
    
    public CommonResponse retriveUser(UserDTO userDTO) {
        return checkAvailability(userDTO, UserStoreType.RETRIEVE);
    }
}

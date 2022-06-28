package com.swlc.bolton.notifier.data_store;

import static com.swlc.bolton.notifier.constants.ApplicationConstant.*;
import com.swlc.bolton.notifier.dto.SubscribeUserDTO;
import com.swlc.bolton.notifier.dto.SubscriptionDTO;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.StoreType;
import com.swlc.bolton.notifier.json.CommonResponse;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author athukorala
 */
public class SubscriptionStore implements SuperStore<SubscriptionDTO>, ChannelSubject {

    private Set<ChannelObserver> setOfPostObservers;

    private static final ArrayList<SubscriptionDTO> subscribedList = new ArrayList<>();

    @Override
    public synchronized CommonResponse reserve(SubscriptionDTO subscriberDTO) {
        return checkAvailability(subscriberDTO, StoreType.RESERVE);
    }

    @Override
    public CommonResponse release(SubscriptionDTO dto) throws Exception {
        return checkAvailability(dto, StoreType.RELEASE);
    }

    @Override
    public CommonResponse retireveListHandler() throws Exception {
        return new CommonResponse<>(true, subscribedList);
    }

    @Override
    public CommonResponse retrieveData(SubscriptionDTO dto) {
        return checkAvailability(dto, StoreType.RETRIEVE);
    }

    @Override
    public CommonResponse checkAvailability(SubscriptionDTO subscriberDTO, StoreType type) {
        try {           
            switch (type) {
                case RESERVE:
                    SubscriptionDTO availableObj = null;
                    for (int i = 0; i < subscribedList.size(); i++) {
                        if ((subscriberDTO.getSubscribedBy() == subscribedList.get(i).getSubscribedBy()) && (subscriberDTO.getSubscriberUserId() == subscribedList.get(i).getSubscriberUserId())) availableObj = subscribedList.get(i);
                    }
                    
                    if (availableObj == null) {
                        subscribedList.add(subscriberDTO);
                    } else {
                        subscribedList.remove(availableObj);
                    }
                    return new CommonResponse<>(true, subscriberDTO);

                case RETRIEVE:
                    ArrayList<SubscribeUserDTO> SubscribeUserDTO = new ArrayList<>();
                    CommonResponse resp = new UserStore().retireveListHandler();
                    ArrayList<UserDTO> userList = (ArrayList<UserDTO>) resp.getBody();

                    userList.forEach(userDetail -> {
                        if (userDetail.getId() == subscriberDTO.getSubscribedBy()) return;
                        SubscribeUserDTO tempSubscribeUser = new SubscribeUserDTO();

                        boolean isFound = false;
                        UserDTO tempUserOb = null;

                        for (int i = 0; i < subscribedList.size(); i++) {
                            SubscriptionDTO subscribedObj = subscribedList.get(i);
                            if ((subscriberDTO.getSubscribedBy() == subscribedObj.getSubscribedBy()) && (subscribedObj.getSubscriberUserId() == userDetail.getId())) {
                                isFound = true;
                                tempUserOb = userDetail;
                            }
                        }
                        tempSubscribeUser.setUserDTO(isFound ? tempUserOb : userDetail);
                        tempSubscribeUser.setIsSubscribe(isFound);
                        SubscribeUserDTO.add(tempSubscribeUser);
                    });
                    return new CommonResponse<>(true, SubscribeUserDTO);

                default:
                    break;
            }
            return new CommonResponse<>(false, USER_NOT_FOUND);
        } catch (Exception e) {
            return new CommonResponse<>(false, null, e.getMessage());
        }
    }

    // observer methods
    @Override
    public void addObserver(ChannelObserver weatherObserver) {

    }

    @Override
    public void removeObserver(ChannelObserver weatherObserver) {

    }

    @Override
    public void sendNotification() {

    }
}

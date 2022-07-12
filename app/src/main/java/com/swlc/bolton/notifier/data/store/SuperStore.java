
package com.swlc.bolton.notifier.data.store;

import com.swlc.bolton.notifier.dto.SuperDTO;
import com.swlc.bolton.notifier.enums.StoreType;
import com.swlc.bolton.notifier.json.CommonResponse;

/**
 *
 * @author athukorala
 * @param <T>
 */
public interface SuperStore <T extends SuperDTO> {
    public CommonResponse reserve(T dto) throws Exception;
    public CommonResponse release(T dto) throws Exception;
    public CommonResponse retrieveListHandler() throws Exception;
    public CommonResponse retrieveData(T dto) throws Exception;
    public CommonResponse checkAvailability(T dto, StoreType store) throws Exception;
}

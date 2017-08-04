package com.bjike.entity.chat;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-02 15:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AudioClient {
    private Map<String,Boolean> requester = new HashMap<>();

    public Map<String, Boolean> getRequester() {
        return requester;
    }

    public void setRequester(Map<String, Boolean> requester) {
        this.requester = requester;
    }
}

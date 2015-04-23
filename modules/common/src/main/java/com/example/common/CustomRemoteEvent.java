package com.example.common;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * Created by dave on 4/23/15.
 */
public class CustomRemoteEvent extends RemoteApplicationEvent {

    private Object payload;

    public CustomRemoteEvent(Object source, String originService, Object payload) {
        super(source, originService);
        this.payload = payload;
    }

    public Object getPayload() {
        return payload;
    }

}

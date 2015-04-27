package com.example.common;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * Created by dave on 4/23/15.
 */
public class CustomRemoteEvent extends RemoteApplicationEvent {

    private CustomDataType payload;

    public CustomRemoteEvent() {}

    public CustomRemoteEvent(Object source, String originService, CustomDataType payload) {
        super(source, originService);
        this.payload = payload;
    }

    public CustomDataType getPayload() {
        return payload;
    }

}

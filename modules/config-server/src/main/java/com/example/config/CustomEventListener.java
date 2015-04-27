package com.example.config;

import com.example.common.CustomRemoteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by dave on 4/23/15.
 */
@Slf4j
@Component
public class CustomEventListener implements ApplicationListener<CustomRemoteEvent> {
    @Override
    public void onApplicationEvent(CustomRemoteEvent event) {
        log.info("Custom event received!");
    }
}

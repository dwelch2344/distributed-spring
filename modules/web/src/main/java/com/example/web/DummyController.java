package com.example.web;

import com.example.common.CustomRemoteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/dummy")
public class DummyController {

    @Value("${configuration.projectName}")
    private String projectName;

    @Value("${configuration.instance.id}")
    private String instanceId;

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("/name")
    public String projectName() {
        return this.projectName;
    }

    @RequestMapping("/foo")
    public void foo(){
        log.info("Publishing custom event");
        publisher.publishEvent(new CustomRemoteEvent(this, instanceId, new HashMap<String, String>(){{
            put("message", "this is a test!");
        }}));
    }
}
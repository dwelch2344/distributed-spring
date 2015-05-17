package com.example.web;

import com.example.common.CustomDataType;
import com.example.common.CustomRemoteEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@Slf4j
@RefreshScope
@RestController
@RequestMapping("/dummy")
public class DummyController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private UserService userService;

    @RequestMapping("/foo")
    public void foo(){
        log.info("Publishing custom event");
        publisher.publishEvent(new CustomRemoteEvent(this, ctx.getId(), new CustomDataType("David", "Welch")));
    }

    @RequestMapping("/users")
    public Object users(){
        return userService.getUsers();
    }

    @RequestMapping("/users/{id}")
    public Object user(@PathVariable String id){
        return userService.getUser(id);
    }
}


@FeignClient("user-service")
interface UserService {
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    UserBean getUser(@PathVariable("userId") String userId);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    Collection<UserBean> getUsers();
}

@Data
class UserBean {
    private Long id;
    private String firstName, lastName, username;

    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }
}
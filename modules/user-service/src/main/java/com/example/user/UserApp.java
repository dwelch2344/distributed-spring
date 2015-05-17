package com.example.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by dave on 5/7/15.
 */
@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
@SpringCloudApplication
public class UserApp {
    public static void main(String[] args){
        SpringApplication.run(UserApp.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository ur) {
        return args ->
                Arrays.asList("jimbo:jones:jimboj,david:welch:dwelch".split(","))
                    .stream()
                    .map(s -> s.split(":"))
                    .forEach(s -> {
                    UserAccount user = new UserAccount(s[0], s[1], s[2]);
                    ur.save(user);
                });
    }
}


interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findOneByUsername(String username);
}


@RestController
class UserControllers {

    @Autowired
    private UserRepository bookmarkRepository;

    @RequestMapping("/")
    Collection<UserAccount> users() {
        return this.bookmarkRepository.findAll();
    }

    @RequestMapping("/{userId}")
    UserAccount user(@PathVariable String userId) {
        return this.bookmarkRepository.findOneByUsername(userId);
    }
}

@Data
@Entity
class UserAccount {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName, lastName, username;

    public UserAccount(){}

    public UserAccount(String firstName, String lastName, String username){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }


}
package org.robert.microservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.robert.microservice.entity.User;
import org.robert.microservice.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

//    public User findByIdFallback(Long id) {
//        User user = new User();
//        user.setId(-1L);
//        user.setName("默认用户");
//        return user;
//    }

}

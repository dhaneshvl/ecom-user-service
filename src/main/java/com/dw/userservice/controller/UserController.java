package com.dw.userservice.controller;

import com.dw.userservice.entity.User;
import com.dw.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private int serverPort;

    @PostMapping
    public User onboardUser(@RequestBody User user) {
        return userService.onboardUser(user);
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserInfo(@PathVariable Long userId) {
        System.out.println("Request processed by port: "+serverPort);
        return userService.getUserInfo(userId);
    }

    @PutMapping("credit/{userId}/{amount}")
    public User topUpUserAccountBalance(@PathVariable Long userId,@PathVariable Double amount) {
        return userService.topUpUserAccountBalance(userId, amount);
    }

    @PutMapping("debit/{userId}/{amount}")
    public User debitAmountFromUserAccount(@PathVariable Long userId, @PathVariable Double amount) {
        return userService.deductAmountFromUserAccount(userId, amount);
    }


}

package com.dw.userservice.service;

import com.dw.userservice.entity.User;
import com.dw.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User onboardUser(User user) {
        user.setOnboardedDate(new Date());
        return userRepository.save(user);
    }

    public Optional<User> getUserInfo(Long userId) {
        return userRepository.findById(userId).map(Optional::of).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Transactional
    public User topUpUserAccountBalance(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        user.setAccountBalance((user.getAccountBalance() != null ? user.getAccountBalance() : 0.0) + amount);
        return userRepository.save(user);
    }

    @Transactional
    public User deductAmountFromUserAccount(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        user.setAccountBalance(user.getAccountBalance() - amount);
        return userRepository.save(user);
    }



}

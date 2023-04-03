package com.projectps.cinema.service.impl;

import com.projectps.cinema.entity.User;
import com.projectps.cinema.repository.UserRepository;
import com.projectps.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setAdmin(user.isAdmin());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRatings(user.getRatings());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}

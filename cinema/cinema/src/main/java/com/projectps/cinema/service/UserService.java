package com.projectps.cinema.service;

import com.projectps.cinema.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User saveUser(User user);

    List<User> saveUsers(List<User> users);

    List<User> getUsers();

    User getUserById(int id);

    User updateUser(User user);

    void deleteUser(int id);

}

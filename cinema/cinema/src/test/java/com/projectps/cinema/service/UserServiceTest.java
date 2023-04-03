package com.projectps.cinema.service;

import com.projectps.cinema.entity.User;
import com.projectps.cinema.repository.UserRepository;
import com.projectps.cinema.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Arrange
        User user = new User();
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.saveUser(user);

        // Assert
        Assertions.assertEquals(user, result);
    }

    @Test
    public void testGetUsers() {
        // Arrange
        List<User> users = Arrays.asList(new User(), new User());
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getUsers();

        // Assert
        Assertions.assertEquals(users, result);
    }

    @Test
    public void testGetUserById() {
        // Arrange
        User user = new User();
        int id = 1;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(id);

        // Assert
        Assertions.assertEquals(user, result);
    }


    @Test
    public void testUpdateUser() {
        // Arrange
        User user = new User();
        int id = 1;
        user.setId(id);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.updateUser(user);

        // Assert
        Assertions.assertEquals(user, result);
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        User user = new User();
        int id = 1;
        user.setId(id);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(id);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
    }

}
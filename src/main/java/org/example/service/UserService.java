package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void deleteAllUsers();

    public boolean isExistUserName(String userName);

    public User getUserByID(Long id);

    public User getUserByUserName(String userName);

    public String addUser(User user);

    public String deleteUser(User user);

    public String deleteUserById(Long id);

    public String changeUser(User changedUser);
}

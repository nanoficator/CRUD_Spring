package org.example.service;

import org.example.exception.DBException;
import org.example.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers() throws DBException;

    public void deleteAllUsers() throws DBException;

    public boolean isExistUserName(String userName) throws DBException;

    public User getUserByID(Long id) throws DBException;

    public User getUserByUserName(String userName) throws DBException;

    public String addUser(User user) throws DBException;

    public String deleteUser(User user) throws DBException;

    public String deleteUserById(Long id) throws DBException;

    public String changeUser(User changedUser) throws DBException;
}

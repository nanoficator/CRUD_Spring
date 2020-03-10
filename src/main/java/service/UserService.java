package service;

import model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void deleteAllUsers();

    public boolean isExistUsername(String username);

    public User getUserByID(Long id);

    public User getUserByUsername(String username);

    public String addUser(User user);

    public String deleteUser(Long id);

    public String changeUser(User changedUser);
}
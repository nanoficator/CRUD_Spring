package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.UserDaoFactory;
import org.example.exception.DBException;
import org.example.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceSpring implements UserService {

    @Override
    public List<User> getAllUsers() throws DBException {
        UserDao dao = UserDaoFactory.getDao();
        try {
            return dao.getAllData();
        } catch (SQLException e) {
            throw new DBException(e);
        }    }

    @Override
    public void deleteAllUsers() throws DBException {
        UserDao dao = UserDaoFactory.getDao();
        try {
            dao.deleteAllData();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public boolean isExistUserName(String userName) throws DBException {
        return false;
    }

    @Override
    public User getUserByID(Long id) throws DBException {
        return null;
    }

    @Override
    public User getUserByUserName(String userName) throws DBException {
        return null;
    }

    @Override
    public String addUser(User user) throws DBException {
        return null;
    }

    @Override
    public String deleteUser(User user) throws DBException {
        return null;
    }

    @Override
    public String deleteUserById(Long id) throws DBException {
        return null;
    }

    @Override
    public String changeUser(User changedUser) throws DBException {
        return null;
    }
}

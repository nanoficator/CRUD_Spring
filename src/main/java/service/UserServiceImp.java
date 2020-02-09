package service;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImp implements UserService {
    @Autowired
    UserDao dao;

    @Override
    public List<User> getAllUsers() {
        try {
            return dao.getAllData();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteAllUsers() {
        try {
            dao.deleteAllData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExistUserName(String userName) {
        try{
            User userFromDB = dao.getDataByUserName(userName);
            if (userFromDB != null) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByID(Long id) {
        try {
            return dao.getDataByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        try {
            return dao.getDataByUserName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String addUser(User user) {

        if (isExistUserName(user.getUserName())) {
            return "Error: Username exists!";
        }

        if (user.getAge() < 0) {
            return "Error: Age can not be negative!";
        }

        try {
            dao.addData(user);
            return "User was added!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }

    @Override
    public String deleteUser(User user) {

        User userFromDB = getUserByUserName(user.getUserName());

        if (userFromDB == null) {
            return "Error: User does not exist!";
        }

        try {
            dao.deleteData(userFromDB);
            return "User was deleted!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }

    public String deleteUserById(Long id) {

        User userFromDB = getUserByID(id);

        if (userFromDB == null) {
            return "Error: User does not exist!";
        }

        try {
            dao.deleteData(userFromDB);
            return "User was deleted!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }

    public String changeUser(User changedUser) {

        Long id = changedUser.getId();
        String newFirstName = changedUser.getFirstName();
        String newSecondName = changedUser.getSecondName();
        String newUserName = changedUser.getUserName();
        String newPassword = changedUser.getPassword();
        Long newAge = changedUser.getAge();
        String newGender = changedUser.getGender();

        User userFromDBById = getUserByID(id);
        User userFromDBByUserName = getUserByUserName(newUserName);

        if (userFromDBById == null) {
            return "Error: User does not exist!";
        }

        if (id == null ||
                newFirstName.equals("") ||
                newSecondName.equals("") ||
                newUserName.equals("") ||
                newPassword.equals("") ||
                newAge == null ||
                newGender.equals("")) {
            return "Error: All fields are required!";
        }

        if (userFromDBByUserName != null && !userFromDBByUserName.getId().equals(id)) {
            return "Error: Username exists!";
        }

        if (newAge < 0) {
            return "Error: Age can not be negative!";
        }

        try {
            dao.changeFirstName(id, newFirstName);
            dao.changeSecondName(id, newSecondName);
            dao.changeUserName(id, newUserName);
            dao.changePassword(id, newPassword);
            dao.changeAge(id, newAge);
            dao.changeGender(id, newGender);
            return "Changes saved!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }
}

package service;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@ComponentScan("dao")
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.getAllData();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteAllUsers() {
        try {
            userDao.deleteAllData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExistUsername(String username) {
        try{
            User userFromDB = userDao.getDataByUsername(username);
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
            return userDao.getDataByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return userDao.getDataByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String addUser(User user) {

        if (isExistUsername(user.getUsername())) {
            return "Error: Username exists!";
        }

        if (!passwordEncoder.matches(user.getConfirmPassword(), user.getPassword())) {
            return "Error: Passwords do not match!";
        }

        try {
            userDao.addData(user);
            return "User was added!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }

    public String deleteUser(Long id) {

        User userFromDB = getUserByID(id);

        if (userFromDB == null) {
            return "Error: User does not exist!";
        }

        try {
            userDao.deleteData(userFromDB);
            return "User was deleted!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }

    public String changeUser(User changedUser) {

        Long id = changedUser.getId();
        String newUserName = changedUser.getUsername();

        User userFromDBById = getUserByID(id);
        User userFromDBByUserName = getUserByUsername(newUserName);

        if (changedUser.getPassword().equals("") && changedUser.getConfirmPassword().equals("")) {
            changedUser.setPassword(userFromDBById.getPassword());
        } else {
            changedUser.setPassword(passwordEncoder.encode(changedUser.getPassword()));
            if (!passwordEncoder.matches(changedUser.getConfirmPassword(), changedUser.getPassword())) {
                return "Error: Passwords do not match!";
            }
        }

        if (userFromDBById == null) {
            return "Error: User does not exist!";
        }

        if (newUserName.equals("")) {
            return "Error: All fields are required!";
        }

        if (userFromDBByUserName != null && !userFromDBByUserName.getId().equals(id)) {
            return "Error: Username exists!";
        }

        try {
            userDao.updateData(changedUser);
            return "Changes saved!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = userDao.getDataByUsername(s);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
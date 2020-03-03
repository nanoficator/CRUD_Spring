package dao;

import model.Role;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAllData() throws SQLException;

    void deleteAllData() throws SQLException;

    void addData(User user) throws SQLException;

    void deleteData(User user) throws SQLException;

    User getDataByID(Long id) throws SQLException;

    User getDataByUsername(String username) throws SQLException;

    void changeUsername(Long id, String newUsername) throws SQLException;

    void changePassword(Long id, String newPassword) throws SQLException;

    void changeRoles(Long id, List<Role> newRoles);

}
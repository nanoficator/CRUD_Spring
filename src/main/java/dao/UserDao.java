package dao;

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

    void updateData(User user) throws SQLException;

}
package dao;

import model.Role;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao {

    List<User> getAllData() throws SQLException;

    void deleteAllData() throws SQLException;

    void addData(Role role) throws SQLException;

    void deleteData(Role role) throws SQLException;

    User getDataByID(Long id) throws SQLException;

    User getDataByName(String name) throws SQLException;

    void changeName(Long id, String newName) throws SQLException;

}

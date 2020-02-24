package service;

import dao.RoleDao;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        try {
            return roleDao.getAllData();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }    }

    @Override
    public void deleteAllRoles() {
        try {
            roleDao.deleteAllData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExistName(String name) {
        try{
            Role roleFromDB = roleDao.getDataByName(name);
            if (roleFromDB != null) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Role getRoleByID(Long id) {
        try {
            return roleDao.getDataByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }    }

    @Override
    public Role getRoleByName(String name) {
        try {
            return roleDao.getDataByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String addRole(Role role) {
        if (isExistName(role.getName())) {
            return "Error: Username exists!";
        }
        try {
            roleDao.addData(role);
            return "Role was added!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }    }

    @Override
    public String deleteRole(Long id) {

        Role roleFromDB = getRoleByID(id);

        if (roleFromDB == null) {
            return "Error: Role does not exist!";
        }

        try {
            roleDao.deleteData(roleFromDB);
            return "User was deleted!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }

    @Override
    public String changeRole(User changedRole) {
        Long id = changedRole.getId();
        String newName = changedRole.getUsername();

        Role roleFromDBById = getRoleByID(id);
        Role roleFromDBByName = getRoleByName(newName);

        if (roleFromDBById == null) {
            return "Error: Role does not exist!";
        }

        if (id == null ||newName.equals("")) {
            return "Error: All fields are required!";
        }

        if (roleFromDBByName != null && !roleFromDBByName.getId().equals(id)) {
            return "Error: Username exists!";
        }

        try {
            roleDao.changeName(id, newName);
            return "Changes saved!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: SQL Exception!";
        }
    }
}

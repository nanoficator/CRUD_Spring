package service;

import model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    void deleteAllRoles();

    boolean isExistName(String name);

    Role getRoleByID(Long id);

    Role getRoleByName(String name);

    String addRole(Role role);

    String deleteRole(Long id);

    String changeRole(Role changedRole);

}
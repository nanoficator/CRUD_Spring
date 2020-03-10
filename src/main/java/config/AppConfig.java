package config;

import controller.UserController;
import dao.RoleDao;
import dao.RoleDaoHibernate;
import dao.UserDao;
import dao.UserDaoHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import service.*;
import util.DBHelper;

public class AppConfig {

    @Bean
    UserController userController() {
        return new UserController();
    }

    @Bean
    DBHelper dbHelper() {
        return new DBHelper();
    }

    @Autowired
    DBHelper dbHelper;

    @Bean
    UserDao userDao() {
        return new UserDaoHibernate(dbHelper.getConfiguration());
    }

    @Bean
    RoleDao roleDao() {
        return new RoleDaoHibernate(dbHelper.getConfiguration());
    }

    @Bean
    UserService userService() {
        return new UserServiceImp();
    }

    @Bean
    RoleService roleService() {
        return new RoleServiceImpl();
    }

}

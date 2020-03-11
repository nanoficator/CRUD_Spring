package config;

import controller.UserController;
import dao.RoleDaoHibernate;
import dao.UserDaoHibernate;
import model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.RoleServiceImp;
import service.UserServiceImp;
import util.DBHelper;

@Configuration
@EnableWebMvc
@ComponentScan
public class AppConfig {

    @Bean
    User user() {
        return new User();
    }

    @Bean
    DBHelper dbHelper() {
        return new DBHelper();
    }

    @Bean
    UserServiceImp userServiceImp() {
        return new UserServiceImp();
    }

    @Bean
    RoleServiceImp roleServiceImp() {
        return new RoleServiceImp();
    }

    @Bean
    UserDaoHibernate userDaoHibernate() {
        return new UserDaoHibernate(dbHelper().getConfiguration());
    }

    @Bean
    RoleDaoHibernate roleDaoHibernate() {
        return new RoleDaoHibernate(dbHelper().getConfiguration());
    }

    @Bean
    UserController  userController() {
        return new UserController();
    }

    @Bean
    SecurityHandler securityHandler() {
        return new SecurityHandler();
    }
}
package config;

import controller.UserController;
import dao.RoleDaoHibernate;
import dao.UserDaoHibernate;
import model.Role;
import model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import security.SecurityHandler;
import service.RoleServiceImp;
import service.UserServiceImp;

@Configuration
@EnableWebMvc
@ComponentScan
public class AppConfig {

    //    package "model"

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public Role role() {
        return new Role();
    }

    //    package "dao"

    @Bean
    public org.hibernate.cfg.Configuration getConfiguration() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/crud_spring?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "p@ssw0rd");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "none");
        return configuration;
    }

    @Bean
    public UserDaoHibernate userDaoHibernate() {
        return new UserDaoHibernate(getConfiguration());
    }

    @Bean
    public RoleDaoHibernate roleDaoHibernate() {
        return new RoleDaoHibernate(getConfiguration());
    }

    //    package "service"

    @Bean
    public UserServiceImp userServiceImp() {
        return new UserServiceImp();
    }

    @Bean
    public RoleServiceImp roleServiceImp() {
        return new RoleServiceImp();
    }

    //    package "controller"

    @Bean
    public UserController  userController() {
        return new UserController();
    }

    //    package "security"

    @Bean
    public SecurityHandler securityHandler() {
        return new SecurityHandler();
    }
}
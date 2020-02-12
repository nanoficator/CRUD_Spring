package config;

import controller.UserController;
import dao.UserDao;
import dao.UserDaoHibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import service.UserService;
import service.UserServiceImp;
import util.DBHelper;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig {

    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    UserController userController() {
        return new UserController();
    }

    @Bean
    DBHelper dbHelper() {
        return new DBHelper();
    }

    @Bean
    UserDao userDao() {
        return new UserDaoHibernate(dbHelper().getConfiguration());
    }

    @Bean
    UserService userService() {
        return new UserServiceImp();
    }
}
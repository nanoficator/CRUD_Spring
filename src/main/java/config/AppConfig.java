package config;

import dao.RoleDaoHibernate;
import dao.UserDaoHibernate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.RoleServiceImp;
import service.UserDetailsServiceImp;
import service.UserServiceImp;
import util.DBHelper;

@Configuration
@EnableWebMvc
@ComponentScan
public class AppConfig {

    @Bean
    DBHelper dbHelper() {
        return new DBHelper();
    }

    @Bean
    UserServiceImp userServiceImp() {
        return new UserServiceImp();
    }

    @Bean
    UserDetailsServiceImp userDetailsServiceImp() {
        return new UserDetailsServiceImp();
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
}
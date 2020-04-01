package config;

import controller.UserController;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import security.SecurityConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                WebConfig.class,
                DBConfig.class,
                SecurityConfig.class,
                UserController.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
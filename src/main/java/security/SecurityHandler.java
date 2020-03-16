package security;

import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import service.RoleService;
import service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
    }

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User loggedInUser = (User) authentication.getPrincipal();
        List<Role> roles = loggedInUser.getRoles();
        if (roles.contains(roleService.getRoleByName("ROLE_ADMIN"))) {
            httpServletResponse.sendRedirect("/admin/table");
        } else if (roles.contains(roleService.getRoleByName("ROLE_USER"))) {
            Long id = userService.getUserByUsername(loggedInUser.getUsername()).getId();
            httpServletResponse.sendRedirect("/user/info?id=" + id);
        }
    }
}
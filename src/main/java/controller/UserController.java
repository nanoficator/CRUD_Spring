package controller;

import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import service.RoleService;
import service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceImp")
    UserService userService;

    @Autowired
    @Qualifier("roleServiceImp")
    RoleService roleService;

    @Autowired
    @Qualifier("securityHandler")
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView defaultPage = new ModelAndView();
        defaultPage.setViewName("redirect:/login");
        return defaultPage;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        List<Role> allRoles = roleService.getAllRoles();
        ModelAndView addPage = new ModelAndView("addPage");
        addPage.addObject("allRoles", allRoles);
        return addPage;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
        public ModelAndView addUser(@ModelAttribute("user") User user,
                @ModelAttribute("ROLE_ADMIN") String roleAdmin,
                @ModelAttribute("ROLE_USER") String roleUser) {

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleService.getRoleByName(roleAdmin));
        userRoles.add(roleService.getRoleByName(roleUser));
        user.setRoles(userRoles);
        String result = userService.addUser(user);

        ModelAndView addPage = new ModelAndView("addPage");
        if (result.contains("Error:")) {
            addPage.addObject("message", result);
        } else {
            addPage.addObject("message", "User " + user.getUsername() + " was added");
        }
        addPage.setViewName("redirect:/result");
        return addPage;

    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public ModelAndView deletePage(@ModelAttribute("id") Long id) {
        ModelAndView deletePage = new ModelAndView("deletePage");
        if (id == 0) {
            deletePage.addObject("id", id);
            deletePage.addObject("userName", "all users");
        } else {
            User userForDelete = userService.getUserByID(id);
            if (userForDelete == null) {
                deletePage.addObject("message", "Error: User does not exist!");
                deletePage.setViewName("redirect:/result");
            } else {
                deletePage.addObject("id", id);
                deletePage.addObject("userName", userForDelete.getUsername());
            }
        }
        return deletePage;
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@ModelAttribute("id") Long id) {
        ModelAndView deletePage = new ModelAndView("deletePage");
        if (id == 0) {
            userService.deleteAllUsers();
            deletePage.addObject("message", "All users were deleted!");
        } else {
            User userForDelete = userService.getUserByID(id);
            String result = userService.deleteUser(id);
            if (result.contains("Error:")) {
                deletePage.addObject("message", result);
            } else {
                deletePage.addObject("message", "User " + userForDelete.getUsername() + " was deleted");
            }
        }
        deletePage.setViewName("redirect:/result");
        return deletePage;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView editPage(@ModelAttribute("id") Long id) {

        User userForEdit = userService.getUserByID(id);
        userForEdit.setConfirmPassword(userForEdit.getPassword());
        List<Role> allRoles = roleService.getAllRoles();

        ModelAndView editPage = new ModelAndView("editPage");
        editPage.addObject("user", userForEdit);
        editPage.addObject("allRoles", allRoles);
        return editPage;

    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User userChanged,
                                 @ModelAttribute("ROLE_ADMIN") String roleAdmin,
                                 @ModelAttribute("ROLE_USER") String roleUser) {

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleService.getRoleByName(roleAdmin));
        userRoles.add(roleService.getRoleByName(roleUser));
        userChanged.setRoles(userRoles);
        String result = userService.changeUser(userChanged);

        ModelAndView editPage = new ModelAndView("editPage");
        if (result.contains("Error:")) {
            editPage.addObject("message", result);
        } else {
            editPage.addObject("message", "User " + userChanged.getUsername() + " was changed");
        }
        editPage.setViewName("redirect:/result");
        return editPage;

    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ModelAndView resultPage(@ModelAttribute("message") String message) {
        ModelAndView resultPage = new ModelAndView("resultPage");
        resultPage.addObject("message", message);
        return resultPage;
    }

    @RequestMapping(value = "/admin/table", method = RequestMethod.GET)
    public ModelAndView tableUser() {
        List<User> allUsers = userService.getAllUsers();
        ModelAndView tablePage = new ModelAndView("tablePage");
        tablePage.addObject("allUsers", allUsers);
        return tablePage;
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public ModelAndView infoPage(@ModelAttribute ("id") Long id) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByID(id);
        boolean loggedInUserIsAdmin = loggedInUser.getRoles().contains(roleService.getRoleByName("ROLE_ADMIN"));

        ModelAndView infoPage = new ModelAndView("infoPage");
        if (user == null) {
            infoPage.setViewName("redirect:/result");
            infoPage.addObject("message", "Error: User with ID = " + id + " does not exist!");
        } else if (loggedInUser.getId() != user.getId() && !loggedInUserIsAdmin) {
            infoPage.setStatus(HttpStatus.FORBIDDEN);
            infoPage.setViewName("redirect:/user/info?id=" + loggedInUser.getId());
        } else {
            infoPage.addObject("user", user);
            infoPage.addObject("loggedInUserIsAdmin", loggedInUserIsAdmin);
        }
        return infoPage;

    }
}
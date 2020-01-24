package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.example.service.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private UserService userService = UserServiceFactory.getUserService();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView authPage() {
        ModelAndView authPage = new ModelAndView("authPage");
        return authPage;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView authUser() {
        ModelAndView authPage = new ModelAndView("authPage");
        return authPage;
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ModelAndView resultPage(@ModelAttribute("message") String message) {
        ModelAndView resultPage = new ModelAndView("resultPage");
        resultPage.addObject("message", message);
        return resultPage;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView addPage = new ModelAndView("addPage");
        return addPage;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        ModelAndView addPage = new ModelAndView("addPage");
        addPage.addObject("message", "User " + user.getUserName() + " was added");
        addPage.setViewName("redirect:/result");
        return addPage;
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public ModelAndView deletePage(@ModelAttribute("id") Long id) {
        User userForDelete = userService.getUserByID(id);
        ModelAndView deletePage = new ModelAndView("deletePage");
        deletePage.addObject("id", id);
        deletePage.addObject("userName", userForDelete.getUserName());
        return deletePage;
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public ModelAndView deleteUser(@ModelAttribute("id") Long id) {
        User userForDelete = userService.getUserByID(id);
        userService.deleteUserById(id);
        ModelAndView deletePage = new ModelAndView("deletePage");
        deletePage.setViewName("redirect:/result");
        deletePage.addObject("message", "User " + userForDelete.getUserName() + " was deleted");
        return deletePage;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView editPage(@ModelAttribute("id") Long id) {
        User userForEdit = userService.getUserByID(id);
        ModelAndView editPage = new ModelAndView("editPage");
        editPage.addObject("user", userForEdit);
        if (userForEdit.getGender().equalsIgnoreCase("male")) {
            editPage.addObject("agender", "female");
        } else {
            editPage.addObject("agender", "male");
        }
        return editPage;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User userChanged) {
        userService.changeUser(userChanged);
        ModelAndView editPage = new ModelAndView("editPage");
        editPage.setViewName("redirect:/result");
        editPage.addObject("message", "User " + userChanged.getUserName() + " was changed");
        return editPage;
    }

    @RequestMapping(value = "/admin/result", method = RequestMethod.GET)
    public ModelAndView resultUser() {
        ModelAndView resultPage = new ModelAndView("resultPage");
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
        User user = userService.getUserByID(id);
        ModelAndView infoPage = new ModelAndView("infoPage");
        if (user == null) {
            infoPage.setViewName("redirect:/result");
            infoPage.addObject("message", "Error: User with ID = " + id + " does not exist!");
        } else {
            infoPage.addObject("user", user);
        }
        return infoPage;
    }
}
package org.example.controller;

import org.example.exception.DBException;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private UserService userService = UserServiceFactory.getUserService();

    public UserController() throws DBException {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView authUser() {
        ModelAndView authPage = new ModelAndView("authPage");
        return authPage;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView addPage = new ModelAndView("addPage");
        return addPage;
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser() {
        ModelAndView deletePage = new ModelAndView("deletePage");
        return deletePage;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView editUser() {
        ModelAndView editPage = new ModelAndView("editPage");
        return editPage;
    }

    @RequestMapping(value = "/admin/result", method = RequestMethod.GET)
    public ModelAndView resultUser() {
        ModelAndView resultPage = new ModelAndView("resultPage");
        return resultPage;
    }

    @RequestMapping(value = "/admin/table", method = RequestMethod.GET)
    public ModelAndView tableUser() throws DBException {
        List<User> allUsers = userService.getAllUsers();
        ModelAndView tablePage = new ModelAndView("tablePage");
        tablePage.addObject("allUsers", allUsers);
        return tablePage;
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public ModelAndView infoUser() {
        ModelAndView infoPage = new ModelAndView("infoPage");
        return infoPage;
    }
}
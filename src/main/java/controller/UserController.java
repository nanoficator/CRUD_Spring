package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView authPage() {
        ModelAndView authPage = new ModelAndView("authPage");
        return authPage;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView authUser(@ModelAttribute ("userName") String userName, @ModelAttribute ("password") String password) {
        User user = userService.getUserByUserName(userName);
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
        String result = userService.addUser(user);
        ModelAndView addPage = new ModelAndView("addPage");
        if (result.contains("Error:")) {
            addPage.addObject("message", result);
        } else {
            addPage.addObject("message", "User " + user.getUserName() + " was added");
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
                deletePage.addObject("userName", userForDelete.getUserName());
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
                deletePage.addObject("message", "User " + userForDelete.getUserName() + " was deleted");
            }
        }
        deletePage.setViewName("redirect:/result");
        return deletePage;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView editPage(@ModelAttribute("id") Long id) {
        User userForEdit = userService.getUserByID(id);
        ModelAndView editPage = new ModelAndView("editPage");
        editPage.addObject("user", userForEdit);
        return editPage;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User userChanged) {
        String result = userService.changeUser(userChanged);
        ModelAndView editPage = new ModelAndView("editPage");
        if (result.contains("Error:")) {
            editPage.addObject("message", result);
        } else {
            editPage.addObject("message", "User " + userChanged.getUserName() + " was changed");
        }
        editPage.setViewName("redirect:/result");
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
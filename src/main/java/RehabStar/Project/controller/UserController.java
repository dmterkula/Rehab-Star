package RehabStar.Project.controller;

import RehabStar.Project.domain.User;
import RehabStar.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by David Terkula on 10/3/2017.
 */
@Controller
public class UserController{// implements ErrorController {
    private UserService userService;
    private static final String PATH = "/error";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/returnAll")
    public @ResponseBody List<User> findAllUser() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET)
    public @ResponseBody User findUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }



}

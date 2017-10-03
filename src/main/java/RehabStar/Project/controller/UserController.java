package RehabStar.Project.controller;

import RehabStar.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by David Terkula on 10/3/2017.
 */
@Controller
public class UserController implements ErrorController {
    private UserService userService;
    private static final String PATH = "/error";

    /*
     Constructor for the UserController
   */

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*
       Returns error handling messagae
    */

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    /*
      Returns error path
   */
    @Override
    public String getErrorPath() {
        return PATH;
    }


}

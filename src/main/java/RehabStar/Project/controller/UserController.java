package RehabStar.Project.controller;

import RehabStar.Project.domain.User;
import RehabStar.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by David Terkula on 10/3/2017.
 */
@Controller
@RequestMapping("/templates")
public class UserController{// implements ErrorController {
    private UserService userService;
    private static final String PATH = "/error";

    /*
     Constructor for the UserController
   */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }




    //return all users
    @RequestMapping(value = "/findAllUsers")
    public @ResponseBody List<User> findAllUser() {
        return userService.getAllUsers();

    }

    /*
      Returns User of passed in id
   */
    @RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET)
    public @ResponseBody User findUserById(@PathVariable("id") int id){
        return userService.findUserById(id);
    }

    /*
    Returns the user wth the given id with the updated username
   */
    @RequestMapping(value = "/updateUserName/{id}/{username}", method = RequestMethod.GET)
    public @ResponseBody User updateUserNameById(@PathVariable("id") int id, @PathVariable("username") String username){
        userService.updateUserName(id, username);
        return userService.findUserById(id);
    }

    /*
   Returns the user wth the given id with the updated email
   */
    @RequestMapping(value = "/updateUserEmail/{id}/{email}", method = RequestMethod.GET)
    public @ResponseBody User updateUserEmailById(@PathVariable("id") int id, @PathVariable("email") String email){
        userService.updateEmail(id, email);
        return userService.findUserById(id);
    }

    /*
   Returns the user wth the given id with the updated password
   */
    @RequestMapping(value = "/updateUserPassword/{id}/{password}", method = RequestMethod.GET)
    public @ResponseBody User updateUserPasswordById(@PathVariable("id") int id, @PathVariable("password") String password){
        userService.updatePassword(id, password);
        return userService.findUserById(id);
    }

    /*
    Returns the user wth the given username
  */
    @RequestMapping(value = "/findUserByUserName/{username}", method = RequestMethod.GET)
    public @ResponseBody User findUserByUserName(@PathVariable("username") String username){
        return userService.findUserByUserName(username);
    }

    @RequestMapping(value = "/authenticate/{userName}/{password}", method = RequestMethod.GET)
    public @ResponseBody boolean authenticate(@PathVariable("userName") String userName, @PathVariable("password") String password){
        boolean b = userService.authenticate(userName, password);
        return b;
    }

    @RequestMapping(value = "/incrementDaysCleanById/{id}", method = RequestMethod.GET)
    public @ResponseBody void incrementDaysClean(@PathVariable int id){
        userService.incrementDaysClean(id);
    }




    @RequestMapping("home")
    public ModelAndView dashboard() {
        User user = new User();
        return new ModelAndView("index", "index", user);
    }

    @RequestMapping(value = "/authenticate", method=RequestMethod.POST)
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new User());
        return "greeting";
    }


}

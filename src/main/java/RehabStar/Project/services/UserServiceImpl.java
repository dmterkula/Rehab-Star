package RehabStar.Project.services;

import RehabStar.Project.dal.UserDao;
import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by david terkula on 10/3/2017.
 */
@Component
public class UserServiceImpl implements UserService{
    private UserDao userDAO;

    @Autowired
    public UserServiceImpl(UserDao userDAO){
        this.userDAO = userDAO;
    }

    /*
      Creates a new user by calling dal layer
  */
    public void createUser(int id, String userName, String email, String password){
        User u = new User(id, userName, email, password);
        userDAO.addUser(u);
    }

    /*
     Updates a user's name given their id
  */
    public void updateUserName(int id, String userName){
        userDAO.updateUserName(id, userName);
    }

    /*
     Updates a user's email given their id
  */
    public void updateEmail(int id, String email){
        userDAO.updateEmail(id, email);
    }

    /*
     Updates a user's password given their id
  */
    public void updatePassword(int id, String password){
        userDAO.updatePassword(id, password);
    }

    /*
     Deletes a user given their id
  */
    public void deleteUser(int id){
        userDAO.deleteUser(id);
    }
}

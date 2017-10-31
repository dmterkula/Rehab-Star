package RehabStar.Project.dal;

import RehabStar.Project.domain.User;

import java.util.List;

/**
 * Created by david terkula on 10/3/2017.
 */
public interface UserDao {
    /*
     *   Returns a list of all Users
     */
    List<User> getAllUsers();


    /*
     *   Returns the User with the matching ID
     */
    User findUserById(int id);

    /*
     *   Returns the User with the matching user name
     */
    User findUserByUserName(String username);

    /*
     *   Adds a User to the database
     */
    void addUser(User u);

    /*
     * Updates the User's userName
     */
    void updateUserName(int id, String userName);

    /*
     * Updates the User's email
     */
    void updateEmail(int id, String email);

    /*
     * Updates the User's password
     */
    void updatePassword(int id, String password);


    /*
    * deletes the user from the db
    */
    void deleteUser (int id);


}

package RehabStar.Project.services;

/**
 * Created by David Terkula on 10/3/2017.
 */
public interface UserService {

    /*
        Creates a new user by calling dal layer
    */
    void createUser(int id, String userName, String email, String password);

    /*
      Updates a user's name given their id
   */
    void updateUserName(int id, String userName);

    /*
      Updates a user's email given their id
   */
    void updateEmail(int id, String email);

    /*
      Updates a user's password given their id
   */
    void updatePassword(int id, String password);

    /*
      Deletes a user given their id
   */
    void deleteUser(int id);
}

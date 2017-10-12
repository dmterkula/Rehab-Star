package RehabStar.Project.dal;

import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * Created by David Terkula on 10/3/2017.
 */
@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    *   Returns a list of all Users
    */

    @Override
    public List<User> getAllUsers(){
        String selectAll = "SELECT * FROM USERS";
        List<User> users = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    /*
     *   Returns the User with the matching ID
     */
    @Override
    public User findUserById(int id){
        String selectUser = "SELECT * FROM USERS WHERE id = ?";

        List<User> users = jdbcTemplate.query(selectUser, new Object[] { id },
                (rs, rowNum) ->
                        new User(
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password")));
        return users.get(0);
    }

    /*
    *   Returns the User with the matching userName
    */
    @Override
    public User findUserByUserName(String userName){
        String selectUser = "SELECT * FROM USERS WHERE username = ?";

        List<User> users = jdbcTemplate.query(selectUser, new Object[] { userName },
                (rs, rowNum) ->
                        new User(
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password")));
        return users.get(0);
    }

    /*
     *   Adds a User to the database
     */
    @Override
    public void addUser(User u){
        String insert = "INSERT INTO USERS " +
                "(id, username, email, password) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(insert, new Object[] {u.getId(), u.getUserName(), u.getEmail(), u.getPassword()});

    }

    /*
     * Updates the User's userName
     */
    @Override
    public void updateUserName(int id, String userName){
        String updateUserName = "UPDATE USERS SET " + "username=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateUserName, new Object[]{userName, id});
    }

    /*
     * Updates the User's email
     */
    @Override
    public void updateEmail(int id, String email){
        String updateEmail = "UPDATE USERS SET " + "email=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateEmail, new Object[]{email, id});

    }

    /*
     * Updates the User's password
     */
    @Override
    public void updatePassword(int id, String password){
        String updatePassword = "UPDATE USERS SET " + "password=? " +
                "WHERE id=?";
        jdbcTemplate.update(updatePassword, new Object[]{password, id});

    }


    /*
    * deletes the user from the db
    */
    @Override
    public void deleteUser (int id){
        String delete ="DELETE FROM USERS WHERE id = ?";
        jdbcTemplate.update(delete, id);
    }
}

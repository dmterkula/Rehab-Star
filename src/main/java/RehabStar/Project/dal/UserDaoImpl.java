package RehabStar.Project.dal;

import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * Created by david terkula on 10/3/2017.
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
        String selectAll = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(User.class));
        return users;
    }


    /*
     *   Returns the User with the matching ID
     */
    @Override
    public User getUserById(int id){
        String selectUser = "SELECT * FROM users WHERE id = ?";

        List<User> users = jdbcTemplate.query(selectUser, new Object[] { id },
                (rs, rowNum) ->
                        new User(
                                rs.getInt("id"),
                                rs.getString("userName"),
                                rs.getString("email"),
                                rs.getString("password")));
        return users.get(0);
    }

    /*
     *   Adds a User to the database
     */
    @Override
    public void addUser(User u){
        String insert = "INSERT INTO users " +
                "(id, userName, email, password) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(insert, new Object[] {u.getId(), u.getUserName(), u.getEmail(), u.getPassword()});

    }

    /*
     * Updates the User's userName
     */
    @Override
    public void updateUserName(int id, String userName){
        String updateUserName = "UPDATE users SET " + "userName=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateUserName, new Object[]{userName, id});
    }

    /*
     * Updates the User's email
     */
    @Override
    public void updateEmail(int id, String email){
        String updateEmail = "UPDATE users SET " + "email=? " +
                "WHERE id=?";
        jdbcTemplate.update(updateEmail, new Object[]{email, id});

    }

    /*
     * Updates the User's password
     */
    @Override
    public void updatePassword(int id, String password){
        String updatePassword = "UPDATE users SET " + "password=? " +
                "WHERE id=?";
        jdbcTemplate.update(updatePassword, new Object[]{password, id});

    }


    /*
    * deletes the user from the db
    */
    @Override
    public void deleteUser (int id){
        String delete ="DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(delete, id);
    }
}

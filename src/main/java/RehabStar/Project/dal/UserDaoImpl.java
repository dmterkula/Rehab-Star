package RehabStar.Project.dal;

import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Return all
    @Override
    public List<User> getAllUsers(){
        String selectAll = "SELECT * FROM USERS";
        List<User> users = jdbcTemplate.query(selectAll, new BeanPropertyRowMapper<>(User.class));
        if(users != null) {
            return users;
        } else {
            return null;
        }
    }


    //Find user by id
    @Override
    public User findUserById(int id){
        assert id > 0;
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());

    }

    // Add user
    @Override
    public void addUser(User user) {
        assert user != null;
        String sql = "INSERT INTO USERS(ID, USERNAME, EMAIL, PASSWORD) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getUserName(), user.getEmail(), user.getPassword());
    }


    // Update username
    @Override
    public void updateUserName(int id, String userName){

    }

    // Updaye email
    @Override
    public void updateEmail(int id, String email){


    }

    // Update password
    @Override
    public void updatePassword(int id, String password){

    }

    // Delete user
    @Override
    public void deleteUser(int id){

    }
}

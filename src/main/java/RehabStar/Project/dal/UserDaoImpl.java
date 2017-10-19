package RehabStar.Project.dal;

import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by David Terkula on 10/3/2017.
 */
@Component
public class UserDaoImpl implements UserDao {
    private final String COLLECTION = "users";
    @Autowired
    private MongoTemplate mongoTemplate;

    /*
    *   Returns a list of all Users
    */

    @Override
    public List<User> getAllUsers(){
        return mongoTemplate.findAll(User.class);
    }

    /*
     *   Returns the User with the matching ID
     */
    @Override
    public User findUserById(int id){
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, User.class, COLLECTION);
    }

    /*
    *   Returns the User with the matching userName
    */
    @Override
    public User findUserByUserName(String userName){
        Query query = new Query(Criteria.where("userName").is(userName));
        return mongoTemplate.findOne(query, User.class, COLLECTION);
    }

    /*
     *   Adds a User to the database
     */
    @Override
    public void addUser(User u){
        mongoTemplate.insert(u);
    }

    /*
     * Updates the User's userName
     */
    @Override
    public void updateUserName(int id, String userName){
        User u = findUserById(id);
        u.setUserName(userName);
        mongoTemplate.save(u);
    }

    /*
     * Updates the User's email
     */
    @Override
    public void updateEmail(int id, String email){
        User u = findUserById(id);
        u.setEmail(email);
        mongoTemplate.save(u);

    }

    /*
     * Updates the User's password
     */
    @Override
    public void updatePassword(int id, String password){
        User u = findUserById(id);
        u.setPassword(password);
        mongoTemplate.save(u);


    }

    /*
    * deletes the user from the db
    */
    @Override
    public void deleteUser (int id){
        User u = findUserById(id);
        mongoTemplate.remove(u);
    }
}

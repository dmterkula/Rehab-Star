package RehabStar.Project.dal;

import RehabStar.Project.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by dmter on 10/17/2017.
 */
public interface UserRepository extends MongoRepository<User, Integer> {

}

package RehabStar.Project;

import RehabStar.Project.dal.UserRepository;
import RehabStar.Project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class })
public class ProjectApplication implements CommandLineRunner{
@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args){
		User u = new User("dmterk", "email", "password");
		u.setId(1);
		userRepository.save(u);
	}


}
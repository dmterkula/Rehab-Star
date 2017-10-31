package RehabStar.Project;

import RehabStar.Project.services.MyFileReader;
import RehabStar.Project.services.MyFileWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

// Created by David Terkula 10/2/2017
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) throws IOException {
//		MyFileWriter fileWriter = new MyFileWriter("story1.txt");
//		fileWriter.writeToFile("This is a sample file");
//		MyFileReader fileReader = new MyFileReader("story1.txt");
//		byte [] story = fileReader.ReadFile();
//		System.out.println(story);
		SpringApplication.run(ProjectApplication.class, args);
	}

//	@Autowired
//	JdbcTemplate jdbcTemplate;
}

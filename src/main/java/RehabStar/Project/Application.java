package RehabStar.Project;

import RehabStar.Project.controller.UserController;
import RehabStar.Project.dal.UserDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Created by David Terkula 10/2/2017
@Configuration
@ComponentScan
@EnableAutoConfiguration
@ComponentScan(basePackageClasses=UserController.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");


	}

}

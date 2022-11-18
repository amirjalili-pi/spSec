package com.example.userservice;

import com.example.userservice.domain.AUser;
import com.example.userservice.domain.Role;
import com.example.userservice.service.user.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);

	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserServiceImpl userService){
		return args -> {
			userService.saveUser(new AUser(null, "Amirmahdi", "Amirmahdi Jalili", "1234", new ArrayList<>()));
			userService.saveUser(new AUser(null, "Amirhosein", "Amirhosein Jalili", "12345", new ArrayList<>()));
			userService.saveUser(new AUser(null, "Mohammadjavad", "Mohammadjavad GholiPour", "1234", new ArrayList<>()));
			userService.saveUser(new AUser(null, "Ali", "Ali Farhang", "1234", new ArrayList<>()));

			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.addUserRole("Amirmahdi Jalili", "ROLE_USER");
			userService.addUserRole("Amirmahdi Jalili", "ROLE_MANAGER");
			userService.addUserRole("Amirmahdi Jalili", "ROLE_ADMIN");

			userService.addUserRole("Ali Farhang", "ROLE_ADMIN");

			userService.addUserRole("Mohammadjavad GholiPour", "ROLE_MANAGER");


		};
	}
}

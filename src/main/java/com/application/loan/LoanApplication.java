package com.application.loan;

import com.application.loan.model.User;
import com.application.loan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class LoanApplication {

	@Autowired
	private UserRepository userRepository;


	@PostConstruct
	public void initUser() {

		List<User> users = Stream.of(
				new User( 101, "user1", "password1", "user1@example.com"),
				new User(102,"user2", "password2", "user2@example.com")
		).collect(Collectors.toList());
		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}


}

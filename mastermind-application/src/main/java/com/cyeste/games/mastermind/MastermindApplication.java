package com.cyeste.games.mastermind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MastermindApplication {

	public static void main(String[] args) {
		SpringApplication.run(MastermindApplication.class, args);
	}

	
}

package com.example.springboot.mycoolapp.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Value("${team.name}")
	private String teamName;
	
	@Value("${team.email}")
	private String teamEmail;

	@GetMapping("/")
	public String sayHello() {
		return "Hello ! The time is " + LocalDateTime.now() + " Team name is: " + teamName + " & Team email is: " + teamEmail;
	}
	
	@GetMapping("/app1")
	public String app1() {
		return "Hello from app1";
	}
	
	@GetMapping("/app2")
	public String app2() {
		return "Hello from app2";
	}
}

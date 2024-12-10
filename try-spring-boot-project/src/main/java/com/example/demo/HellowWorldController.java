package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWorldController {
	
	@GetMapping("/hello-world")
	public String helloworld() {
		return "Hello World!! Halloo";
	}
}

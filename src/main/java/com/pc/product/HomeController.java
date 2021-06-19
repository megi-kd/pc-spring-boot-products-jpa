package com.pc.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeController {
	
	@GetMapping
	public String index() {
		return "Index page for PC Spring Boot Products project!";
	}

}

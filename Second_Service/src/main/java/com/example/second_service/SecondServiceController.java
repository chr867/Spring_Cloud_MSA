package com.example.second_service;

import com.netflix.discovery.converters.Auto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service/")
@Slf4j
public class SecondServiceController {
	Environment env;
	@Autowired
	public SecondServiceController(Environment env){
		this.env = env;
	}

	@GetMapping("/welcome")
	public String welcome()	{
		return "Welcome to the Second service.";
	}

	@GetMapping("/message")
	public String message(@RequestHeader("second-request") String header){
		log.info(header);
		return header;
	}

	@GetMapping("/check")
	public String check(HttpServletRequest request){
		log.info("Server Port={}", request.getServerPort());
		return String.format("Hi, there. This is a message from Second Service on Port %s",
				env.getProperty("Local.server.port"));
	}
}

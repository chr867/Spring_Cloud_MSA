package com.example.user_micro_service;

import com.example.user_micro_service.dto.UserDto;
import com.example.user_micro_service.service.UserService;
import com.example.user_micro_service.vo.Greeting;
import com.example.user_micro_service.vo.RequestUser;
import com.example.user_micro_service.vo.ResponseUser;
import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    public UserController(Environment env, UserService userService, Greeting gretting){
        this.env = env;
        this.userService = userService;
        this.greeting = gretting;
    }
    private Environment env;
    private Greeting greeting;
    private UserService userService;

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return greeting.getMessage();
//        return env.getProperty("greeting.message");
    }

    @PostMapping("/users")
    public ResponseEntity createUser (@RequestBody RequestUser user){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}

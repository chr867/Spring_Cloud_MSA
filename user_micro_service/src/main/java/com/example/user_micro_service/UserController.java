package com.example.user_micro_service;

import com.example.user_micro_service.dto.UserDto;
import com.example.user_micro_service.jpa.UserEntity;
import com.example.user_micro_service.service.UserService;
import com.example.user_micro_service.vo.Greeting;
import com.example.user_micro_service.vo.RequestUser;
import com.example.user_micro_service.vo.ResponseOrder;
import com.example.user_micro_service.vo.ResponseUser;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-service")
// Gateway 를 통할 땐 Root 값에 application-name 을 추가 해줘야함
@RequiredArgsConstructor
public class UserController {
    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health_check")
    public String status()
    {
        return String.format("It's Working in User Service on Port %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome(){
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser (@RequestBody RequestUser user){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(){
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        userList.forEach(user->result.add(mapper.map(user, ResponseUser.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId){
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser returnUser = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnUser);
    }

}

package com.example.user_micro_service.service;

import com.example.user_micro_service.dto.UserDto;
import com.example.user_micro_service.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();

    UserDetails getUserByUserName(String name);

    UserDto getUserDetailsByEmail(String userName);
}

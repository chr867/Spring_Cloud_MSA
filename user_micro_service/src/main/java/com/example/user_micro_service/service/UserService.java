package com.example.user_micro_service.service;

import com.example.user_micro_service.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}

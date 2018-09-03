package com.apenkovsky.service;

import com.apenkovsky.dto.UserDto;
import com.apenkovsky.entity.User;

import java.util.List;

public interface UserService {

    User loadUserByEmail(String username);

    UserDto getCurrentUserDto(String name);

    User loadUserById(Long id);

    List<User> loadManagers();

    List<User> loadEngineers();

}

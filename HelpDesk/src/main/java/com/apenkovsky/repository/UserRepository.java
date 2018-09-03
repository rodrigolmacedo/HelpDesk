package com.apenkovsky.repository;

import com.apenkovsky.entity.User;

import java.util.List;

public interface UserRepository {

    User loadUserByEmail(String username);

    User loadUserById(Long id);

    List<User> loadManagers();

    List<User> loadEngineers();

}

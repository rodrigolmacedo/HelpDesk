package com.apenkovsky.controller.rest;

import com.apenkovsky.converters.UserConverter;
import com.apenkovsky.dto.UserDto;
import com.apenkovsky.service.UserService;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestContoller {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @GetMapping("/current")
    public ResponseEntity<UserDto> getcurentUser(Authentication auth) {
        String name = auth.getName();

        return ResponseEntity.ok().body(userService.getCurrentUserDto(name));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getNameOfUser(@PathVariable Long userId) throws InvalidDataException {
        Optional<UserDto> userDtoOptional = userConverter
                .convertToDto(userService.loadUserById(userId), new ArrayList<>());
        if (!userDtoOptional.isPresent()) {
            throw new InvalidDataException("Invalid data!");
        } else {
            return ResponseEntity.ok().body(userDtoOptional.get());
        }
    }
}

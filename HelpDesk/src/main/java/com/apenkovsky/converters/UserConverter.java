package com.apenkovsky.converters;

import com.apenkovsky.dto.UserDto;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class UserConverter {

    public Optional<UserDto> convertToDto(final User user, final List<Ticket> tickets) {
        Optional<UserDto> maybeResult = Optional.empty();
        if (user != null && tickets != null) {
            UserDto newUserDto = UserDto.UserDtoBuilder.anUserDto()
                    .withId(user.getId())
                    .withFirstName(user.getFirstName())
                    .withLastName(user.getLastName())
                    .withPosition(user.getPosition())
                    .withPhone(user.getPhone())
                    .withRole(user.getRole())
                    .withEmail(user.getEmail())
                    .withAdress(user.getAddress())
                    .withTickets(tickets)
                    .build();

            maybeResult = Optional.of(newUserDto);
        }
        return maybeResult;
    }
}

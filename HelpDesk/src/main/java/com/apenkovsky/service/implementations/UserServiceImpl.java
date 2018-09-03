package com.apenkovsky.service.implementations;

import com.apenkovsky.dto.UserDto;
import com.apenkovsky.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apenkovsky.entity.User;
import com.apenkovsky.repository.UserRepository;
import com.apenkovsky.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    public User loadUserByEmail(String email) {
        return userRepository.loadUserByEmail(email);
    }

    public UserDto getCurrentUserDto(String name) {
        User user = loadUserByEmail(name);
        List<Ticket> ticketList = new ArrayList<>();
        switch (user.getRole()) {
            case EMPLOYEE:
                ticketList = ticketServiceImpl.loadTicketsForEmployee(user.getId());
                break;
            case MANAGER:
                ticketList = ticketServiceImpl.loadTicketsForManager(user.getId());
                break;
            case ENGINER:
                ticketList = ticketServiceImpl.loadTicketsForEngineer(user.getId());
                break;
        }
        return UserDto.UserDtoBuilder.anUserDto()
                .withId(user.getId())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withPosition(user.getPosition())
                .withPhone(user.getPhone())
                .withRole(user.getRole())
                .withEmail(user.getEmail())
                .withAdress(user.getAddress())
                .withTickets(ticketList)
                .build();
    }

    public User loadUserById(Long id) {
        return userRepository.loadUserById(id);
    }

    @Override
    public List<User> loadManagers() {
        return userRepository.loadManagers();
    }

    @Override
    public List<User> loadEngineers() {
        return userRepository.loadEngineers();
    }
}

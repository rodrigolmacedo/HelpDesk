package com.apenkovsky.dto;

import com.apenkovsky.enums.Role;
import com.apenkovsky.entity.Ticket;

import java.util.List;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private Role role;
    private String email;
    private String adress;
    private List<Ticket> tickets;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public static final class UserDtoBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String position;
        private String phone;
        private Role role;
        private String email;
        private String address;
        private List<Ticket> tickets;

        private UserDtoBuilder() {
        }

        public static UserDtoBuilder anUserDto() {
            return new UserDtoBuilder();
        }

        public UserDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDtoBuilder withPosition(String position) {
            this.position = position;
            return this;
        }

        public UserDtoBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserDtoBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public UserDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder withAdress(String adress) {
            this.address = adress;
            return this;
        }

        public UserDtoBuilder withTickets(List<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public UserDto build() {
            UserDto userDto = new UserDto();
            userDto.id = this.id;
            userDto.firstName = this.firstName;
            userDto.position = this.position;
            userDto.email = this.email;
            userDto.adress = this.address;
            userDto.lastName = this.lastName;
            userDto.phone = this.phone;
            userDto.role = this.role;
            userDto.tickets = this.tickets;
            return userDto;
        }
    }
}

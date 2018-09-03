package com.apenkovsky.entity;

import com.apenkovsky.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotNull
    @Size(max = 25)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(max = 25)
    private String lastName;

    @Column(name = "position")
    @NotNull
    @Size(max = 30)
    private String position;

    @Column(name = "phone")
    @NotNull
    @Size(max = 20)
    private String phone;

    @Column(name = "role_id")
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "email")
    @NotNull
    @Size(max = 100)
    private String email;

    @Column(name = "password")
    @NotNull
    @Size(max = 20)
    private String password;

    @Column(name = "address")
    @NotNull
    @Size(max = 100)
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

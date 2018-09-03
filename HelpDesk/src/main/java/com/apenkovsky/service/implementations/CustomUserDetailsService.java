package com.apenkovsky.service.implementations;

import com.apenkovsky.enums.Role;
import com.apenkovsky.entity.User;
import com.apenkovsky.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.loadUserByEmail(s);
        if (user != null) {
            List<GrantedAuthority> authorities = buildUserAuthority(user);
            return buildUserForAuthentication(user, authorities);
        }
        return new org.springframework.security.core.userdetails.User(
                "anonymous", "anonymous", false,
                false, false,
                false,
                new ArrayList<>());
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                                                                                          List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true,
                true, true,
                true,
                authorities
        );
    }

    private List<GrantedAuthority> buildUserAuthority(User user) {
        Role role = user.getRole();

        GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(authority);
        return result;
    }
}
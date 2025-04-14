package com.tickets.GoBus.service.impl;


import com.tickets.GoBus.domain.USER_ROLE;
import com.tickets.GoBus.model.BusOperators;
import com.tickets.GoBus.model.User;
import com.tickets.GoBus.repository.BusOperatorsRepository;
import com.tickets.GoBus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final BusOperatorsRepository busOperatorsRepository;
    private static final String BusOperator_PREFIX = "busOperators_";

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.startsWith(BusOperator_PREFIX)) {
        System.out.println(username);
        username=username.substring(BusOperator_PREFIX.length());
        BusOperators busOperators= busOperatorsRepository.findByEmail(username);
        if(busOperators!=null){
            return buildUserDetails(busOperators.getEmail(), busOperators.getPassword(), busOperators.getRole());
        }

    } else {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
        }
    }
    throw new UsernameNotFoundException("user or seller not found with email "+username);
}

private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
    if (role == null) {
        role = USER_ROLE.CUSTOMER;
    }
    List<GrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority(role.toString() ));


    return new org.springframework.security.core.userdetails.User(email, password, authorityList);
}
}

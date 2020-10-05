package com.lti.AuthenticationManager.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lti.AuthenticationManager.client.UserServiceClient;
import com.lti.AuthenticationManager.dto.UserDto;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserServiceClient userServiceClient;
	
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	UserDto user=userServiceClient.getUserByUsername(s);
    	
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
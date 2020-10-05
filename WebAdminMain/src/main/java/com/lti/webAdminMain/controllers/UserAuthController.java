package com.lti.webAdminMain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.webAdminMain.client.UserAuthClient;
import com.lti.webAdminMain.client.UserServiceClient;
import com.lti.webAdminMain.dto.UserDto;
import com.lti.webAdminMain.models.AuthenticationRequest;

@RestController
@RequestMapping("v1/proxy/users")
@EnableFeignClients(basePackages = "com.lti.webAdminMain.client")
public class UserAuthController {
	
	@Autowired
	private UserAuthClient userAuthClient;
	
	@Autowired
	private UserServiceClient userServiceClient;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		return userAuthClient.auth(authenticationRequest);
	}
	
	@GetMapping("/all")
	public  List<UserDto> getUsers() {
		return userServiceClient.getUsers();
	}
	
	

}

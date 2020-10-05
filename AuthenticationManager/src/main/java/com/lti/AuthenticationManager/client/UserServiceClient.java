package com.lti.AuthenticationManager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lti.AuthenticationManager.dto.UserDto;

@FeignClient(url="${user.service.endpoint}",name="USERSERVICE")
public interface UserServiceClient {

	@GetMapping(path = "/auth/{username}")
	public UserDto getUserByUsername(@PathVariable("username") String username);
}
